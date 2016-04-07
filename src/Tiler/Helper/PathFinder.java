package Tiler.Helper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;

import Tiler.Objects.Square;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PathFinder {

	private Square[][] Nodes;
	private HashMap<Square, Square> CurrentTo;

	public PathFinder(Square[][] Nodes) {
		this.Nodes = Nodes;

	}

	public void StartPathing(Square Start, Square End) {
		StartFrontier(Start, End);

		Retrace(End);

	}

	public void StartFrontier(Square Start, Square End) {
		System.out.println("Start Frontier");
		int counter = 0;
		Queue<Square> queue = new LinkedList<Square>();
		queue.add(Start);

		while (!queue.isEmpty()) {

			Square current = queue.poll();
			current.setClosed();
			counter++;

			int X = (current.getX() / TileManager.Blocks_HeightandWidth) - 1;
			int Y = (current.getY() / TileManager.Blocks_HeightandWidth) - 1;

			System.out.printf("%3d Setting Closed X: %d and Y: %d %n", counter,
					X, Y);

			if (current == End) {
				break;
			}

			// Get Nodes to the Right
			if (X + 1 < Nodes.length) {
				if (Nodes[X + 1][Y].isOpen()) {
					queue.add(Nodes[X + 1][Y]);

					Nodes[X + 1][Y].setParent(current);

					// The Original value needs to be set closed, when the Queue
					// Square object is set closed, it doesn't change the value
					// of the original value
					Nodes[X + 1][Y].setClosed();
					System.out.printf("Adding RIGHT X: %d Y: %d %n", X + 1, Y);

				}

			}

			// Get Nodes on Top
			if (Y + 1 < Nodes[Nodes.length - 1].length) {
				if (Nodes[X][Y + 1].isOpen()) {
					queue.add(Nodes[X][Y + 1]);
					Nodes[X][Y + 1].setClosed();

					Nodes[X][Y + 1].setParent(current);

					System.out.printf("Adding TOP X: %d Y: %d %n", X, Y + 1);

				}

			}

			// Get Nodes on Bottom
			if ((Y - 1) >= 0) {
				if (Nodes[X][Y - 1].isOpen()) {
					queue.add(Nodes[X][Y - 1]);

					Nodes[X][Y - 1].setParent(current);
					Nodes[X][Y - 1].setClosed();

					System.out.printf("Adding TOP X: %d Y: %d %n", X, Y - 1);
				}

			}

			// Get Nodes to the Left
			if ((X - 1) >= 0) {
				if (Nodes[X - 1][Y].isOpen()) {
					queue.add(Nodes[X - 1][Y]);

					Nodes[X - 1][Y].setParent(current);
					Nodes[X - 1][Y].setClosed();

					System.out.printf("Adding TOP X: %d Y: %d %n", X - 1, Y);

				}
			}

			// Diagonial support.

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
					queue.add(Nodes[X + 1][Y]);

					Nodes[X + 1][Y - 1].setParent(current);
					Nodes[X + 1][Y - 1].setClosed();

				}

			}

			// Bottom Left

			if (((X - 1) >= 0) && ((Y - 1) >= 0)) {

				if (Nodes[X - 1][Y - 1].isOpen()) {
					queue.add(Nodes[X - 1][Y-1]);

					Nodes[X - 1][Y - 1].setParent(current);
					Nodes[X - 1][Y - 1].setClosed();

				}

			}

			System.out.println(" -- ");

		}

		System.out.println("End with " + counter);

	}

	public void Retrace(Square ENDPOINT) {
		Square Current = ENDPOINT;
		ENDPOINT.setTracing();
		Square Temp = Current.getParent();

		System.out.println("Retrace Started");
		while (Current.hasParent()) {

			Current.setTracing();
			Current.getParent().setTracing();
			Current = Current.getParent();

		}
		System.out.println("Stopped");

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

		AssetLoader.Font.draw(batch, Nodes.length + ""
				+ Nodes[Nodes.length - 1].length, 50, 50);

	}

}
