package controller;

import UserInterface.StartMenu;
import application.StorageManager;
import application.User;
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
	
	/**
	 * Controller constructor requires a stage to enable Controller to change the visible scene.
	 * Does not start by default.
	 * @param primaryStage
	 */
	public Controller(Stage primaryStage) {
		datastore = new StorageManager();
		stage = primaryStage;
		currScene = new Scene(new StartMenu(this));
		currUser = null;
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