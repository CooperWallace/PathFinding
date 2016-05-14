package Tiler.Objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class Square {

	private Rectangle SquareRect;
	private Square Parent;

	private enum SquareType {
		Unassigned, Start, Blocked, Tracing, Floor;
	}

	private enum SquareStatus {
		Closed, Open;
	}

	// This specific type if needed for the pathfinding to not affect the SquareType. If it's set to either closed or
	// open it'll affect the SquareType. It needs to be an entirely seperate variable to avoid problems.a
	private SquareStatus SquareStat;
	private SquareType SqTy;

	/**
	 * 
	 * @param x
	 *            , y, height, width - All of these are used to set the Rectangle so that the Square may be rendered.
	 */

	public Square(int x, int y, int height, int width) {
		SquareRect = new Rectangle(x, y, width, height);
		SqTy = SquareType.Unassigned;
		SquareStat = SquareStatus.Open;
	}

	public String toString() {
		return "X: " + SquareRect.x + "Y: " + SquareRect.y;
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
		SquareStat = SquareStatus.Closed;
	}

	public void setOpen() {
		SquareStat = SquareStatus.Open;
	}

	public void setBlocked() {
		SqTy = SquareType.Blocked;
	}

	public void setStart() {
		SqTy = SquareType.Start;
	}

	public void setFloor() {
		SqTy = SquareType.Floor;
	}

	public void setTracing() {
		SqTy = SquareType.Tracing;
	}

	/* Getter Classes */
	public Color getColor() {
		switch (SqTy) {
		case Start:
			return Color.GREEN;
		case Blocked:
			return Color.YELLOW;
		case Tracing:
			return Color.RED;
		case Floor:
			return Color.DARK_GRAY;
		default:
			return Color.LIGHT_GRAY;

		}

	}

	public boolean isOpen() {
		if (SquareStat == SquareStatus.Open) {
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

	public boolean isFloor() {
		if (SqTy == SquareType.Floor) {
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
