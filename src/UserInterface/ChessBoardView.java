package UserInterface;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;
import controller.Controller;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.*;

public class ChessBoardView extends GridPane {
	
	Button chessBoard[][] = new Button[9][9];
	Label you = new Label ("You");
	Label opponent = new Label ("Opponent");
	Controller ctrl;
	
	ChessBoardView(Controller controller) {
		this.ctrl = controller;
		createBoard();
	}
	
	void createBoard() {
		int k = 1;
        add(opponent, 0, 0);
        add(you, 10, 7);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                chessBoard[i + 1][j + 1] = new Button("");
                add(chessBoard[ i + 1][j + 1],j + k, i);
                int a = i + j;
                if((a % 2) == 0) {
                	chessBoard[i + 1][j + 1].setStyle("-fx-background-color: #ffffff;");
                }
                else {
                	chessBoard[i + 1][j + 1].setStyle("-fx-background-color: #7f7f7f;");
                }
                chessBoard[i + 1][j + 1].setMaxSize((100),(100));
                chessBoard[i + 1][j + 1].setMinSize((100),(100));
            }
        }
        you.setTextFill(Color.web("#000000"));
        you.setFont(Font.font("Arial", 50));
        
        opponent.setTextFill(Color.web("#000000"));
        opponent.setFont(Font.font("Arial", 50));
	}
	
	
	

}
