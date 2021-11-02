package packageA;

public class ScoutBoat extends Boat{
	public ScoutBoat(int team, int direction, int health, int vision, Coordinates location) {
		super(team, direction, health, 1, vision, location);
	}

	/*may have to write some more code to update the actual health?
	 * 
	 * */ 
	 
	
	public String takeHit(int numAttacks) {
		if(Math.random() > .75) {
			return this.getID() + "has avoided the attack!";
		}else {
			return this.getID() + "takes " + numAttacks + " damage.";
		}
	}

	@Override
	public String getID() {
		return null; //what
	}

	@Override
	public String act(int[] intArray, World w) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
