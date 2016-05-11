package Tiler.Helper;

import java.util.ArrayList;
import java.util.Random;

import Tiler.Objects.Room;
import Tiler.Objects.Square;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RoomGenerator {

	/*
	 * This class is dedicated to Procedurally Generating Rooms.
	 * 
	 * @ TODO - Refactor - Split into cleaner methods
	 */

	private Square[][] Nodes;
	private ArrayList<Room> Rooms;
	private TunnelPathFinder TunnelDigger;

	// The Max Number without going outside of the grid and throwing an error.
	private int MaxHeight;
	private int MaxWidth;

	public RoomGenerator(Square[][] Nodes) {

		this.Nodes = Nodes;

		// A rectangle is used so that its easier to check for collisions.
		Rooms = new ArrayList<Room>();

		MaxHeight = Nodes[Nodes.length - 1].length - 1;
		MaxWidth = Nodes.length - 1;

		TunnelDigger = new TunnelPathFinder(Nodes);

	}

	public void Generate() {

		// This method generates the rooms.
		GenerateRooms();

		// Makes paths between rooms
		TunnelPathing();

		// Takes the rooms and applies them to the grid.
		TranslatetoGrid();
		System.out.println(Rooms.size());

	}

	public void GenerateRooms() {
		/*
		 * Try to generate a Random room within the limits of the Nodes Grid.
		 * 
		 * - Needs to be able to test if a room is overlapping another - If theres space between rooms. - Maximum size
		 * of the rooms.
		 */

		Random rand = new Random();

		int MaxRoomSize = 10;
		int MinRoomSize = 5;

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
				Room Temp = new Room(new Rectangle(RandomX - 1, RandomY - 1, RandomWidth + 2, RandomHeight + 2));

				/*
				 * This For loop test got kind of tricky. You cannot simply add the room to the ArrayList if it doesnt
				 * overlap. If you do it will add and try to test against itself to remove itself throwing an error.
				 */

				boolean IsOverlapping = false;

				for (Room room : Rooms) {

					// If the room overlaps at all, it sets the boolean as True.
					// Discarding it from being added to the ArrayList
					if (Temp.getRectangle().overlaps(room.getRectangle())) {
						IsOverlapping = true;

					}
				}

				// If the Room didn't end up overlapping add it to the ArrayList
				if (IsOverlapping == false) {
					Rooms.add(new Room(new Rectangle(RandomX, RandomY, RandomWidth, RandomHeight)));

				}

			} else {
				Trying++;
			}

		}

	}

	public void TunnelPathing() {

		// Dig path between rooms.

		// Set the main room.
		// Dig path between rooms.

		// Flawed System Currently:
		// Bug: Sometimes a room wont be connected. It seems to be a problem with the index number.

		for (int RoomNum = 0; RoomNum < Rooms.size() - 1; RoomNum++) {

			System.out.println(RoomNum + " Max:" + (Rooms.size() - 1));

			Room Temp = Rooms.get(RoomNum);
			Room Temp2 = Rooms.get(RoomNum + 1);

			// Calculate Middle Point.
			Vector2 MidOrig = Temp.CenterPoint();

			Square Original = Nodes[(int) MidOrig.x][(int) MidOrig.y];

			Square NextRoom = Nodes[(int) Temp2.CenterPoint().x][(int) Temp2.CenterPoint().y];

			if (!(Original.getRectangle().x == NextRoom.getRectangle().x)
					&& !(Original.getRectangle().y == NextRoom.getRectangle().y)) {

				TunnelDigger.StartPathing(Original, NextRoom);
				System.out.println(Original.getRectangle() + "Linked to " + NextRoom.getRectangle());
				Temp.SetConnectedtoMain();
				Temp2.SetConnectedtoMain();
			}
		}

		System.out.println(".");
		for (Room test : Rooms) {

			if (!test.isConnected()) {
				System.out.println("Bug: Not connected");
			}
		}

	}

	public void TranslatetoGrid() {

		// Properly set the rooms in the Grid to appear.
		// Set the walls and floors of each of the squares within a room.

		for (Room RoomTest : Rooms) {

			// Set the outside of the room as blocked
			for (int X = (int) RoomTest.getRectangle().getX(); X < (RoomTest.getRectangle().getX() + RoomTest
					.getRectangle().getWidth()); X++) {
				for (int Y = (int) RoomTest.getRectangle().getY(); Y < (RoomTest.getRectangle().getY() + RoomTest
						.getRectangle().getHeight()); Y++) {

					if (!Nodes[X][Y].isFloor()) {
						Nodes[X][Y].setBlocked();
					}

				}

			}

			// Set the inside of the rooms as floors

			for (int X = (int) RoomTest.getRectangle().getX() + 1; X < (RoomTest.getRectangle().getX()
					+ RoomTest.getRectangle().getWidth() - 1); X++) {
				for (int Y = (int) RoomTest.getRectangle().getY() + 1; Y < (RoomTest.getRectangle().getY() + RoomTest
						.getRectangle().getHeight()) - 1; Y++) {

					Nodes[X][Y].setFloor();

				}

			}

		}

	}

}
