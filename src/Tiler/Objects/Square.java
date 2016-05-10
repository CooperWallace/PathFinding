package Tiler.Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class Square {

	private Rectangle SquareRect;
	private Square Parent;

	private enum SquareType {
		Closed, Open, Start, Blocked, Tracing, Floor;
	}

	private SquareType SqTy;

	/**
	 * 
	 * @param x, y, height, width - All of these are used to set the
	 *            Rectangle so that the Square may be rendered.
	 * 
	 * @param CostofMove
	 * 			The calculated Heuristic cost from the block to the end point.
	 */

	public Square(int x, int y, int height, int width) {
		SquareRect = new Rectangle(x, y, width, height);
		SqTy = SquareType.Open;

	}

	// Parent Methods for pathfinding.
	public void setParent(Square Parent) {
		this.Parent = Parent;
	}


	public Square getParent() {
		return Parent;
	}

	public void KillParentLink() {
		Parent = null;
	}

	public boolean hasParent() {

		if (Parent == null) {
			return false;
		}

		return true;

	}

	// Set Square Types
	public void setClosed() {
		SqTy = SquareType.Closed;
	}

	public void setOpen() {
		SqTy = SquareType.Open;
	}

	public void setBlocked() {
		SqTy = SquareType.Blocked;
	}
	
	public void setStart() {
		SqTy = SquareType.Start;
	}
	
	public void setFloor(){
		SqTy = SquareType.Floor;
	}

	public void setTracing() {
		SqTy = SquareType.Tracing;
	}

	/* Getter Classes */
	public Color getColor() {
		
		if (SqTy == SquareType.Closed) {
			return Color.LIGHT_GRAY;
		} else if (SqTy == SquareType.Open) {
			return Color.LIGHT_GRAY;
		} else if (SqTy == SquareType.Start) {
			return Color.GREEN;
		} else if (SqTy == SquareType.Blocked) {
			return Color.YELLOW;
		} else if (SqTy == SquareType.Tracing) {
			return Color.RED;
		}else if(SqTy == SquareType.Floor){
			return Color.DARK_GRAY;
			
		}
		else {
			return Color.BLACK;
		}

	}

	public boolean isOpen() {
		if (SqTy == SquareType.Open) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isBlocked() {
		if (SqTy == SquareType.Blocked) {
			return true;
		}

		return false;
	}
	
	
	public boolean isFloor(){
		if(SqTy == SquareType.Floor){
			return true;
		}
		
		return false;
	}

	public Rectangle getRectangle() {
		return SquareRect;
	}

	public float getX() {
		return SquareRect.x;
	}

	public float getY() {
		return SquareRect.y;
	}

	public float getWidth() {
		return SquareRect.width;
	}

	public float getHeight() {
		return SquareRect.height;
	}

}
