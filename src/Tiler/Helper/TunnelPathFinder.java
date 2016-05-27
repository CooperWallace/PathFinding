package Tiler.Helper;

import Tiler.Objects.Room;
import Tiler.Objects.Square;

public class TunnelPathFinder extends PathFinder {

	public TunnelPathFinder(Square[][] Nodes) {
		super(Nodes);
		// TODO Auto-generated constructor stub
	}

	public void PathBetweenRooms(Room Start, Room End) {

		Square StartSq = Nodes[(int) Start.CenterPoint().x][(int) Start.CenterPoint().y];

		Square EndSq = Nodes[(int) End.CenterPoint().x][(int) End.CenterPoint().y];

		int ClosestValue = StartSq.returnDistance(EndSq);

		// Enters a loop which tests every single floor square inside of the Room(1 block left on all sides for the
		// walls.) and finds the one closest to the room it's trying to path to. Makes this easier on the pathing and
		// prevents windy connections.
		for (int X = (int) Start.getRectangle().getX() + 1; X < (Start.getRectangle().getX()
				+ Start.getRectangle().getWidth() - 1); X++) {
			for (int Y = (int) Start.getRectangle().getY() + 1; Y < (Start.getRectangle().getY() + Start.getRectangle()
					.getHeight()) - 1; Y++) {

				if (Nodes[X][Y].returnDistance(EndSq) < ClosestValue) {

					ClosestValue = Nodes[X][Y].returnDistance(EndSq);
					StartSq = Nodes[X][Y];

				}

			}
		}

		// Same loop as above but for the EndSquare instead of the StartSquare

		ClosestValue = EndSq.returnDistance(StartSq);
		for (int X = (int) End.getRectangle().getX() + 1; X < (End.getRectangle().getX()
				+ End.getRectangle().getWidth() - 1); X++) {
			for (int Y = (int) End.getRectangle().getY() + 1; Y < (End.getRectangle().getY() + End.getRectangle()
					.getHeight()) - 1; Y++) {

				if (Nodes[X][Y].returnDistance(StartSq) < ClosestValue) {
					ClosestValue = Nodes[X][Y].returnDistance(StartSq);
					EndSq = Nodes[X][Y];

				}

			}
		}

		UpdatePathfinder();

		StartPathing(StartSq, EndSq);
	}

	@Override
	public void Retrace(Square EndPoint) {

		// This retraces from the EndPoint because it has the parent path to the
		// starting point. It makes the block path red showing which way to get
		// there.

		// Uses the Endpoint because it has the parent to retrace leading to the
		// beginning.
		Square Current = EndPoint;

		// Enters a loop to retrace the path to the beginning
		while (Current.hasParent()) {

			// Set the path red
			Current.setFloor();
			// set the parent to be sure that it was set originally.
			Current.getParent().setFloor();

			// Set to the current so that it can loop and find the start.
			Current = Current.getParent();

		}

	}

}
