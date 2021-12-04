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
	Controller ctrl = new Controller(new Stage());
	ChessBoardView chessBoard = new ChessBoardView(true, ctrl);
	
	public static Network connection;
	@Override
	public void start(Stage primaryStage) {
		
		// Create server first, the comment out server and createClient with the board disabled.
		connection = createServer();
		
		//connection = createClient();
		//chessBoard.setDisable(true);
		
		try {
			connection.startConnection();
		}
		catch (Exception exception) {
			System.err.println("Error: Failed to start connection");
            System.exit(1);
		}
		
		
		primaryStage.setTitle("iChess");
		primaryStage.setHeight(1000);
		primaryStage.setWidth(1000);
		
		primaryStage.setScene(new Scene(chessBoard));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Server createServer() {
        return new Server(5555, data -> {
            Platform.runLater(() -> {
                if (data instanceof MoveData)
                {
                    chessBoard.processOpponentMove((MoveData) data);
                }
            });
        });
    }
	
	 private Client createClient() {
	        return new Client("127.0.0.1", 5555, data -> {
	            Platform.runLater(() -> {
	                if (data instanceof MoveData)
	                {
	                    chessBoard.processOpponentMove((MoveData) data);
	                } 
	            });
	        });
	    }
}
