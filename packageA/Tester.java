package packageA;

import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		World w1 = new World(10, 10);
		int view = 1;
		Boat[] bArr = new Boat[3];
		bArr[0] = new Submarine(1, new Coordinates(4,4), 3);
		bArr[1] = new AircraftCarrier(2,new Coordinates(5,5), 1);
		bArr[2] = new Battleship(3, new Coordinates(7, 7), 5);
		String map = w1.drawTeamMap(bArr, 1);
		//System.out.println(map);
		
		System.out.println("Which view? (pick 1)");
		view = in.nextInt();
		map = w1.drawTeamMap(bArr, view);
		System.out.println(map);
		
		System.out.println("Which view? (pick 2)");
		view = in.nextInt();
		map = w1.drawTeamMap(bArr, view);
		System.out.println(map);
		
		System.out.println("Which view? (pick 3)");
		view = in.nextInt();
		map = w1.drawTeamMap(bArr, view);
		System.out.println(map);
		
		int[] submarineActions = new int[1]; //im only going to have it at 1 for simplicity's sake
		for(int i = 0; i < submarineActions.length; i++) {
			System.out.println(bArr[0].getActions());
			submarineActions[i] = in.nextInt();
			bArr[0].act(submarineActions, w1);
		}
		
		System.out.println(map);
		/*
		System.out.println(bArr[1].takeHit(1));
		//System.out.println(map);
		
		for (Boat b: bArr) {
			w1.setOccupant(b, b.getLocation());
		}
		for (Boat b: bArr) {
			if (b instanceof Attacker) {
				System.out.println(((Attacker)b).attack(w1));
			}
		}
		bArr[2].move(w1);
		map = w1.drawTeamMap(bArr, 3);
		System.out.println(map);
		
		*/

	}

}


