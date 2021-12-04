package UserInterface;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import ChessBoard.MoveData;
import ChessBoard.Bishop;
import ChessBoard.King;
import ChessBoard.Knight;
import ChessBoard.MoveData;
import application.*;


import ChessBoard.Pawn;
import ChessBoard.Queen;
import ChessBoard.Rook;
import application.*;
import javafx.scene.image.ImageView;
import controller.Controller;

public class ChessBoardView extends GridPane {
	public Tile [][] tiles = new Tile[8][8];
	public Tile activeTile = null;
	private Controller ctrl;
	
	/**
	 * Creates a chessboard with pieces in their starting place and buttons that allow for piece movement.
	 * @param white boolean true/false
	 * @param controller
	 */
	public ChessBoardView(boolean white, Controller controller) {
		super();
		
		this.ctrl = controller;
		// Nested loop that creates a chessboard with bottom left at (0,0) if white == true and bottom left as (7,7) if white == false.
		for (int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles.length; y++) {
				boolean light = ( (x + y) % 2 != 0 );
				tiles[x][y] = new Tile(light, x, y);
				
				if (white) {
					this.add(tiles[x][y], x, 7 - y); 
				}
				else {
					this.add(tiles[x][y], 7 - x, y); 
				}
				
				final int finalXVal = x;
				final int finalYVal = y;
				
				tiles[x][y].setOnAction(e -> onTileClick(finalXVal, finalYVal));
			}
		}
		
		//Function to set pieces in the starting positions.
		this.setStartingBoard();
	}
	
	/**
	 * Sets pieces in their starting positions.
	 */
	public void setStartingBoard() {
		// Sets pawns
		for (int i = 0; i < tiles.length; i++) {
			tiles[i][1].setPiece(new Pawn(true));
			tiles[i][6].setPiece(new Pawn(false));
		}
		
		// Sets rooks
		tiles[0][0].setPiece(new Rook(true));
		tiles[7][0].setPiece(new Rook(true));
		tiles[0][7].setPiece(new Rook(false));
		tiles[7][7].setPiece(new Rook(false));
		
		// Sets Knights
		tiles[1][0].setPiece(new Knight(true));
		tiles[6][0].setPiece(new Knight(true));
		tiles[1][7].setPiece(new Knight(false));
		tiles[6][7].setPiece(new Knight(false));
		
		// Sets Bishops
		tiles[2][0].setPiece(new Bishop(true));
		tiles[5][0].setPiece(new Bishop(true));
		tiles[2][7].setPiece(new Bishop(false));
		tiles[5][7].setPiece(new Bishop(false));
		
		// Sets Queens
		tiles[3][0].setPiece(new Queen(true));
		tiles[3][7].setPiece(new Queen(false));
		
		// Sets Kings
		tiles[4][0].setPiece(new King(true));
		tiles[4][7].setPiece(new King(false));
		
	}
	
	/**
	 * Removes red color from previous active space and adds it to the new active space.
	 * @param Tile input
	 */
	public void setActiveTile(Tile input) {
		if (this.activeTile != null) {
			if (((this.activeTile.getX() + this.activeTile.getY()) % 2 != 0 )) {
				this.activeTile.setStyle("-fx-background-color: #ffffff;");
			}
			if (((this.activeTile.getX() + this.activeTile.getY()) % 2 == 0 )) {
				this.activeTile.setStyle("-fx-background-color: #7f7f7f;");
			}
		}
		
		this.activeTile = input;
		
		if(this.activeTile != null) {
			this.activeTile.setStyle("-fx-background-color: red");
		}
		
		
	}
	
	/**
	 * Handles logic for when user clicks on a tile.
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void onTileClick(int x, int y) {
		Tile clickedTile = tiles[x][y];
		
		// Checks if a user clicked on an a new piece that is their color.
		if (activeTile != null  && activeTile.getPiece() != null && clickedTile.getTilePieceColor() != activeTile.getTilePieceColor())  {
			MoveData data;
			data = new MoveData(activeTile.getX(), activeTile.getY(), x, y);
			
			if (this.makeMove(data)) {      // Updates local game board.
				if(this.sendMove(data)) {   // If move move is made locally it attempts to send it online.
					this.setDisable(true);  // If the move is sent successfully online then the board is disabled and other player has a turn.
				}
			}
			this.setActiveTile(null);
		}
		// If a user clicks on a tile when there is no active tile.
		else
		{
			if (tiles[x][y].getPiece() != null)  // Checks that there is a piece on tile.
			{
				this.setActiveTile(tiles[x][y]); // Sets active tile to the one user clicked
			}
		}
	}
	
	/**
	 * Tries to send a move online, returns true if successful, false if not.
	 * @param MoveData info
	 * @return boolean
	 */
	protected boolean sendMove(MoveData info) {
		try {
			ctrl.connection.sendData(info);
		} catch (Exception e) {
			System.err.println("Error: Failed to send move");
	        return false;
		}
		
		return true;
	}
	
	/**
	 * Used to take MoveData info sent online and make changes to local board.
	 * @param MoveData info
	 */
	public void processOpponentMove(MoveData info)
    {
        if (makeMove(info))
        {
            this.setDisable(false);
        }
    }
	
	/**
	 * Makes a move on the local board.
	 * @param MoveData input
	 * @return boolean
	 */
	protected boolean makeMove(MoveData input) {
		Tile oldTile = tiles[input.getOldX()][input.getOldY()];
		Tile newTile = tiles[input.getNewX()][input.getNewY()];
		newTile.setPiece(oldTile.removePiece());
		return true;
	}


}