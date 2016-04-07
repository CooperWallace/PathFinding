package Tiler.Helper;

import Tiler.Objects.Square;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class TileManager {
	int Width = 10;
	int Height = 10;
	private Square[][] Nodes;
	private PathFinder AStar;
	
	int X=5; int Y=5;
	
	// Edit this to change block sizes
	public static final int Blocks_HeightandWidth = 16;
	
	//@TODO
	// Add a way to set the blocked, and open blocks in the client itself
	//		-	Like a X mark that can move X and Y and keys are to set different variables.
	// Have Fun!
	
	

	public TileManager() {
		Nodes = new Square[Width][Height];

		buildTiles();
		
		AStar = new PathFinder(Nodes);
		AStar.StartPathing( Nodes[0][0],Nodes[9][9]);
		
		
	}
	
	
	
	public void buildTiles() {

		// Build the 2D Array
		// Edit Constant to make different blocks
		int WidthandHeight = Blocks_HeightandWidth;
		

		for (int i = 0; i < Nodes.length; i++) {

			for (int B = 0; B < Nodes[i].length; B++) {
				// Both the X and Y have 64 added in for the offset on the side
				// of the screen.

				Nodes[i][B] = new Square(((i) * WidthandHeight) + WidthandHeight, ((B) * WidthandHeight) + WidthandHeight, WidthandHeight,
						WidthandHeight);
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
				shape.setColor(Color.RED);
				shape.rect(Nodes[i][B].getX(), Nodes[i][B].getY(),
						Nodes[i][B].getWidth(), Nodes[i][B].getHeight());
				shape.end();
			}
		}

		
		
	}

	public void RenderPosText(SpriteBatch batch) {

		// Renders stuff like position text

		for (int i = 0; i < Nodes.length; i++) {

			// String is +1 to account for offset of For statement

			for (int B = 0; B < Nodes[i].length; B++) {
				AssetLoader.Font.draw(batch, Integer.toString(i + 1),
						Nodes[i][B].getX() + 1, Nodes[i][B].getY() + 20);
				AssetLoader.Font.draw(batch, Integer.toString(B + 1),
						Nodes[i][B].getX() + 1, Nodes[i][B].getY() + 40);

			}
		}

		AStar.DebugFont(batch);
	}
}
