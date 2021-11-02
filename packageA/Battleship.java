package packageA;

public class Battleship extends Boat implements Attacker {
	
	public Battleship(int team, Coordinates location, int direction) {
		super(team, direction, 4, 3, 1, location);
	}
	
	@Override
	public String getID() {
		return "B" + this.getTeam();
	}
	
	@Override
	public String getActions() {
		return "Choose any of the following actions for the Battleship:\n1. Move\n2. Turn left\n3. Turn right\n4. Attack";
	}
	
	@Override
	public String act(int[] choices, World w) {
		String returnString = "";
		for(int i = 0; i < choices.length; i++) {
			if(choices[i] == 1) {
				returnString += this.move(w);
			}else if(choices[i] == 2) {
				returnString += this.turn(-1);	//rights and lefts were mixed up for the strings just changed...i dont wanna change other		
			}else if(choices[i] == 3){
				returnString += this.turn(1);
			}else if(choices[i] == 4){
				returnString += this.attack(w);
			}
		}
		return returnString;
	}
	
	@Override
	public String attack(World w1) {
		Coordinates original = this.getLocation();
		Coordinates original2 = this.getLocation();
		boolean hasHit = false;
			for(int i = 1; i <= this.getVision(); i++) {
				//w1.getAdjacentLocation(original, this.getDirectionInt());
				original = original2;
				
			if(this.getDirectionInt() == World.north) {
				original.setCoordinates(original.getX(), original.getY() - i);
			}else if(this.getDirectionInt() == World.east) {
				original.setCoordinates(original.getX() + i, original.getY());
			}else if(this.getDirectionInt() == World.south) {
				original.setCoordinates(original.getX(), original.getY() + i);
			}else if(this.getDirectionInt() == World.west){ //west
				original.setCoordinates(original.getX() - i, original.getY());
			}else if(this.getDirectionInt() == World.northeast) {
				original.setCoordinates(original.getX() + i, original.getY() - i);
			}else if(this.getDirectionInt() == World.southeast) {
				original.setCoordinates(original.getX() + i, original.getY() + i);
			}else if(this.getDirectionInt() == World.southwest) {
				original.setCoordinates(original.getX() - i, original.getY() + i);
			}else { //nw
				original.setCoordinates(original.getX() - i, original.getY() - i);
			}
			
			if(w1.getMap()[original.getY()][original.getX()] != null) {
				//w1.getMap()[original.getY()][original.getX()].takeHit(this.getStrength());
				//w1.getMap()[original.getY()][original.getX()].takeHit(this.getStrength());
				hasHit = true;
				i = 11;
			}
			}
			if(hasHit == true) {
				return this.getID() + " fires cannons! " + w1.getMap()[original.getY()][original.getX()].takeHit(this.getStrength()) + " " 
						+ w1.getMap()[original.getY()][original.getX()].takeHit(this.getStrength());
			}else {
				return "There are no boats currently in range.";
			}		
		}

}
