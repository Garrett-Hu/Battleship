package packageA;

public class Destroyer extends Boat implements Attacker{
	
	public Destroyer(int team, Coordinates location, int direction) {
		super(team, direction, 2, 2, 1, location);
	}
	
	@Override
	public String getID() {
		return "B" + this.getTeam();
	}
	
	@Override
	public String getActions() {
		return "Choose any of the following actions for the Destroyer:\n1. Move\n2. Turn left\n3. Turn right\n4. Attack";
	}
	
	@Override
	public String act(int[] choices, World w) {
		String returnString = "";
		for(int i = 0; i < choices.length; i++) {
			if(choices[i] == 1) {
				this.move(w);
				if(i == 0) {
					returnString += this.getID() + " moved. ";			
				}else {
					returnString += "Then moved.";
				}
			}else if(choices[i] == 2) {
				this.turn(-1);
				if(i == 0) {
					returnString += this.getID() + " turned right. ";			
				}else {
					returnString += "Then turned right.";
				}
			}else if(choices[i] == 3){
				this.turn(1);
				if(i == 0) {
					returnString += this.getID() + " turned left. ";			
				}else {
					returnString += "Then turned left.";
				}
			}else if(choices[i] == 4) {
				attack(w);
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
				//w1.getMap()[original.getY()][original.getX()].takeHit(this.getStrength()); //does this double with the return statement
				hasHit = true;
				i = 11;
			}
			}
			if(hasHit == true) {
				return this.getID() + " fires cannons! " + w1.getMap()[original.getY()][original.getX()].takeHit(this.getStrength());
			}else {
				return "There are no boats currently in range.";
			}		
		}
	
	@Override
	public String takeHit(int numAttacks) {
		if(Math.random() > .5) {
			return this.getID() + "has avoided the attack!";
		}else {
			return this.getID() + "takes " + numAttacks + " damage.";
		}
	}
}
