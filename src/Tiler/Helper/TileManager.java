package Tiler.Helper;

import Tiler.Objects.Square;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class TileManager {
	private Square[][] Nodes;
	private PathFinder PathFinding;
	private RoomGenerator RoomGen;

	// Edit this to change block sizes
	public static final int Blocks_HeightandWidth = 16;

	/**
	 * This class is used to manage the Tiles. Anything regarding tile movement should be in this class.
	 * 
	 */

	public TileManager() {

		int Width = (Gdx.graphics.getWidth() / Blocks_HeightandWidth) - 2;

		int Height = (Gdx.graphics.getHeight() / Blocks_HeightandWidth) - 2;

		// Builds the tiles to match the screen width with 1x1 block border
		Nodes = new Square[Width][Height];

		buildTiles();

		RoomGen = new RoomGenerator(Nodes);
		RoomGen.Generate();
	}

	public void buildTiles() {

		// Build the 2D Array
		// Edit Constant to make blocks different size
		int WidthandHeight = Blocks_HeightandWidth;

		// This method is dedicated to Initializing all of the squares in the
		// grid.

		for (int i = 0; i < Nodes.length; i++) {

			for (int B = 0; B < Nodes[i].length; B++) {
				// Both the X and Y have 64 added in for the offset on the side
				// of the screen.

				Nodes[i][B] = new Square(((i) * WidthandHeight) + WidthandHeight, ((B) * WidthandHeight)
						+ WidthandHeight, WidthandHeight, WidthandHeight);
			}
		}

	}

	public void render(ShapeRenderer shape) {

		// Renders both the Rectangle and the lines
		for (int i = 0; i < Nodes.length; i++) {

			for (int B = 0; B < Nodes[i].length; B++) {
				shape.begin(ShapeType.Filled);
				shape.setColor(Nodes[i][B].getColor());
				shape.rect(Nodes[i][B].getX(), Nodes[i][B].getY(), Nodes[i][B].getWidth(), Nodes[i][B].getHeight());
				shape.end();

				shape.begin(ShapeType.Line);
				shape.setColor(Color.BLACK);
				shape.rect(Nodes[i][B].getX(), Nodes[i][B].getY(), Nodes[i][B].getWidth(), Nodes[i][B].getHeight());
				shape.end();
			}
		}

	}

	public void RenderPosText(SpriteBatch batch) {

		/*
		 * This method is only really ment to be used for debugging purposes
		 */

		batch.begin();
		// Renders stuff like position text

		for (int i = 0; i < Nodes.length; i++) {

			// String is +1 to account for offset of For statement

			for (int B = 0; B < Nodes[i].length; B++) {
				// AssetLoader.Font.draw(batch, "Y: " + B , Nodes[i][B].getX() + 12, Nodes[i][B].getY() + 15);
				// AssetLoader.Font.draw(batch, "X: " + i, Nodes[i][B].getX() + 12, Nodes[i][B].getY() + 30);

			}
		}

		batch.end();

	}

	public void UpdatePathing() {
		PathFinding.UpdatePathfinder();
	}

	public int getBlock_HeightandWidth() {
		return Blocks_HeightandWidth;
	}

	public Square[][] getGrid() {

		return Nodes;
	}

}
