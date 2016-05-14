package Tiler.Helper;

import Tiler.Objects.Room;
import Tiler.Objects.Square;

public class TunnelPathFinder extends PathFinder {

	public TunnelPathFinder(Square[][] Nodes) {
		super(Nodes);
		// TODO Auto-generated constructor stub
	}

	public void PathBetweenRooms(Room Start, Room End){


		Square StartSq = Nodes[(int) Start.CenterPoint().x][(int) Start.CenterPoint().y];

		Square EndSq = Nodes[(int) End.CenterPoint().x][(int) End.CenterPoint().y];
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
