package packageA;

public class AircraftCarrier extends Boat implements Attacker {
	private boolean hasPlanes;
	
	public AircraftCarrier(int team, Coordinates location, int direction) {
		super(team, direction, 5, 1, 1, location);
		hasPlanes = true;
	}
	
	@Override
	public String getID() {
		return "A" + this.getTeam();
	}
	
	@Override
	public String getActions() {
		return "Choose any of the following actions for the Aircraft Carrier:\n1. Move\n2. Turn left"
				+ "\n3. Turn right\n4. Launch planes";
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
	public String attack(World w) {
		Coordinates original = this.getLocation();
		int numAttacks = 0;
		double successRate = 1;
		//original.setCoordinates(original.getX() + i, original.getY());
		if(hasPlanes == true) {
			if(w.getMap()[original.getY() - 1][original.getX() - 1] != null) {
				numAttacks++;
				w.getMap()[original.getY() - 1][original.getX() - 1].takeHit(this.getStrength()); //is the input for takeHit strength? torpedo was random
			}
			if(w.getMap()[original.getY() - 1][original.getX()] != null) {
				numAttacks++;
				w.getMap()[original.getY() - 1][original.getX()].takeHit(this.getStrength());
			}
			if(w.getMap()[original.getY() - 1][original.getX() + 1] != null) {
				numAttacks++;
				w.getMap()[original.getY() - 1][original.getX() + 1].takeHit(this.getStrength());
			}
			if(w.getMap()[original.getY()][original.getX() - 1] != null) {
				numAttacks++;
				w.getMap()[original.getY()][original.getX() - 1].takeHit(this.getStrength());
			}
			if(w.getMap()[original.getY()][original.getX() + 1] != null) {
				numAttacks++;
				w.getMap()[original.getY()][original.getX() + 1].takeHit(this.getStrength());
			}
			if(w.getMap()[original.getY() + 1][original.getX() - 1] != null) {
				numAttacks++;
				w.getMap()[original.getY() + 1][original.getX() - 1].takeHit(this.getStrength());
			}
			if(w.getMap()[original.getY() + 1][original.getX()] != null) {
				numAttacks++;
				w.getMap()[original.getY() + 1][original.getX()].takeHit(this.getStrength());
			}
			if(w.getMap()[original.getY() + 1][original.getX() + 1] != null) {
				numAttacks++;
				w.getMap()[original.getY() + 1][original.getX() + 1].takeHit(this.getStrength());
			}
			if(numAttacks == 0) {
				return "There are no boats currently in range.";
			}else {
				for(int i = 0; i < numAttacks; i++) {
					successRate *= .8;
				}
				if(Math.random() > successRate) {
					hasPlanes = false;
					return "The planes have been destroyed";
				}
				return "Air Raid!";
			}
		}else {
			return this.getID() + " has no planes remaining.";
		}
	}
}
/*
			for(int row = 1; row <= this.getVision(); row++) { //i starts at 1 so not self attack 
				for(int col = 1; col <= this.getVision(); col++) { //also vision == 1 so i can make a case just for this one...i probably dont even need the loops
*/