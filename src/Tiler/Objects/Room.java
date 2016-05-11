package Tiler.Objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Room {

	private Rectangle RoomDimensions;
	private boolean isConnected;

	private Room ParentRoom;

	// Need to use an Array for Child rooms if there is multiple. 
	// This variable is going to be used to see if the room
	// is a mid room or a last room.
	private Room ChildRoom;

	public Room(Rectangle RoomDimensions) {
		this.RoomDimensions = RoomDimensions;

		// If the room is connected to the Entrance/Main area
		isConnected = false;
	}

	// SETTER METHODS
	public void setParent(Room ParentRoom) {
		this.ParentRoom = ParentRoom;
	}

	public void SetConnectedtoMain() {
		isConnected = true;
	}

	// GETTER METHODS
	public boolean hasParent() {
		if (ParentRoom == null) {
			return false;
		}
		return true;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public Rectangle getRectangle() {
		return RoomDimensions;
	}
	
	public Room returnParent(){
		return ParentRoom;
	}

	// Returns the center point in a 2D Vector.
	public Vector2 CenterPoint() {
		Vector2 MainPoint = new Vector2();
		MainPoint.x = Math.round((RoomDimensions.getX() + (RoomDimensions.getWidth() / 2)));
		MainPoint.y = Math.round((RoomDimensions.getY() + (RoomDimensions.getHeight() / 2)));

		return MainPoint;
	}
	
	
	// Checks the distance between two rooms.
	public int returnDistanceBetweenRooms(Room TestingRoom){
		float Value = Math.abs(CenterPoint().x - TestingRoom.CenterPoint().x) + Math.abs(CenterPoint().y - TestingRoom.CenterPoint().x);
		
		return (int)Value;
		
		
		
	}

}
