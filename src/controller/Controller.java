package controller;

import ChessBoard.MoveData;
import OnlineConnection.Client;
import OnlineConnection.Network;
import OnlineConnection.Server;
import UserInterface.ChessBoardView;
import UserInterface.StartMenu;
import application.StorageManager;
import application.User;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller class will manage which scene is shown and how the UI interacts with iChess data.
 * @author Luis Alvarez Sanchez
 *
 */
public class Controller {
	private StorageManager datastore;
	private Stage stage;
	private Scene currScene;
	private User currUser;
	public ChessBoardView chessBoard;
	public Network connection;
	
	
	/**
	 * Controller constructor requires a stage to enable Controller to change the visible scene.
	 * Does not start by default.
	 * @param primaryStage
	 */
	public Controller(Stage primaryStage) {
		datastore = new StorageManager();
		stage = primaryStage;
		chessBoard = new ChessBoardView(true, this);
		currScene = new Scene(new StartMenu(this));
		currUser = null;
	}

	public void startGame(){
		if(currUser.isClient()){
			connection = createClient();
		}else{
			connection = createServer();
		}

		try {
			connection.startConnection();
		}
		catch (Exception exception) {
			System.err.println("Error: Failed to start connection");
			System.exit(1);
		}
	}

	private Server createServer() {
		return new Server("98.37.127.113", 55555, data -> {
			Platform.runLater(() -> {
				if (data instanceof MoveData)
				{
					chessBoard.processOpponentMove((MoveData) data);
				}
			});
		});
	}

	private Client createClient() {
		return new Client("98.37.127.113", 55555, data -> {
			Platform.runLater(() -> {
				if (data instanceof MoveData)
				{
					chessBoard.processOpponentMove((MoveData) data);
				}
			});
		});
	}

	/**
	 * returns this instance's storage manager
	 * @return
	 */
	public StorageManager getDatastore() {
		return this.datastore;
	}
	
	/**
	 * Changes which scene is currently visible
	 * @param view 
	 */
	public void setScene(Parent view) {
		currScene = new Scene(view);
		stage.setScene(currScene);
		start();
	}

	/**
	 * Returns the current user
	 * @return
	 */
	public User getCurrUser(){
		return this.currUser;
	}

	/**
	 * Sets the current user to the parameter
	 * @param user
	 */
	public void setCurrUser(User user){
		currUser = user;
	}
	
	/**
	 * Sets the scene in the stage and shows it.
	 */
	public void start() {
		stage.setScene(currScene);
		stage.show();
	}
}