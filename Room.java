import java.util.ArrayList;
import java.util.Random;

public class Room {
    public final ArrayList<Entity> ENTITIES = new ArrayList<>();
    private final int size;

    /**
     * The constructor to generate a room
     * @param size size of the room
     */
    public Room(int size) {
        this.size = size - 1;

        //loops through and places walls (size * size)
        for (int row = 1; row <= size; row++) {
            for (int column = 1; column <= size; column++) {
                Entity entity = new Entity(row, column, Entity.entityType.WALL);

                if (column == 1 || column == this.size) {
                    ENTITIES.add(entity);
                }

                else if (row == 1 || row == this.size) {
                    ENTITIES.add(entity);
                }


            }
        }

        //generate a random wall to make it look like we arent just teleporting back into the same room
        Random rand = new Random();
        ENTITIES.add(new Entity(rand.nextInt(3, this.size - 3), rand.nextInt(3, this.size - 3), Entity.entityType.WALL));

        //generates 4 doors on each wall of the room
        ArrayList<Entity> temp = (ArrayList<Entity>) ENTITIES.clone();
        for (Entity entity : temp) {
            if (entity.position.equals(GridPoint.key(this.size / 2 + 1, this.size))) {
                ENTITIES.remove(entity);
                ENTITIES.add(new Entity(this.size / 2 + 1, this.size, Entity.entityType.DOOR));
            }

            else if (entity.position.equals(GridPoint.key(this.size / 2 +1, 1))) {
                ENTITIES.remove(entity);
                ENTITIES.add(new Entity(this.size / 2 + 1, 1, Entity.entityType.DOOR));
            }

            else if (entity.position.equals(GridPoint.key(this.size, this.size / 2 + 1))) {
                ENTITIES.remove(entity);
                ENTITIES.add(new Entity(this.size, this.size / 2 + 1, Entity.entityType.DOOR));
            }

            else if (entity.position.equals(GridPoint.key(1, this.size / 2 + 1))) {
                ENTITIES.remove(entity);
                ENTITIES.add(new Entity(1, this.size / 2 + 1, Entity.entityType.DOOR));
            }
        }

    }

    public int getSize() {
        return size;
    }
}
