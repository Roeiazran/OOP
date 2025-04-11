// 204058366 Roei Azran
package environment;
import java.awt.Color;

import geometry.Point;
import geometry.Rectangle;
import shapes.Block;
/**
 * provides global contants to be used across all clsses.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Consts {

    /* contants */
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 800;
    public static final int NUM_OF_BLOCKS = 42;
    public static final int NUM_OF_BALLS = 3;
    public static final int INITIAL_SCORE = 0;
    private static int margin = 1;

    /* screen rectangles */
    private static Rectangle scrUp =
        new Rectangle(new Point(-margin, 0),
        SCREEN_WIDTH + 2 * margin, 0);
    private static Rectangle scrDown =
        new Rectangle(new Point(-margin, SCREEN_HEIGHT),
        SCREEN_WIDTH + 2 * margin, 0);
    private static Rectangle scrLeft =
        new Rectangle(new Point(0, -margin), 0,
        SCREEN_HEIGHT + 2 * margin);
    private static Rectangle scrRight =
        new Rectangle(new Point(SCREEN_WIDTH, -margin),
        0, SCREEN_HEIGHT + 2 * margin);

    /* paddles rectangle */
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 20;
    public static final int PADDLE_START_X = SCREEN_WIDTH / 2;
    public static final int PADDLE_START_Y = SCREEN_HEIGHT - 100;
    public static final Color PADDLE_COLOR = Color.GREEN;

    private Rectangle paddle1Rect =
    new Rectangle(new Point(PADDLE_START_X, PADDLE_START_Y),
        PADDLE_WIDTH, PADDLE_HEIGHT);
    private Rectangle paddle2Rect =
    new Rectangle(new Point(PADDLE_START_X, PADDLE_START_Y),
        PADDLE_WIDTH, PADDLE_HEIGHT);


    /* screen blocks */
    private Block scrUpBlock = new Block(scrUp, Color.BLACK);
    private Block scrDownBlock = new Block(scrDown, Color.BLACK);
    private Block scrRightBlock = new Block(scrRight, Color.BLACK);
    private Block scrLeftBlock = new Block(scrLeft, Color.BLACK);

    /* balls */
    public static final Point BALL1_START_PT = new Point(300,  300);
    public static final Point BALL2_START_PT = new Point(500,  300);
    public static final Point BALL3_START_PT = new Point(700,  300);

    public static final int BALL_REDIUS = 10;
    public static final double INIT_DX = 4;
    public static final double INIT_DY = 2;

    /* Blocks */
    public static final int BLOCK_WIDTH = 60;
    public static final int BLOCK_HEIGHT = 30;
    public static final int NUM_BLOCKS_IN_ROW1 = 12;
    public static final int NUM_OF_BLOCKS_ROWS = 6;
    public static final int BLOCK_PADDING = 1;


    /**
     * getter.
     * @return the paddle1 rectangle.
     */
    public Rectangle getPaddle1Rect() {
        return paddle1Rect;
    }

    /**
     * getter.
     * @return paddle2 rectangle.
     */
    public Rectangle getPaddle2Rect() {
        return paddle2Rect;
    }

    /**
     * getter.
     * @return the upper screen block.
     */
    public Block getScrUpBlock() {
        return scrUpBlock;
    }

    /**
     * getter.
     * @return the down screen block.
     */
    public Block getScrDownBlock() {
        return scrDownBlock;
    }

    /**
     * getter.
     * @return the right screen block.
     */
    public Block getScrRightBlock() {
        return scrRightBlock;
    }

    /**
     * getter.
     * @return the left screen block.
     */
    public Block getScrLeftBlock() {
        return scrLeftBlock;
    }

}
