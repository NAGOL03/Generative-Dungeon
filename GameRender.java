import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameRender {
    public JFrame frame;
    private JTextPane textPane;
    private StyledDocument doc;
    private SimpleAttributeSet attr;
    private JPanel panel;

    //colors
    private Style WHITE;
    private Style GREEN;
    private Style BLUE;
    private Style BROWN;

    public GameRender(KeyListener controller) { //components required to render
        //assign variables
        frame = new JFrame();
        textPane = new JTextPane();
        panel = new JPanel();
        doc = textPane.getStyledDocument();
        attr = new SimpleAttributeSet();

        //colors used for entities
        WHITE = textPane.addStyle("WHITE", null);
        StyleConstants.setForeground(WHITE, Color.WHITE);
        BLUE = textPane.addStyle("BLUE", null);
        StyleConstants.setForeground(BLUE, Color.BLUE);
        GREEN = textPane.addStyle("GREEN", null);
        StyleConstants.setForeground(GREEN, Color.GREEN);
        BROWN = textPane.addStyle("BROWN", null);
        StyleConstants.setForeground(BROWN, new Color(139, 69, 19));

        //frame settings
        StyleConstants.setLineSpacing(attr, -0.54f);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.requestFocus();
        frame.getContentPane().setBackground(Color.black);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.getContentPane().add(Box.createVerticalGlue());

        //textPane settings
        doc.setParagraphAttributes(0, doc.getLength(), attr, false);
        textPane.setFont(new Font("Monospaced", Font.PLAIN, frame.getHeight() / 25));
        textPane.setForeground(Color.WHITE);
        textPane.setEditable(false);
        textPane.setOpaque(false);
        textPane.setCaretColor(Color.black);
        textPane.addKeyListener(controller);

        //panel settings
        panel.setOpaque(false);
        panel.add(textPane);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void appendRenderData(Entity entity) {
        Style chooseColorStyle;
        String entitySymbol = entity.getSymbol();

        switch (entity.getColor()) {
            case BLUE -> chooseColorStyle = BLUE;
            case GREEN -> chooseColorStyle = GREEN;
            case BROWN -> chooseColorStyle = BROWN;
            default -> chooseColorStyle = WHITE;
        }

        try {
            doc.insertString(doc.getLength(), entitySymbol, chooseColorStyle);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void appendRenderData(String text) {
        try {
            doc.insertString(doc.getLength(), text, WHITE);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }


    public void render(Game game) { //Renders game
        Entity thisPositionContains;

        textPane.setFont(new Font("Monospaced", Font.PLAIN, frame.getHeight() / 25)); //sets proper font for rendering
        textPane.setText(""); //clears text

        for (int row = 1; row <= game.currentRoom.getSize(); row++) {
            for (int column = 1; column <= game.currentRoom.getSize(); column++) {
                // checkThisPosition.set("row", row);
                // checkThisPosition.set("column", column);
                thisPositionContains = game.getEntities().get(GridPoint.key(row, column));

                if (thisPositionContains != null) {
                    appendRenderData(thisPositionContains);
                }
                if (column == game.currentRoom.getSize()) {
                    appendRenderData("\n");
                }
                else if (thisPositionContains == null) {
                    appendRenderData(" ");
                }

            }
        }
    }

}
