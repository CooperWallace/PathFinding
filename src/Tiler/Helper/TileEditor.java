package Tiler.Helper;

import Tiler.Objects.Square;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class TileEditor {

	/*
	 * TODO This class is going to dedicated to making a live positional editor'
	 * It'll be used to change the map real time and test the path finding
	 * abilities.
	 */

	private Square[][] Nodes;
	private Square PositionalIndicator;
	private Color IndicatorOverlay;

	private Rectangle IndicatorRawPosition;

	public TileEditor(Square[][] NodesGrid) {
		// Object Initializations
		Nodes = NodesGrid;

		IndicatorOverlay = new Color(Color.CYAN);
		IndicatorRawPosition = new Rectangle(0, 0, 1, 1);

		PositionalIndicator = Nodes[0][0];

		// Sets the Indicator position to the Raw Grid Array Index Number.

		IndicatorRawPosition.x = (PositionalIndicator.getX() / TileManager.Blocks_HeightandWidth) - 1;
		IndicatorRawPosition.y = (PositionalIndicator.getY() / TileManager.Blocks_HeightandWidth) - 1;
	}

	/**
	 * Dedicated to updating the Indicator Movements when a input is detected
	 */
	public void UpdateInputMovements(OrthographicCamera cam) {

		// This class changes the position of the positon.
		// It checks to make sure that the move is within range.

		/*
		 * Base for mouse controls.
		 * 
		 * 1. Get the Mouse Input and subtract the left over from dividing by
		 * the blocks. Ex. 200 / 64 would be 3.125, Using % would calculate the
		 * .125 which would be 8 extra pixels 2. Divide by the Blocks to get the
		 * Array Position. Ex 200%64 = 8. (200-8)/64 would be 3. 3. Set the X
		 * equal to MouseX given its in range.
		 */

		/*
		 * ============ Bug ================ When the keyboard and mouse is
		 * being used the keyboard wont move when the mouse is taken off screen.
		 * 
		 * Possible fix: Toggle between keyboard and cursor.
		 */

		// Get the Raw value to the window. If you use only GetX or Y, then it
		// takes the position of the entire screen. Not the position within the
		// game screen
		Vector3 MousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		cam.unproject(MousePos);

		int MouseX = (int) ((MousePos.x / TileManager.Blocks_HeightandWidth));
		if (MouseX > 0 && MouseX <= Nodes.length) {
			// Needs 1 offset to line up mouse. Take away 1 and it lines up
			// properly
			IndicatorRawPosition.x = MouseX - 1;

		}
		/*
		 * // Had a problem with using the divison remainder on the GetY method.
		 * Messed around the removing it seems to work perfectly fine.
		 */
		int MouseY = (((Gdx.graphics.getHeight() - Gdx.input.getY())) / TileManager.Blocks_HeightandWidth);
		if (MouseY > 0 && MouseY <= Nodes[Nodes.length - 1].length) { 
			// Offset for position
			IndicatorRawPosition.y = MouseY - 1;
		}

		/*
		 * Base for Keyboard input.
		 * 
		 * Sees if the movement is valid and moves the Indicator to that
		 * position.
		 */

		// Update the movements of the Indicator with the use of keys.
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			// Checks to see if the desired move location is valid
			if (IndicatorRawPosition.y < Nodes[Nodes.length - 1].length - 1) {
				// If the location is valid, the indicator is set to that
				// position.
				IndicatorRawPosition.y++;
			}
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			if (IndicatorRawPosition.y > 0) {
				IndicatorRawPosition.y--;
			}

		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (IndicatorRawPosition.x < Nodes.length - 1) {
				IndicatorRawPosition.x++;
			}
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			if (IndicatorRawPosition.x > 0) {
				IndicatorRawPosition.x--;
			}

		}

		// Update the Indicator to the changed position.
		PositionalIndicator = Nodes[(int) IndicatorRawPosition.x][(int) IndicatorRawPosition.y];

		// Changing the type of block with a press of a button. Useful for
		// realtime editing.

		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			Nodes[(int) IndicatorRawPosition.x][(int) IndicatorRawPosition.y].setBlocked();
		}

		if (Gdx.input.isKeyPressed(Keys.E)) {
			Nodes[(int) IndicatorRawPosition.x][(int) IndicatorRawPosition.y].setOpen();
		}

	}

	public Square getPositionalIndicator() {
		return PositionalIndicator;
	}

	public Color returnColorPosIndicator() {
		return IndicatorOverlay;
	}

}
