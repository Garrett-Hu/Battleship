package packageA;

public class Cruiser extends ScoutBoat{

	public Cruiser(int team, Coordinates location, int direction) {
		super(team, direction, 3, 3, location);
	}

	@Override
	public String getID() {
		return "C" + this.getTeam();
	}
	
	@Override
	public String getActions() {
		return "Choose any of the following actions for the Cruiser:\n1. Move\n2. Turn left\n3. Turn right";
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
			}else {
				returnString += this.turn(1);
			}
		}		
		return returnString;
	}
	
}
