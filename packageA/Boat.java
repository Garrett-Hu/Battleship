package packageA;

public abstract class Boat {
	private int team, direction, health, strength, vision;
	private Coordinates location;

	public Boat(int team, int direction, int health, int strength, int vision, Coordinates location) {
		this.team = team;
		this.direction = direction;
		this.health = health;
		this.strength = strength;
		this.vision = vision;
		this.location = location;
	}
	
	public int getTeam() {
		return this.team;
	}
	
	public Coordinates getLocation() {
		return this.location;
	}
	
	public int getDirectionInt() {
		return this.direction;
	}
	
	public String getDirection() {
		String s = "";
		if(direction == World.north) {
			s = "↑";
		}else if(direction == World.northeast) {
			s = "↗";
		}else if(direction == World.east) {
			s = "→";
		}else if(direction == World.southeast) {
			s = "↘";
		}else if(direction == World.south) {
			s = "↓";
		}else if(direction == World.southwest) {
			s = "↙";
		}else if(direction == World.west) {
			s = "←";
		}else if(direction == World.northwest) {
			s = "↖";
		}	
		return s;
	}
	
	public int getHealth() {
		return this.health;
	}
	public int getStrength() {
		return this.strength;
	}
	public int getVision() {
		return this.vision;
	}
	
	public abstract String getID();
	public abstract String act(int[] intArray, World w);
	public abstract String getActions();
	
	public String move(World w1) {
		Coordinates tempLocation = this.location;
		Coordinates adjLocation = w1.getAdjacentLocation(this.getLocation(), this.direction);
		if(adjLocation.getX() > 10 || adjLocation.getY() > 10 || adjLocation.getY() < 0 || adjLocation.getX() < 0) {
			return this.toString() + " cannot move to " + adjLocation + " because as it is either occupied or off the map";
		}

		if(w1.getMap()[adjLocation.getY()][adjLocation.getX()] == null) {
			Coordinates c1 = new Coordinates(adjLocation.getY(), adjLocation.getX());
			w1.getMap()[this.getLocation().getY()][this.getLocation().getX()] = null;
//			this.getLocation().setCoordinates(this.getLocation().getY(), this.getLocation().getX());
			w1.setOccupant(this, c1);
			this.setLocation(c1);
//			w1.setOccupant(this, c1);
	//		w1.setOccupant(null, tempLocation);
		//	w1.setMap(tempLocation);
			return this.toString() + " moves from " + tempLocation + " to " + adjLocation;
		}
/*		else {
			return this.toString() + " cannot move to " + adjLocation + " because as it is either occupied or off the map";
		//must check if off map as wel...shit..well i check for that in the other class's method
		//maybe i could do cannot move to X because it is either off the map or is occupied
		//now the big problem is whether it can move to "D3" because it is occupied...i could setCoordinates, then change back? no return has to be the last one...
		//or i could store the setcoordinates a variable, then setcoordinates again...but then i have to change direction...mannnnn
		//oh isn't it just moves to adjLocation? lmfao
		} */
		return "hello";
	}
	
	/*
	 * if(w1.getMap()[adjLocation.getY()][adjLocation.getX()] == null) {
			w1.getMap()[tempLocation.getY()][tempLocation.getX()] = null;
			w1.setOccupant(this, adjLocation);
			this.setLocation(adjLocation); //or next commented out line?
			//location.setCoordinates(adjLocation.getY(), adjLocation.getX());
			w1.setOccupant(this, adjLocation);
			return this.toString() + " moves from " + tempLocation + " to " + adjLocation; //have to change toString method
		}
	 * 
	 */
	
	public String turn(int turnDirection) {
		String directionArray[] = {"north", "northeast", "east", "southeast", "south", "southwest", "west", "northewst"};
		
		String turnString = "";
		if(turnDirection == -1) { //left turn
			direction--;
			if(direction == -1) {
				direction = 7;
			}
			turnString = this + " turned left, now facing "  + directionArray[direction]; 
		}else { //right
			direction++;
			if(direction == 8) {
				direction = 0;
			}
			turnString = this + " turned right, now facing "  + directionArray[direction];
		}
		return turnString;
	}
	
	public String takeHit(int attackStrength) {
		String hitString = "";
		this.setHealth(attackStrength);
		if(this.health <= 0) {
			this.health = 0;
			hitString = this + " has been sunk!";
		}else {
			hitString = this + " has taken " + attackStrength + " damage";
		}
		return hitString;
	}
	
	public void setLocation(Coordinates coord1) {
		this.location = coord1;
	}
	
	public String toString() {
		return this.getID();
	}
	
	public void setHealth(int amount) { //new method while doing tester 9;14 pm 5/15/21...didnt work kek...works?
		this.health -= amount;
	}
}
	
	
/*	public static void main(String[] args) {
		Coordinates c = new Coordinates(3, 5);
		Boat b = new Boat(1, 0, 3, 4, 5, c);
		System.out.println(b.getDirection());
	}
*/	
	
/*	
	public static void main(String[] args) {
		Coordinates c = new Coordinates(3, 5);
		Boat b = new Boat(1, 2, 3, 4, 5, c);
		System.out.print(b.location);
	}
*/


/*


if(direction == World.north) {

}else if(direction == World.northeast) {
	
}else if(direction == World.east) {
	
}else if(direction == World.southeast) {
	
}else if(direction == World.south) {
	
}else if(direction == World.southwest) {
	
}else if(direction == World.west) {
	
}else if(direction == World.northwest) {
	
}

*/