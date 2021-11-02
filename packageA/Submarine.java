package packageA;

public class Submarine extends ScoutBoat implements Attacker {
	private int numOfTorpedoes;
	
	public Submarine(int team, Coordinates location, int direction) {
		super(team, direction, 3, 2, location);
		this.numOfTorpedoes = 3;
	}
	
	@Override
	public String getID() {
		return "S" + this.getTeam();
	}
	
	@Override
	public String getActions() {
		if(numOfTorpedoes > 0) {
			return "Choose any of the following actions for the Submarine:\n1. Move\n2. Turn left"
					+ "\n3. Turn right\n4. Submerge\n5. Fire Torpedoes";	
		}else {
			return "Choose any of the following actions for the Cruiser:\n1. Move\n2. Turn left"
					+ "\n3. Turn right\n4. Submerge";
		}
		
	}
	
	@Override
	public String act(int[] choices, World w) {
		//turning -1 is left, 1 is right
				String returnString = "";
				for(int i = 0; i < choices.length; i++) {
					if(choices[i] == 1) {
						returnString += this.move(w);
					}else if(choices[i] == 2) {
						returnString += this.turn(-1);	//rights and lefts were mixed up for the strings just changed...i dont wanna change other		
					}else if(choices[i] == 3){
						returnString += this.turn(1);
					}else if(choices[i] == 4){
						returnString += this.submerge(w);
					}else if(choices[i] == 5){
						returnString += this.attack(w);
					}
				}
		return returnString;
	}
	
	public String attack(World w1) {
		Coordinates original = this.getLocation();
		Coordinates original2 = this.getLocation();
		boolean hasHit = false;
		int randomHit = 0;
		if(numOfTorpedoes > 0) { //n,e,s,w even
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
					randomHit = (int)(Math.random() * (w1.getMap()[original.getY()][original.getX()].getHealth()) + 1);
					//w1.getMap()[original.getY()][original.getX()].takeHit(randomHit);
					hasHit = true;
					i = 11;
				}
			}
			if(hasHit == true) {
				return this.getID() + " fires torpedoes! " + w1.getMap()[original.getY()][original.getX()].takeHit(randomHit);
			}else {
				return "There are no boats currently in range.";
			}		
		}else {
			return this.getID() + " has no torpedoes remaining";
		}
	}
	
	public String submerge(World w1) {
		boolean locationFound = false;
		String originalLocation = this.getLocation().toString();
		String newLocation = "";
		int randomXMove, randomYMove = 0;
		while(locationFound == false) {
			randomXMove = (int) (Math.random() * w1.getWidth());
			randomYMove = (int) (Math.random() * w1.getHeight());
			
			if(w1.getMap()[randomYMove][randomXMove] == null && this.getLocation().getX() - randomXMove >= 2 && this.getLocation().getY() - randomYMove >= 2) {
				Coordinates c1 = new Coordinates(randomYMove, randomXMove);
			//	this.getLocation().setCoordinates(randomXMove, randomYMove);
				
				w1.getMap()[this.getLocation().getY()][this.getLocation().getX()] = null;
				w1.setOccupant(this, c1);
				this.setLocation(c1);
	//past 3 lines r new			
				
				//does above line have coordinates to change for next line?
				
				w1.setOccupant(this, c1);
				
				newLocation = c1.toString();
				locationFound = true;
//				System.out.println(this.getID() + " moved from " + originalLocation + " to " + newLocation);
			}
		}
		
		return this.getID() + " moved from " + originalLocation + " to " + newLocation;
	}
	
}


/*
if(this.getDirectionInt() % 2 == 0) {
	if(this.getDirectionInt() == World.north) {
		for(int i = 1; i <= this.getVision(); i++) {
			w1.getAdjacentLocation(this.getLocation(), 5);
		}
	}else if(this.getDirectionInt() == World.east) {
		s = "→";
	}else if(this.getDirectionInt() == World.south) {
		s = "↓";
	}else { //west
		s = "←";
	}
}else {
	if(this.getDirectionInt() == World.northeast) {
		s = "↗";
	}else if(this.getDirectionInt() == World.southeast) {
		s = "↘";
	}else if(this.getDirectionInt() == World.southwest) {
		s = "↙";
	}else { //nw
		s = "↖";
	}
}


///
 * if(this.getDirectionInt() % 2 == 0) {
				
 * 
*/	