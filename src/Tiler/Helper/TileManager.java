package Tiler.Helper;

import Tiler.Objects.Square;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class TileManager {
	int Width = 10;
	int Height = 10;
	private Square[][] Nodes;
	private PathFinder PathFinding;

	int X = 5;
	int Y = 5;

	// Edit this to change block sizes
	public static final int Blocks_HeightandWidth = 64;

	/**
	 * This class is used to manage the Tiles. Anything regarding tile movement
	 * should be in this class.
	 * 
	 */

	public TileManager() {

		Width = (Gdx.graphics.getWidth() / Blocks_HeightandWidth) - 2;

		Height = (Gdx.graphics.getHeight() / Blocks_HeightandWidth) - 2;


		// Builds the tiles to match the screen width with 1x1 block border
		Nodes = new Square[Width][Height];

		buildTiles();

		PathFinding = new PathFinder(Nodes);
		PathFinding.StartPathing(Nodes[0][Height / 2 - 1],
				Nodes[Width - 1][Height / 2 - 1]);

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

				Nodes[i][B] = new Square(((i) * WidthandHeight)
						+ WidthandHeight, ((B) * WidthandHeight)
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
				shape.rect(Nodes[i][B].getX(), Nodes[i][B].getY(),
						Nodes[i][B].getWidth(), Nodes[i][B].getHeight());
				shape.end();

				shape.begin(ShapeType.Line);
				shape.setColor(Color.BLACK);
				shape.rect(Nodes[i][B].getX(), Nodes[i][B].getY(),
						Nodes[i][B].getWidth(), Nodes[i][B].getHeight());
				shape.end();
			}
		}
	}

	public void RenderPosText(SpriteBatch batch) {

		batch.begin();
		// Renders stuff like position text

		for (int i = 0; i < Nodes.length; i++) {

			// String is +1 to account for offset of For statement

			for (int B = 0; B < Nodes[i].length; B++) {
				AssetLoader.Font.draw(batch, Nodes[i][B].getCost() + "", Nodes[i][B].getX() + 12,
						Nodes[i][B].getY() + 15);

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
