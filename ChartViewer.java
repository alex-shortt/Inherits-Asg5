import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JComponent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

//Created by Aneesh Lodhavia and Alex Shortt

/**
 This component displays a rectangle that can be moved.
 */
public class ChartViewer
{
    public static void main(String[] args)
    {
        JFrame frame = new ChartFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
 * Self Evaluation
 *
 * This projects is very accurate in terms of the clicking. The program can get the exact location of the user's click
 * and move the bar in the chart to that exact point. One flaw is that it's hard to remove the bar completely if it
 * reaches 0 because within the code you can only repaint the current object.
 *
 * Making a battleship game would involve creating a grid system because, assuming you want to make it easy, you
 * probably want there to be a grid system so that the user doesn't have to click exactly on the battleship to hit it.
 * With a grid they can have some extra space. Assuming you code this, battleship wouldn't be too hard.
 */
class ChartFrame extends JFrame
{
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;

    private ChartComponent scene;

    class MousePressListener implements MouseListener
    {
        public void mousePressed(MouseEvent event)
        {
            int x = event.getX();
            int y = event.getY();

            scene.moveRectangleTo(x, y);
        }

        // Do-nothing methods
        public void mouseReleased(MouseEvent event) {}
        public void mouseClicked(MouseEvent event) {}
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
    }


    public ChartFrame()
    {
        scene = new ChartComponent();
        add(scene);

        MouseListener listener = new MousePressListener();
        scene.addMouseListener(listener);

        scene.setFocusable(true);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
}

class ChartComponent extends JComponent
{
    public int BOX_X = 10;
    public int BOX_Y = 10;
    public int BOX_WIDTH = 20;
    public int BOX_HEIGHT = 30;
    public int barLevel;
    public int lastY = 30;
    public Rectangle[] bars = new Rectangle[100];

    /**
     * Component in charge of drawing the bars
     */
    public ChartComponent()
    {
        // The bar that the paintComponent method draws
        for (int i = 0; i < bars.length; i++) {
            bars[i] = new Rectangle(BOX_X, BOX_Y, 0, 0);
        }
    }

    /**
     * Paints the component on the screen
     * @param g graphics program
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < bars.length; i++) {
            g2.draw(bars[i]);
        }
    }

    /**
     Moves the rectangle to the given location.
     @param x the x-position of the new location
     @param y the y-position of the new location
     */
    public void moveRectangleTo(int x, int y)
    {
        x -= 10;
        barLevel = y / 30;
        if(x < 3) x = 0;
        if (y > lastY) {
            bars[lastY/30] = new Rectangle(BOX_X, lastY, x, 30);
            lastY+=30;
        }
        else
            bars[barLevel] = new Rectangle(BOX_X, (barLevel*30), x, 30);
        repaint();
    }
}