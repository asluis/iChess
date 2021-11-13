package controller;

import UserInterface.StartMenu;
import application.ChessBoard;
import application.StorageManager;
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
	private ChessBoard gameBoard;
	
	public ChessBoard getGameBoard() {
		return gameBoard;
	}
	
	/**
	 * Controller constructor requires a stage to enable Controller to change the visible scene.
	 * Does not start by default.
	 * @param primaryStage
	 */
	public Controller(Stage primaryStage) {
		datastore = new StorageManager();
		gameBoard = new ChessBoard();
		stage = primaryStage;
		currScene = new Scene(new StartMenu(this));
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
	 * Sets the scene in the stage and shows it.
	 */
	public void start() {
		stage.setScene(currScene);
		stage.show();
	}
}