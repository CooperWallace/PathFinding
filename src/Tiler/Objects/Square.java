package Tiler.Objects;

import com.badlogic.gdx.graphics.Color;

public class Square {

	private int x;
	private int y;
	private int height;
	private int width;
	private Square Parent;
	private int CostofMove;

	private enum SquareType {
		Closed, Open,Start, Blocked, Tracing;
	}

	public SquareType SqTy;

	public Square(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		SqTy = SquareType.Open;
		
	}

	
	// Parent Methods for pathfinding.
	public void setParent(Square Parent){
		this.Parent = Parent;
	}
	
	public int getCost(){
		return CostofMove;
		
		
	}
	
	public Square getParent(){
		return Parent;
		
	}
	
	public boolean hasParent(){
		
		if(Parent == null){
			return false;
		}
		
		return true;
		
	}
	
	
	// Set Square Types
	public void setClosed(){
		SqTy = SquareType.Closed;		
	}
	
	public void setOpen(){
		SqTy = SquareType.Open;	
	}
	
	public void setBlocked(){
		SqTy = SquareType.Blocked;
	}
	
	
	public void setTracing(){
		SqTy = SquareType.Tracing;
	}
	
	
	/* Getter Classes */
	public Color getColor() {
		if (SqTy == SquareType.Closed) {
			return Color.BLUE;
		} else if(SqTy == SquareType.Open){
			return Color.LIGHT_GRAY;
		}else if(SqTy == SquareType.Start){
			return Color.GREEN;
		}else if(SqTy == SquareType.Blocked){
			return Color.YELLOW;
			
		}else if(SqTy == SqTy.Tracing){
			return Color.RED;
		}
		else {
			return Color.BLACK;
		}

	}
	public boolean isOpen(){
		if(SqTy == SquareType.Open){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
