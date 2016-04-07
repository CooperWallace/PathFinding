package Tiler.Helper;

import Tiler.Objects.Square;

import com.badlogic.gdx.graphics.Color;

public class TileEditor {

	/* This class is going to dedicated to doing the following
	*	- [ ] Changing Grid Square types real time rather than hardcoding
	*	- [ ] Live Position Indicatior, which can change the type of square with a button press.
	*	- [ ] Rerunning the Pathing Algorithmto update after the edits
	*/
	
	private Square[][] Nodes;
	private Square PositionalIndicator;
	private Color IndicatorOverlay;
	
	public TileEditor(Square[][] NodesGrid){
		Nodes = NodesGrid;
		
		IndicatorOverlay = new Color(Color.CYAN);
		
		PositionalIndicator = Nodes[0][0];
		
		
	}
	
	
	
	
	
	public Square getPositionalIndicator(){
		return PositionalIndicator;
	}
	
	public Color returnColorPosIndicator(){
		return IndicatorOverlay;
	}
	
	
	
}
