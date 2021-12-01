package application;
import java.io.Serializable;

public class MoveData implements Serializable {
	int oldX, oldY, newX, newY;
	
	public MoveData() {
		oldX = 0;
		oldY = 0;
		newX = 0;
		newY = 0;
	}
	public MoveData(int a, int b, int c, int d) {
		oldX = a;
		oldY = b;
		newX = c;
		newY = d;
	}
	public int getOldX() { return oldX; }
	public int getOldY() { return oldY; }
	public int getNewX() { return newX; }
	public int getNewY() { return newY; }
	
	public void setOldX(int oldX) { this.oldX = oldX; }
	public void setOldY(int oldY) { this.oldY = oldY; }
	public void setNewX(int newX) { this.newX = newX; }
	public void setNewY(int newY) { this.newY = newY; }
	
	public int getDifferenceX() {  return newX - oldX; }
	public int getDifferenceY() {  return newY - oldY; }
	

}
