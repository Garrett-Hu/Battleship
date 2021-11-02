package packageA;

public class BoatRunner extends Boat{
    public BoatRunner(int team, int direction, int health, int strength, int vision, Coordinates location) {
        super(team, direction, health, strength, vision, location);
    }
    
    public String getID() {
        return "B";
    }
    
    public String act(int[] choices, World world){
        return "Not implemented in Part A";
    }
    
    public String getActions(){
        return "Not implemented in Part A";
    }
    
    public static void main(String[] args) {
        Coordinates c = new Coordinates(2, 3);
        Coordinates d = new Coordinates();
        if (d.getX() == 0 && d.getY() == 0)
        	System.out.println("Coordinates default to 0");  
        World w = new World(10, 10); //should default to minimum of x = 4
        if (w.getHeight() == 5)
            System.out.println("World height is correct");
        if (!w.isLocationValid(new Coordinates(2,6)))
            System.out.println("Marks an incorrect location");
/*        BoatRunner a = new BoatRunner(1, 0, 9, 5, 6, new Coordinates(0,0));
        if (w.setOccupant(a, new Coordinates(0,0)))
            System.out.println("Set a boat at (0,0)");
*/
        Coordinates e = w.getAdjacentLocation(new Coordinates(0,0), World.southeast);
        System.out.println(e);
        
        BoatRunner[] barray = new BoatRunner[4];
        //team, direction, health, strength, vision, location
        barray[0] = new BoatRunner(1, 2, 9, 5, 1, new Coordinates(3,3));
        barray[1] = new BoatRunner(2, 3, 9, 5, 1, new Coordinates(2,2));
        barray[2] = new BoatRunner(3, 5, 9, 5, 1, new Coordinates(1,2));
        barray[3] = new BoatRunner(3, 5, 9, 5, 1, new Coordinates(6,7));
        String map1 = w.drawTeamMap(barray, 2);
        System.out.println(map1);
    }

}
