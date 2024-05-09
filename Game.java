import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {

    public Room currentRoom;
    private GameMap gameMap;
    private Player player;
    private GameRender render;
    private ArrayList<Entity> loadedEntities = new ArrayList<>();

    public Game() { //initialize
        Random rand = new Random(); 
        this.gameMap = new GameMap();
        GridPoint initialPlayerPosition = new GridPoint(rand.nextInt(2, this.gameMap.ROOM_SIZE - 2), rand.nextInt(2, this.gameMap.ROOM_SIZE - 2));
        this.player = new Player(initialPlayerPosition.get("row"), initialPlayerPosition.get("column"), this);
        this.render = new GameRender(player.controller);
        this.loadRoom(player.mapPosition);
        this.loadedEntities.add(player);
        this.player.setRoomEntities(this.getEntities());
        this.render.render(this);

    }

    // gets all entities and puts them into a hashmap<GridPoint, Entity>
    public HashMap<GridPoint, Entity> getEntities() {
        HashMap<GridPoint, Entity> entityHashMap = new HashMap<>();

        for (Entity entity : loadedEntities) {
            entityHashMap.put(entity.position, entity);
        }
        return entityHashMap;        
    }

    //loads a room from GameMap object
    public void loadRoom(GridPoint playerMapPosition) {
        if (this.gameMap.rooms.get(playerMapPosition) == null) {
            this.gameMap.generateRoom(playerMapPosition);
        }
        
        this.currentRoom = gameMap.rooms.get(playerMapPosition);
        this.loadedEntities = currentRoom.ENTITIES;
        this.loadedEntities.add(player);
        this.player.setRoomEntities(getEntities());
        this.render.frame.setTitle("Infinite Dungeon RPG | CURRENT ROOM : " + this.player.mapPosition.toString());
    }

    public void consumeTurn() { //consumes game turn allowing all to move after player

        this.player.setRoomEntities(getEntities());//updates player's knowledge of the room
        this.render.render(this);
    }

}