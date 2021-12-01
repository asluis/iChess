package UserInterface;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import application.Piece;

public class Tile extends Button {
	private int x;
	private int y;
	private Piece piece;
	
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
	
	public String getTilePieceColor() {
		if (getPiece() != null) {
			return getPiece().getColor();
		}
		else {
			return "";
		}
	}
	
	public void setX(int a) {this.x = a; }
	public void setY(int a) {this.y = a; }
	
	public void setPiece(Piece input) {
		this.piece = input;
		
		if (this.piece != null) {
			this.setGraphic(new ImageView (piece.getIcon()));
		}
	}
	
	public Piece removePiece() {
		Piece temp = this.piece;
		this.setPiece(null);
		this.setGraphic(null);
		return temp;
	}

}
