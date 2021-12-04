package ChessBoard;

import javafx.scene.image.Image;

public abstract class Piece {
	protected boolean hasMoved;
	protected Image icon;
	protected boolean color;
	
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
	
	public Piece(boolean x) {
		this.color = x;
		hasMoved = false;
		
		String filePath = "/images/";
		String fileName = this.getColor() + this.getName() + ".png";
		this.icon = new Image(filePath + fileName);
		
	}
	
	
	public abstract String getName();

}
