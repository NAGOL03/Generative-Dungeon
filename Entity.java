import java.util.HashMap;

public class Entity {
    public GridPoint position;
    private HashMap<GridPoint, Entity> loadedEntities;
    private entityType type;
    private String symbol;
    private COLOR color;

    //used to determine other factors like the symbol and color for rendering
    public static enum entityType {
        // ENEMY,
        WALL,
        DOOR
    }

    //colors used for rendering
    public enum COLOR {
        WHITE,
        RED,
        BLUE,
        GREEN,
        YELLOW,
        BROWN

    }

    public Entity(int row, int column, String symbol, COLOR color) {
        this.position = new GridPoint(row, column);
        this.symbol = symbol;
        this.color = color;
    }

    public Entity(int row, int column, entityType type) {
        this.position = new GridPoint(row, column);
        this.type = type;

        switch (type) {
            case WALL -> {
                symbol = "o";
                color = COLOR.WHITE;
            }
            case DOOR -> {
                this.type = entityType.DOOR;
                symbol = "n";
                color = COLOR.BROWN;
            }
        }
    }

    public entityType getEntityType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }

    public COLOR getColor() {
        return color;
    }

    public void setRoomEntities(HashMap<GridPoint, Entity> entities) { //sets current entity positions
        this.loadedEntities = entities;
    }

    public HashMap<GridPoint, Entity> getloadedEntities() { //lets subclasses get access to entity positions
        return loadedEntities;
    }

}
