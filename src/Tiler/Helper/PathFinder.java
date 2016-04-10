package Tiler.Helper;

import java.util.LinkedList;
import java.util.Queue;

import Tiler.Objects.Square;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PathFinder {

	private Square[][] Nodes;
	private Square Start;
	private Square End;

	/**
	 * This class is dedicated to path finding. Anything related to finding the
	 * shortest path should be in this file.
	 * 
	 * @param Nodes
	 * 
	 * 
	 * @TODO 
	 * - Heuristics
	 */

	public PathFinder(Square[][] Nodes) {
		this.Nodes = Nodes;

	}

	public void StartPathing(Square Start, Square End) {
		this.Start = Start;
		this.End = End;
		StartFrontier();

		Retrace(End);

	}

	/**
	 * This Method is dedicated to scanning the grid until it finds a /
	 * *suitable path between the starting point and the end point.
	 */
	public void StartFrontier() {

		System.out.println("Start Frontier");

		// A debugging feature to see how many times the queue is run, and
		// essentially how many blocks were searched.
		// If it's over the H*W number then it is inefficient and searching
		// blocks more than once.
		int counter = 0;

		Queue<Square> queue = new LinkedList<Square>();

		// queue needs to be cleared, if the pathing exits early it can leave
		// some squares
		// making a problrm if its rerun.
		queue.clear();

		// Add the starting node to the front of the queue, this is the starting
		// point that the path is found from.
		queue.add(Start);
		Start.setCost(0);

		while (!queue.isEmpty()) {
			
			// Peeks and gets the first Node from the queue
			Square current = queue.peek();
			
			
			// Sets a base Lowest cost based on the first node in the queue
			int LowestHeuristic = current.getCost(); 
			
			System.out.println("Number of Possible squares " + queue.size());
			
			//Loops through all current Square objects in the queue.
			for(Square TestHeuristicValue : queue){
				System.out.println(TestHeuristicValue.getCost());
				
				// If the cost of the Square object is lower than the 
				if(LowestHeuristic >= TestHeuristicValue.getCost() && TestHeuristicValue.isOpen()){
					
					current = TestHeuristicValue;
				}
				
			}
			System.out.println("Chosen " + current.getCost());
			
			
			queue.remove(current);
			
			
			current.setClosed();
			counter++;

			int X = (int) (current.getX() / TileManager.Blocks_HeightandWidth) - 1;
			int Y = (int) (current.getY() / TileManager.Blocks_HeightandWidth) - 1;

			if (current == End) {
				break;
			}

			// Get Nodes to the Right
			if (X + 1 < Nodes.length) {
				
				
				
				if (Nodes[X + 1][Y].isOpen()) {
					queue.add(Nodes[X + 1][Y]);

					// The Node needs to have a parent so that we can retrace
					// the Nodes to make a path.
					Nodes[X + 1][Y].setParent(current);

					// The Original value needs to be set closed, when the Queue
					// Square object is set closed, it doesn't change the value
					// of the original value
					Nodes[X + 1][Y].setClosed();
					
					
					Nodes[X+1][Y].setCost(CalculateHeuristicsCost(Nodes[X+1][Y]));
				}

			}

			// Get Nodes on Top
			if (Y + 1 < Nodes[Nodes.length - 1].length) {
				if (Nodes[X][Y + 1].isOpen()) {
					queue.add(Nodes[X][Y + 1]);
					Nodes[X][Y + 1].setClosed();

					Nodes[X][Y + 1].setParent(current);

					Nodes[X][Y+1].setCost(CalculateHeuristicsCost(Nodes[X][Y+1]));

				}

			}

			// Get Nodes on Bottom
			if ((Y - 1) >= 0) {
				if (Nodes[X][Y - 1].isOpen()) {
					queue.add(Nodes[X][Y - 1]);

					Nodes[X][Y - 1].setParent(current);
					Nodes[X][Y - 1].setClosed();


					Nodes[X][Y-1].setCost(CalculateHeuristicsCost(Nodes[X][Y-1]));
				}

			}

			// Get Nodes to the Left
			if ((X - 1) >= 0) {
				if (Nodes[X - 1][Y].isOpen()) {
					queue.add(Nodes[X - 1][Y]);

					Nodes[X - 1][Y].setParent(current);
					Nodes[X - 1][Y].setClosed();


				}
			}

			// Diagonial support.
			// X and Y both need to have a + or - value to them to check for the
			// Squares diagionally

			
			/*
			// Top Right
			if ((((X + 1) < Nodes.length) && (Y + 1 < Nodes[Nodes.length - 1].length))) {

				if (Nodes[X + 1][Y + 1].isOpen()) {
					queue.add(Nodes[X + 1][Y + 1]);

					Nodes[X + 1][Y + 1].setParent(current);
					Nodes[X + 1][Y + 1].setClosed();

				}

			}

			// Top Left

			if ((((X - 1) >= 0) && (Y + 1 < Nodes[Nodes.length - 1].length))) {

				if (Nodes[X - 1][Y + 1].isOpen()) {
					queue.add(Nodes[X - 1][Y + 1]);

					Nodes[X - 1][Y + 1].setParent(current);
					Nodes[X - 1][Y + 1].setClosed();

				}

			}

			// Bottom Right

			if ((X + 1 < Nodes.length) && ((Y - 1) >= 0)) {

				if (Nodes[X + 1][Y - 1].isOpen()) {
					queue.add(Nodes[X + 1][Y - 1]);

					Nodes[X + 1][Y - 1].setParent(current);
					Nodes[X + 1][Y - 1].setClosed();

				}

			}

			// Bottom Left

			if (((X - 1) >= 0) && ((Y - 1) >= 0)) {

				if (Nodes[X - 1][Y - 1].isOpen()) {
					queue.add(Nodes[X - 1][Y - 1]);

					Nodes[X - 1][Y - 1].setParent(current);
					Nodes[X - 1][Y - 1].setClosed();

				}

			}*/

		}

		System.out.println("End with " + counter);

	}

	/**
	 * This method is dedicated to retracing the route found and displaying the
	 * route taken in a graphical form.
	 * 
	 * @param ENDPOINT
	 *            In order to retrace the route found in StartFrontier
	 */
	public void Retrace(Square ENDPOINT) {
		// This retraces from the EndPoint because it has the parent path to the
		// starting point. It makes the block path red showing which way to get
		// there.

		// Uses the Endpoint because it has the parent to retrace leading to the
		// beginning.
		Square Current = ENDPOINT;

		System.out.println("Retrace Started");

		// Enters a loop to retrace the path to the beginning
		while (Current.hasParent()) {

			// Set the path red
			Current.setTracing();
			// set the parent to be sure that it was set originally.
			Current.getParent().setTracing();

			// Set to the current so that it can loop and find the start.
			Current = Current.getParent();

		}
		
		ENDPOINT.setStart();
		
		System.out.println("Stopped");

	}

	/**
	 * This method is dedicated to rerunning the Algorithm and setting the
	 * searched nodes to unsearched to allow it to be rerun without problems.
	 * 
	 */
	public void UpdatePathfinder() {

		// This is needed to set all of the unblocked blocks to open so that
		// they can be researched. If they aren't reset to nothing then the
		// program will retrace it from the existing parents. Which can make it
		// go through blocked blocks.

		for (int i = 0; i < Nodes.length; i++) {

			for (int n = 0; n < Nodes[Nodes.length - 1].length; n++) {
				if (!(Nodes[i][n].isBlocked() && !Nodes[i][n].isOpen())) {
					// Sets the Node open so that it can be searched again
					Nodes[i][n].setOpen();
					// Kills the Parent link so that the Frontier can reset it
					// to a new value.
					Nodes[i][n].KillParentLink();
					
					Nodes[i][n].setCost(0);
				}

			}
		}

		StartFrontier();
		Retrace(End);

	}

	public int CalculateHeuristicsCost(Square Evalutation){
		/*	This method is dedicated to finding the Heuristic value from the Current object to the End Object. 
		 * 		Basically calculated the Distance from Current to the End Object.
		 * 
		 *  		Needs to use the Node position rather than the Raw positional value!
		 *  		Raw being .GetY and .GetX
		 *
		 *			Math :   return abs(a.x - b.x) + abs(a.y - b.y)
		 **/

		int EvaluationX = (int) (Evalutation.getX() / TileManager.Blocks_HeightandWidth) - 1;
		int EvaluationY = (int) (Evalutation.getY() / TileManager.Blocks_HeightandWidth) - 1;

		int EndX = (int) (End.getX() / TileManager.Blocks_HeightandWidth) - 1;
		int EndY = (int) (End.getY() / TileManager.Blocks_HeightandWidth) - 1;
		float TotalCost = Math.abs(EvaluationX - EndX) + Math.abs(EvaluationY - EndY);
		
		return (int)TotalCost;
	}
	
	
	public boolean ifExists(Square Test, int I) {
		if ((Test.getX() / 64 - 1) <= Nodes[Nodes.length - 1].length) {
			if ((Test.getY() / 64 - 1) - 1 <= Nodes.length) {

				return true;
			}
		}

		return false;

	}

	public void DebugFont(SpriteBatch batch) {

		// This outputs the Position of each block X: Y:.
		// For Debugging

		AssetLoader.Font.draw(batch, Nodes.length + ""
				+ Nodes[Nodes.length - 1].length, 50, 50);

	}

}
