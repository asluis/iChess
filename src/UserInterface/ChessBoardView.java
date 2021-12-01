package UserInterface;

import javafx.scene.layout.GridPane;
import application.*;
import javafx.scene.image.ImageView;
import controller.Controller;

public class ChessBoardView extends GridPane {
	public Tile [][] tiles = new Tile[8][8];
	public Tile activeTile = null;
	private Controller ctrl;
	
	public ChessBoardView(boolean white, Controller controller) {
		super();
		
		this.ctrl = controller;
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
				
				tiles[x][y].setOnAction( e -> onTileClick(finalXVal, finalYVal) );
			}
		}
		
		this.setStartingBoard();
	}
	
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
	
	public void setActiveTile(Tile input) {
		if (this.activeTile != null) {
			if (((this.activeTile.getX() + this.activeTile.getY()) % 2 != 0 ))
				this.activeTile.setStyle("-fx-background-color: #ffffff;");
			if (((this.activeTile.getX() + this.activeTile.getY()) % 2 == 0 ))
				this.activeTile.setStyle("-fx-background-color: #7f7f7f;");
		}
		
		this.activeTile = input;
		
		if(this.activeTile != null) {
			this.activeTile.setStyle("-fx-background-color: red");
		}
		
		
	}
	
	public void onTileClick(int x, int y) {
		Tile clickedTile = tiles[x][y];
		
		if (activeTile != null  && activeTile.getPiece() != null && clickedTile.getTilePieceColor() != activeTile.getTilePieceColor())  {
			MoveData data;
			data = new MoveData(activeTile.getX(), activeTile.getY(), x, y);
			
			if (this.makeMove(data)) {
				//this.setDisable(true);
				
			}
			this.setActiveTile(null);
			
		}
		else
		{
			if (tiles[x][y].getPiece() != null)
			{
				this.setActiveTile(tiles[x][y]);
			}
		}
	}
	
	protected boolean makeMove(MoveData input) {
		
		Tile oldTile = tiles[input.getOldX()][input.getOldY()];
		Tile newTile = tiles[input.getNewX()][input.getNewY()];
		newTile.setPiece(oldTile.removePiece());
		oldTile.setGraphic(null);
		return true;
	}
	
}