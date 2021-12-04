package ChessBoard;

import javafx.scene.image.Image;

public abstract class Piece {
	protected boolean hasMoved;
	protected Image icon;
	protected boolean color;
	
	/**
	 * Returns white if color is true and black if color is false.
	 */
	public String getColor() {
		String result;
		if (this.color == true) {
			result = "White";
		}
		else {
			result = "Black";
		}
		return result;
	}
	
	public Image getIcon() { return this.icon; }
	
	/**
	 * Sets a piece's hasMoved variable to false and sets its icon to the corresponding icon to its color and piece name.
	 * @param x which is the color boolean of an object
	 */
	public Piece(boolean x) {
		this.color = x;
		hasMoved = false;
		
		String filePath = "/images/";
		String fileName = this.getColor() + this.getName() + ".png";
		this.icon = new Image(filePath + fileName);
		
	}
	
	/**
	 * Abstract function used to return the name of a chess piece
	 */
	public abstract String getName();
}
