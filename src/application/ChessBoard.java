package application;

public class ChessBoard {
	
	private String board[][];
	
	public ChessBoard() {
		board = new String[9][9];
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j++) {
				if (j == 0 || i == 0) {
					board[i][j] = " "; // Sets border of Chess Board empty
				}
				else {
					board[i][j] = "empty"; // Sets all board items to empty instead of null;
				}
			}
		}
		
		// Loop to set pawns
		for (int k = 1; k < 9; k++) {
			board[2][k] = "BlackPawn";
			board[7][k] = "WhitePawn";
		}
		
		//Sets queen and king pieces
		board[1][4] = "BlackQueen";
		board[1][5] = "BlackKing";
		board[8][4] = "WhiteQueen";
		board[8][5] = "WhiteKing";
		
		//Sets rook pieces on board.
		board[1][1] = "BlackRook";
		board[1][8] = "BlackRook";
		board[8][1] = "WhiteRook";
		board[8][8] = "WhiteRook";
		
		//Sets knight pieces.
		board[1][2] = "BlackKnight";
		board[1][7] = "BlackKnight";
		board[8][2] = "WhiteKnight";
		board[8][7] = "WhiteKnight";
		
		//Sets Bishop pieces.
		board[1][3] = "BlackBishop";
		board[1][6] = "BlackBishop";
		board[8][3] = "WhiteBishop";
		board[8][6] = "WhiteBishop";
		
	}
	
	public String[][] getBoard() {
		return board;
	}
	
}
