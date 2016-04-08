package Tiler.Helper;

import javax.annotation.PostConstruct;

import Tiler.Objects.Square;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class TileEditor {

	/*
	 * This class is going to dedicated to doing the following - [ ] Changing
	 * Grid Square types real time rather than hardcoding - [ ] Live Position
	 * Indicatior, which can change the type of square with a button press. - [
	 * ] Rerunning the Pathing Algorithmto update after the edits
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

	public void UpdateInputMovements() {

		// Update the movements of the Indicator

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			if (IndicatorRawPosition.y < Nodes[Nodes.length - 1].length - 1) {
				IndicatorRawPosition.y++;
			}
		}

		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			if (IndicatorRawPosition.y > 0) {
				IndicatorRawPosition.y--;
			}

		}
		
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			if(IndicatorRawPosition.x < Nodes.length-1){
				IndicatorRawPosition.x++;
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			if(IndicatorRawPosition.x > 0){
				IndicatorRawPosition.x--;
			}
			
		}
		
		// Update the Indicator to the changed position.
		PositionalIndicator = Nodes[(int) IndicatorRawPosition.x][(int) IndicatorRawPosition.y];
		
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			Nodes[(int) IndicatorRawPosition.x][(int) IndicatorRawPosition.y].setBlocked();
		}
		
		if(Gdx.input.isKeyPressed(Keys.E)){
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
