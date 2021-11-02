package packageA;

public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates() {
		this.x = 0;
		this.y = 0;
	}
	
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setCoordinates(int xCoord, int yCoord) {
		this.x = xCoord;
		this.y = yCoord;
	}
	
	public String toString() {
		char letters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		return Character.toString(letters[this.getY()]) + (this.getX() + 2); //supposed to return ID + teamNumber, not the square, oh no this is fine
		//the boat supposed to return id + teamnum
	}
	
	
/*	
	public static void main(String[] args) {
		Coordinates c = new Coordinates(3, 5);
		System.out.println(c.toString());

	}
*/
	
}
