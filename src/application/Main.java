package application;

import controller.Controller;
import OnlineConnection.*;
import UserInterface.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import ChessBoard.*;

public class Main extends Application {


	@Override
	public void start(Stage primaryStage) {
		Controller ctrl = new Controller(primaryStage);
		primaryStage.setTitle("iChess");
		primaryStage.setHeight(1000);
		primaryStage.setWidth(1000);

		ctrl.start();
		//primaryStage.setScene(new Scene(chessBoard));
		//primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
