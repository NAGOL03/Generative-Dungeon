import java.util.HashMap;

public class GameMap {
    public HashMap<GridPoint, Room> rooms = new HashMap<>();
    public final int ROOM_SIZE = 16;

    public GameMap() {
        rooms.put(new GridPoint(0, 0), new Room(ROOM_SIZE));
    }

    public void generateRoom(GridPoint position) {
        rooms.put(new GridPoint().clone(position), new Room(ROOM_SIZE));
        System.out.println("Room created at " + position);
    }
}