package UserInterface;

import ChessBoard.Piece;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Tile extends Button {
	private int x;
	private int y;
	private Piece piece;
	
	
	/**
	 * Constructor that creates an empty white or black tile.
	 * @param boolean white
	 * @param x coordinate
	 * @param y coordinate
	 */
	public Tile(boolean white, int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.piece = null;
		this.setMinSize((75),(75));
		this.setMaxSize((75), (75));
		
		if(white) {
			this.setStyle("-fx-background-color: #ffffff;");
		}
		else {
			this.setStyle("-fx-background-color: #7f7f7f;");
		}	
	}
	
	public boolean hasPeice() { return (this.piece !=null); }
	public Piece getPiece() { return this.piece; }
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	/**
	 * Checks if the tile has a piece and returns its color if there is one or an empty string if there is no piece.
	 * @return String
	 */
	public String getTilePieceColor() {
		if (getPiece() != null) {  // Checks that the Tile even has a piece
			return getPiece().getColor();
		}
		else {
			return "";
		}
	}
	
	public void setX(int a) {this.x = a; }
	public void setY(int a) {this.y = a; }
	
	
	/**
	 * Sets a piece on the Tile and adds a graphic corresponding to the a piece to the tile.
	 * @param Piece input
	 */
	public void setPiece(Piece input) {
		this.piece = input;
		
		if (this.piece != null) {
			this.setGraphic(new ImageView (piece.getIcon()));
		}
	}
	
	/**
	 * Removes the old piece from the tile and then the function returns the piece
	 * @return returns the old piece.
	 */
	public Piece removePiece() {
		Piece temp = this.piece;
		this.setPiece(null);
		this.setGraphic(null);
		return temp;
	}

}
