package Tiler.Helper;

import Tiler.Objects.Room;
import Tiler.Objects.Square;

public class TunnelPathFinder extends PathFinder {

	public TunnelPathFinder(Square[][] Nodes) {
		super(Nodes);
		// TODO Auto-generated constructor stub
	}

	public void PathBetweenRooms(Room Start, Room End){
		Square StartSq = new Square((int)Start.CenterPoint().x, (int)Start.CenterPoint().y, TileManager.Blocks_HeightandWidth, TileManager.Blocks_HeightandWidth);

		Square EndSq = new Square((int)End.CenterPoint().x, (int)End.CenterPoint().y, TileManager.Blocks_HeightandWidth, TileManager.Blocks_HeightandWidth);
		
		
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
	
	@Override
	public void UpdatePathfinder() {

		// This is needed to set all of the unblocked blocks to open so that
		// they can be researched. If they aren't reset to nothing then the
		// program will retrace it from the existing parents. Which can make it
		// go through blocked blocks.

		for (int i = 0; i < Nodes.length; i++) {

			for (int n = 0; n < Nodes[Nodes.length - 1].length; n++) {
				Nodes[i][n].KillParentLink();

			}
		}


	}

}
