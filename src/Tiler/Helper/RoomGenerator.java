package Tiler.Helper;

import java.util.ArrayList;
import java.util.Random;

import Tiler.Objects.Square;

import com.badlogic.gdx.math.Rectangle;

public class RoomGenerator {

	/*
	 * This class is dedicated to Procedurally Generating Rooms.
	 * 
	 * @ TODO
	 */

	private Square[][] Nodes;
	private ArrayList<Rectangle> Rooms;
	private TunnelPathFinder TunnelDigger;

	// The Max Number without going outside of the grid and throwing an error.
	private int MaxHeight;
	private int MaxWidth;

	public RoomGenerator(Square[][] Nodes) {

		this.Nodes = Nodes;

		// A rectangle is used so that its easier to check for collisions.
		Rooms = new ArrayList<Rectangle>();

		MaxHeight = Nodes[Nodes.length - 1].length - 1;
		MaxWidth = Nodes.length - 1;
		
		TunnelDigger = new TunnelPathFinder(Nodes);

	}

	public void Generate() {
		/*
		 * Try to generate a Random room within the limits of the Nodes Grid.
		 * 
		 * - Needs to be able to test if a room is overlapping another 
		 * - If theres space between rooms. 
		 * - Maximum size
		 * of the rooms.
		 */

		Random rand = new Random();

		int MaxRoomSize = 20;
		int MinRoomSize = 10;

		/*
		 * This trying loop keeps on repeating until a certain threshold is reached where there are no more possible
		 * rooms to add within the restrictions.
		 */

		int Trying = 0;

		while (Trying < 100) {
			// Randomly Generates X,Y Position, and the Size of then room.
			int RandomHeight = rand.nextInt(MaxRoomSize);
			int RandomWidth = rand.nextInt(MaxRoomSize);

			int RandomX = rand.nextInt(MaxWidth - RandomWidth);
			int RandomY = rand.nextInt(MaxHeight - RandomHeight);

			// Contiunes to generate until suitable size is found that is within
			// the Maximum and Minimum value
			while (!(RandomHeight >= MinRoomSize && RandomHeight <= MaxRoomSize)) {
				RandomHeight = rand.nextInt(MaxRoomSize);
			}

			// Contiunes to generate until suitable size is found that is within
			// the Maximum and Minimum value
			while (!(RandomWidth >= MinRoomSize && RandomWidth <= MaxRoomSize)) {
				RandomWidth = rand.nextInt(MaxRoomSize);
			}

			// Checks to see if the room is valid and is within the valid range
			// of the Node Array
			if (RandomX < MaxWidth - RandomWidth && RandomY < MaxHeight - RandomHeight) {

				// Create a temporary room to test for overlapping other rooms.
				// This room is bigger by 1 on all sides.
				Rectangle Temp = new Rectangle(RandomX - 1, RandomY - 1, RandomWidth + 2, RandomHeight + 2);

				/*
				 * This For loop test got kind of tricky. You cannot simply add the room to the ArrayList if it doesnt
				 * overlap. If you do it will add and try to test against itself to remove itself throwing an error.
				 */

				boolean IsOverlapping = false;

				for (Rectangle room : Rooms) {

					// If the room overlaps at all, it sets the boolean as True.
					// Discarding it from being added to the ArrayList
					if (Temp.overlaps(room)) {
						IsOverlapping = true;
						

					}
				}

				// If the Room didn't end up overlapping add it to the ArrayList
				if (IsOverlapping == false) {
					Rooms.add(new Rectangle(RandomX, RandomY, RandomWidth, RandomHeight));
					
				}

			} else {
				Trying++;
			}

		}

		
		// Dig path between rooms.
		for(int RoomNum =1; RoomNum < Rooms.size(); RoomNum++){
			
			Rectangle Temp = Rooms.get(RoomNum-1);
			Rectangle Temp2 = Rooms.get(RoomNum);
			
			// Calculate Middle Point.
			int MidX =  Math.round((Temp.getX() + (Temp.getWidth() / 2)));
			int MidY = Math.round((Temp.getY() + (Temp.getHeight() / 2)));
			

			int MidX2 =  Math.round((Temp2.getX() + (Temp2.getWidth() / 2)));
			int MidY2 = Math.round((Temp2.getY() + (Temp2.getHeight() / 2)));
			
			Square Original = Nodes[MidX][MidY];

			Square NextRoom = Nodes[MidX2][MidY2];
			
			TunnelDigger.StartPathing(Original, NextRoom);
			
		}
		
		
		
		
		// Properly set the rooms in the Grid to appear.
		for (Rectangle Room : Rooms) {

			for (int X = (int) Room.getX(); X < (Room.getX() + Room.getWidth()); X++) {
				for (int Y = (int) Room.getY(); Y < (Room.getY() + Room.getHeight()); Y++) {

					if(!Nodes[X][Y].isFloor()){
					Nodes[X][Y].setBlocked();
					}

				}

			}
			
			for (int X = (int) Room.getX()+1; X < (Room.getX() + Room.getWidth()-1); X++) {
				for (int Y = (int) Room.getY()+1; Y < (Room.getY() + Room.getHeight())-1; Y++) {

					Nodes[X][Y].setFloor();

				}

			}
			
			
			
			

		}
		
		
		
		
		
		
		
		

	}

}
