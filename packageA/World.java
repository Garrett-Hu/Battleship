package packageA;

public class World {
	private int width, height;
	public final static int north = 0, northeast = 1, east = 2, southeast = 3, south = 4, 
	southwest = 5, west = 6, northwest = 7;
	Boat map[][];
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		if(this.width < 4) {
			this.width = 4;
		}else if(this.width > 10) {
			this.width = 10;
		}
		
		if(this.height < 4) {
			this.height = 4;		
		}else if(this.height > 10) {
			this.height = 10;
		}
		
		map = new Boat[this.height][this.width];
		for(int row = 0; row < this.height; row++) {
			for(int col = 0; col < this.width; col++) {
				map[row][col] = null;
			}
		}				
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Boat[][] getMap() {
		return this.map;
	}
	
	public void setMap(Coordinates c) {
		this.map[c.getY()][c.getX()] = null;
	}
	
	public Boat getOccupant(Coordinates coord) {
		return map[coord.getY()][coord.getX()]; //if != null, else return null sort of thing if it doesnt work
	}
	
	public boolean isLocationValid(Coordinates coord2) {
		if(coord2.getX() > this.width || coord2.getX() < 1 || coord2.getY() > this.height || coord2.getY() < 1) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean isLocationOccupied(Coordinates coord3) {
		if(map[coord3.getY()][coord3.getX()] != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean setOccupant(Boat b, Coordinates coord4) {
		if(map[coord4.getY()][coord4.getX()] == null) {
			map[coord4.getY()][coord4.getX()] = b;
			map[coord4.getX()][coord4.getY()] = b; //added to try
			return true;
		}else {
			return false;
		}
	}
	
	public Coordinates getAdjacentLocation(Coordinates coord5, int direction) {
		if(direction == World.north) {
			if(coord5.getY() >= 2) {
				coord5.setCoordinates(coord5.getX(), coord5.getY() - 1);		
			}else {
				return null;
			}
		}else if(direction == World.northeast) {
			if(coord5.getX() < this.width && coord5.getY() >= 2) {
				coord5.setCoordinates(coord5.getX() + 1, coord5.getY() - 1);		
			}else {
				return null;
			}
		}else if(direction == World.east) {
			if(coord5.getX() < this.width) {
				coord5.setCoordinates(coord5.getX() + 1, coord5.getY());		
			}else {
				return null;
			}
		}else if(direction == World.southeast) {
			if(coord5.getX() < this.width && coord5.getY() < this.height) {
				coord5.setCoordinates(coord5.getX() + 1, coord5.getY() + 1);		
			}else {
				return null;
			}
		}else if(direction == World.south) {
			if(coord5.getY() < this.height) {
				coord5.setCoordinates(coord5.getX(), coord5.getY() + 1);		
			}else {
				return null;
			}
		}else if(direction == World.southwest) {
			if(coord5.getX() >= 2 && coord5.getY() < this.height) {
				coord5.setCoordinates(coord5.getX() - 1, coord5.getY() + 1);		
			}else {
				return null;
			}
		}else if(direction == World.west) {
			if(coord5.getX() >= 2) {
				coord5.setCoordinates(coord5.getX() - 1, coord5.getY());		
			}else {
				return null;
			}
		}else if(direction == World.northwest) {
			if(coord5.getX() >= 2 && coord5.getY() >= 2) {
				coord5.setCoordinates(coord5.getX() - 1, coord5.getY() - 1);		
			}else {
				return null;
			}
		}
		return coord5;
	}
	
	public String drawTeamMap(Boat[] boatArray, int displayType) {
		String invisible = "###  ", unoccupiedVisible = "~~~  ";
		String oneSpace = " ", twoSpaces = "  ", threeSpaces = "   ", newLine = "\n";
		String topRow = "O", xRow = "";
		String returnedString = "";
		char letters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		boolean x = false;
		
		for(int i = 0; i < boatArray.length; i++) {
			map[boatArray[i].getLocation().getY()][boatArray[i].getLocation().getX()] = boatArray[i];
		}
		
		if(displayType == 1) {
			for(int i = 1; i <= this.width; i++) {
				topRow += threeSpaces + oneSpace + i;
			}
			returnedString += topRow + newLine; //topRow = "";			
			
			for(int j = 0; j < this.height; j++) {
				for(int k = 0; k <= this.width; k++) {
					if(k == 0) {
						xRow += Character.toString(letters[j]) + threeSpaces;
					}else {
						xRow += invisible;
					}
				}
				returnedString += xRow + newLine;
				xRow = "";
			}
		}else if(displayType == 2) {
			for(int i = 1; i <= this.width; i++) {
				topRow += threeSpaces + oneSpace + i;
			}
			returnedString += topRow + newLine; //topRow = "";
			
			for(int j = 0; j < this.height; j++) {
				for(int k = 0; k < this.width; k++) {
					if(k == 0) {
						xRow += Character.toString(letters[j]) + threeSpaces;
					}
					for(int z = 0; z < boatArray.length; z++) { 
						x = false;
						if(k != 0 && Math.abs(boatArray[z].getLocation().getX() - (k - 1)) <= boatArray[z].getVision() && 
						Math.abs(boatArray[z].getLocation().getY() - j) <= boatArray[z].getVision()) {
							if(map[j][k - 1] == null) {
								x = true;
								xRow += unoccupiedVisible;
								z = boatArray.length;
							}else if(map[j][k - 1].equals(boatArray[z])){ 
								x = true;
								xRow += boatArray[z].getDirection() + boatArray[z].toString() + twoSpaces;
								z = boatArray.length;
							}
						}
/*						else if(k != 0){ //probably need to have this as an else if? //i think this is executing when k == 0? since it is else when k != 0 fk
							//vision not working here, maybe add a catch phrase outside of this, and detatch it from the else if
							x = true;
							z = boatArray.length;
						}	
						*/
					}
					if(x == false) {
						xRow += invisible;								
					}
				}
				returnedString += xRow + newLine;;
				xRow = "";
			}
		}else if(displayType == 3) {
			for(int i = 1; i <= this.width; i++) {
				topRow += threeSpaces + oneSpace + i;
			}
			returnedString += topRow + newLine; //topRow = "";
			
			for(int j = 0; j < this.height; j++) {
				for(int k = 0; k < this.width; k++) {
					if(k == 0) {
						xRow += Character.toString(letters[j]) + threeSpaces;
					}
					for(int z = 0; z < boatArray.length; z++) { 
						x = false;
						if(k != 0 && Math.abs(boatArray[z].getLocation().getX() - (k - 1)) <= boatArray[z].getVision() && 
						Math.abs(boatArray[z].getLocation().getY() - j) <= boatArray[z].getVision()) {
							if(map[j][k - 1] == null) {
								x = true;
								xRow += unoccupiedVisible;
								z = boatArray.length;
							}else if(map[j][k - 1].equals(boatArray[z])){ 
								x = true;
								xRow += boatArray[z].getHealth() + boatArray[z].toString() + twoSpaces;
								z = boatArray.length;
							}
						}
					}
					if(x == false) {
						xRow += invisible;								
					}
				}
				returnedString += xRow + newLine;;
				xRow = "";
			}
		}
		return returnedString;
	}
	
	public static void main(String[] args) {
		String invisible = "###  ";
		String unoccupiedVisible = "~~~";
		String oneSpace = " ";
		String twoSpaces = "  ";
		String threeSpaces = "   ";
		char letters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		String topRow = "O";
		for(int i = 1; i <= 7; i++) {
			topRow += threeSpaces + oneSpace + i;
		}
		System.out.println(topRow);
		
		String xRow = "";
		for(int j = 0; j < 8; j++) {
			for(int k = 0; k <= 7; k++) {
				if(k == 0) {
					xRow += Character.toString(letters[j]) + threeSpaces;
				}else {
					xRow += invisible;
				}
			}
			System.out.println(xRow);
			xRow = "";
		}
		

	}

}
/*		char x = '\u2192';
System.out.println('\u2190');
System.out.println('\u2192');

*/
//this is aids, but yeah make sure the coordinate is in the square of the vision
//&& map[j][k] == null??? then draw ~~~
//then another else if which draws the boats...must check to see if spaces is == 0 away from boat, meaning that space is boat so dont draw
//the ~~~, instead draw boat
//what is j supposed to be? or inside boatArray[j]



/*//THIS LOGIC IS FLAWED, ABSOLUTE VALUE ALWAYS GREATER THAN 0, prob just have to get the order right, but what if its under/over
						//maybe the subtraction > getVision sort of thing?
						boatArray[z].getLocation().getX() - map[j][k - 1].getLocation().getX() < boatArray[z].getVision()//left
						boatArray[z].getLocation().getX() + boatArray[z].getVision() > width or whatever the hell it is, but use width//right
						
						might have to (x||y)&&(a||b) so an or of the one above, and then do and for height
						//what am i trying to do? the first if statement is for if something is in the vision of any of the z-1 boats there are
						
						*/

/*else if(k != 0){ //probably need to have this as an else if? //i think this is executing when k == 0? since it is else when k != 0 fk
							//vision not working here, maybe add a catch phrase outside of this, and detatch it from the else if
							xRow += invisible;
							z = boatArray.length;
						}*/

/*else if(displayType == 2) {
			for(int i = 1; i <= this.width; i++) {
				topRow += threeSpaces + oneSpace + i;
			}
			returnedString += topRow + newLine; //topRow = "";
			
			for(int j = 0; j < this.height; j++) {
				for(int k = 0; k <= this.width; k++) {
					if(k == 0) {
						xRow += Character.toString(letters[j]) + threeSpaces; // try moving this to outside third for loop, and changing next if to != 1
					}
					for(int z = 0; z < boatArray.length; z++) { //map[j][k -1] is NULL right now, must instantiate it //or i can maybe use j and k since those are
						//coordinates
						if(k != 0 && Math.abs(boatArray[z].getLocation().getX() - (k - 1)) <= boatArray[z].getVision() && 
						Math.abs(boatArray[z].getLocation().getY() - j) <= boatArray[z].getVision()) {
							if(map[j][k - 1] == null) {
								xRow += unoccupiedVisible;
								z = boatArray.length;
							}else if(map[j][k - 1].equals(boatArray[z])){ ///k, j????? //what if k is 0, doesnt get to access k == 0 fk //(map[j][k - 1] != null)
							//boatArray[z].getLocation()
							//boat not yet in the map array...FUCK
								xRow += boatArray[z].getDirection() + boatArray[z].toString() + twoSpaces; //map[j][k].getDirection() can use this instead? maybe
								z = boatArray.length;
							}
						}else if(k != 0){ //probably need to have this as an else if? //i think this is executing when k == 0? since it is else when k != 0 fk
							//vision not working here, maybe add a catch phrase outside of this, and detatch it from the else if
							xRow += invisible;
							z = boatArray.length;
						}
					}
				}
				returnedString += xRow + newLine;;
				xRow = "";
			}*/