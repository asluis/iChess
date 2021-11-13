package UserInterface;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.*;
import controller.Controller;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.*;

public class ChessBoardView extends GridPane {
	
	private Button chessBoard[][] = new Button[9][9];
	private Label you = new Label ("You");
	private Label opponent = new Label ("Opponent");
	private Controller ctrl;
	private Image WhitePawn = new Image("/images/WhitePawn.png");
	private Image BlackPawn = new Image("/images/BlackPawn.png");
	private Image WhiteKing = new Image("/images/WhiteKing.png");
	private Image BlackKing = new Image("/images/BlackKing.png");
	private Image WhiteQueen = new Image("/images/WhiteQueen.png");
	private Image BlackQueen = new Image("/images/WhiteQueen.png");
	private Image WhiteRook = new Image("/images/WhiteRook.png");
	private Image BlackRook = new Image("/images/BlackRook.png");
	private Image WhiteKnight = new Image("/images/WhiteKnight.png");
	private Image BlackKnight = new Image("/images/BlackKnight.png");
	private Image WhiteBishop = new Image("/images/WhiteBishop.png");
	private Image BlackBishop = new Image("/images/BlackBishop.png");
	
	ChessBoardView(Controller controller) {
		this.ctrl = controller;
		createBoard();
		updateBoard();
	}
	
	private void createBoard() {
		int k = 1;
        add(opponent, 0, 0);
        add(you, 10, 7);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                chessBoard[i + 1][j + 1] = new Button("");
                add(chessBoard[ i + 1][j + 1],j + k, i);
                if(((i + j) % 2) == 0) {
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
	
	private void updateBoard() {
		for(int i = 1; i < 9; i++) {
            for(int j = 1; j < 9; j++) {
            	if (((i + j) % 2) == 0) {
            		chessBoard[i][j].setStyle("-fx-background-color: #ffffff;");
            	}
            	else {
            		chessBoard[i][j].setStyle("-fx-background-color: #7f7f7f;");
            	}
            	
            	if (ctrl.getGameBoard().getBoard()[i][j].equals("WhitePawn")) {
            		chessBoard[i][j].setGraphic(new ImageView(WhitePawn));
            	}
            	else if (ctrl.getGameBoard().getBoard()[i][j].equals("BlackPawn")) {
            		chessBoard[i][j].setGraphic(new ImageView(BlackPawn));
            	}
            	
            	else if (ctrl.getGameBoard().getBoard()[i][j].equals("WhiteKing")) {
            		chessBoard[i][j].setGraphic(new ImageView(WhiteKing));
            	}
            	else if (ctrl.getGameBoard().getBoard()[i][j].equals("BlackKing")) {
            		chessBoard[i][j].setGraphic(new ImageView(BlackKing));
            	}
            	
            	else if (ctrl.getGameBoard().getBoard()[i][j].equals("WhiteQueen")) {
            		chessBoard[i][j].setGraphic(new ImageView(WhiteQueen));
            	}
            	else if (ctrl.getGameBoard().getBoard()[i][j].equals("BlackQueen")) {
            		chessBoard[i][j].setGraphic(new ImageView(BlackQueen));
            	}
            		
            	else if (ctrl.getGameBoard().getBoard()[i][j].equals("WhiteRook")) {
            		chessBoard[i][j].setGraphic(new ImageView(WhiteRook));
            	}
            	else if (ctrl.getGameBoard().getBoard()[i][j].equals("BlackRook")) {
            		chessBoard[i][j].setGraphic(new ImageView(BlackRook));
            	}
            	
            	else if (ctrl.getGameBoard().getBoard()[i][j].equals("WhiteKnight")) {
            		chessBoard[i][j].setGraphic(new ImageView(WhiteKnight));
            	}
            	else if (ctrl.getGameBoard().getBoard()[i][j].equals("BlackKnight")) {
            		chessBoard[i][j].setGraphic(new ImageView(BlackKnight));
            	}
            	
            	else if (ctrl.getGameBoard().getBoard()[i][j].equals("WhiteBishop")) {
            		chessBoard[i][j].setGraphic(new ImageView(WhiteBishop));
            	}
            	else if (ctrl.getGameBoard().getBoard()[i][j].equals("BlackBishop")) {
            		chessBoard[i][j].setGraphic(new ImageView(BlackBishop));
            	}
            }
		}
	}
}
