import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Player extends Entity {
    public GridPoint mapPosition = new GridPoint(0, 0);
    private Game game;

    public Player(int row, int column, Game game) {
        super(row, column, "@", COLOR.BLUE);
        this.game = game;
    }

    public KeyListener controller = new KeyListener() { //Listens to keys
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            keyBinds(e); //refers to keyBinds method to determine what action will occur
            game.consumeTurn();
        }
    };

    public void keyBinds(KeyEvent e) { //does action based on key press
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> { //Up arrow
                this.move("row", -1);
            }
            case KeyEvent.VK_W -> { //W key
                this.move("row", -1);
            }
            case KeyEvent.VK_DOWN -> { //Down arrow
                this.move("row", 1);
            }
            case KeyEvent.VK_S -> { //S key
                this.move("row", 1);
            }
            case KeyEvent.VK_LEFT -> { //Left arrow
                this.move("column", -1);
            }
            case KeyEvent.VK_A -> { //A key
                this.move("column", -1);
            }
            case KeyEvent.VK_RIGHT -> { //Right arrow
                this.move("column", 1);
            }
            case KeyEvent.VK_D -> { //D key
                this.move("column", 1);
            }
        }
    }

    private void move(String axis, int units) {
        HashMap<GridPoint, Entity> currentEntities = this.game.getEntities();
        GridPoint potentialPosition;

        if (axis.equals("row") || axis.equals("column")) {
            potentialPosition = new GridPoint().clone(this.position);
            potentialPosition.change(axis, units);

            if (currentEntities.get(potentialPosition) == null) this.position.copy(potentialPosition);
            else if (currentEntities.get(potentialPosition).getEntityType() == entityType.DOOR) {
                int direction = (int) Math.signum(units);
                int roomSize = this.game.currentRoom.getSize();

                this.mapPosition.change(axis, units);
                this.position.change(axis, -(direction * (roomSize - 3)));
                game.loadRoom(this.mapPosition);
            }
        }
        else throw new IllegalArgumentException(axis + " is invalid");
    }
}
