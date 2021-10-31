package application;

import UserInterface.LoginMenu;
import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		Controller ctrl = new Controller();
		
		try {
			primaryStage.setTitle("iChess");
			primaryStage.setHeight(1000);
			primaryStage.setWidth(1000);
			Scene menuScene = new Scene(new LoginMenu());
			menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(menuScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
