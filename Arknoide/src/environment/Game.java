package environment;
import biuoop.GUI;
import collisions.Collidable;
import geometry.Point;
import geometry.Rectangle;
import listeners.Counter;
import listeners.ScoreIndicator;
import listeners.ScoreTrackingListener;
import listeners.BlockRemover;
import listeners.BallRemover;
import shapes.Ball;
import shapes.Block;
import shapes.Paddle;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
/**
 * Provides initialization and running of the game also handles removing
 * and adding of game objects.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Game {

    /* initalize fields */
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private biuoop.KeyboardSensor keyboard;
    private Paddle paddle1;
    private Paddle paddle2;
    private GUI gui;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter scoreCounter;

    /**
     * Ctor.
     */
    public Game() {
        this.blocksCounter = new Counter(Consts.NUM_OF_BLOCKS);
        this.ballsCounter = new Counter(Consts.NUM_OF_BALLS);
        this.scoreCounter = new Counter(Consts.INITIAL_SCORE);
    }

    /**
     * adds collidable to the environment.
     * @param c the collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * removes collidable from the environment.
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * adds sprites to the environment.
     * @param s the sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * removes sprite from the environment.
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {

        Consts c = new Consts();

        /* initalize the game environmment and refernce it to the balls */
        environment = new GameEnvironment();

        /* initalize the sprite collection */
        sprites = new SpriteCollection();


        /* add score display to the game */
        addScoreDisplayToTheGame();

        /* add the balls to the game */
        addBallsToTheGame();

        /* initalize the graphics variables */
        gui = new GUI("", Consts.SCREEN_WIDTH, Consts.SCREEN_HEIGHT);
        keyboard = gui.getKeyboardSensor();

        /* add paddle to the game after initalizing the keyboard sensor */
        addPaddlesToTheGame(c);

        /*  adding blocks to the game  */
        addBlockToTheGame();

        /* adding the screen borders to the game */
        addScreenBordersToTheGame(c);

    }

    /**
     * adding the score display to the game environment.
     */
    private void addScoreDisplayToTheGame() {
        ScoreIndicator si = new ScoreIndicator(scoreCounter);
        si.addToGame(this);
    }

    /**
     * adding the screen borders to the game environment.
     * @param c the consts instace.
     */
    private void addScreenBordersToTheGame(Consts c) {

        /* add listener to the buttom block */
        BallRemover br = new BallRemover(this, ballsCounter);
        c.getScrDownBlock().addHitListener(br);

        /* adding the object to the game */
        c.getScrDownBlock().addToGame(this);
        c.getScrLeftBlock().addToGame(this);
        c.getScrRightBlock().addToGame(this);
        c.getScrUpBlock().addToGame(this);
    }

    /**
     * adding the paddles to the game environment.
     * @param c the consts instance.
     */
    private void addPaddlesToTheGame(Consts c) {

        /* creating the paddles */
        paddle1 = new Paddle(c.getPaddle1Rect(), keyboard, this);
        paddle2 = new Paddle(c.getPaddle2Rect(), keyboard, this);

        /* initalizeng the othe paddle to each other */
        paddle1.setOther(paddle2);
        paddle2.setOther(paddle1);

        /* adding paddle1 to the game */
        paddle1.addToGame(this);
    }

    /**
     * adding the balls to the game environment.
     */
    private void addBallsToTheGame() {

        /* create the balls and set their initial velocity */
        Ball ball1 = new Ball(Consts.BALL1_START_PT, Consts.BALL_REDIUS,
        Color.BLUE, environment);
        Ball ball2 = new Ball(Consts.BALL2_START_PT, Consts.BALL_REDIUS,
        Color.BLUE, environment);
        Ball ball3 = new Ball(Consts.BALL3_START_PT, Consts.BALL_REDIUS,
        Color.BLUE, environment);
        ball1.setVelocity(Consts.INIT_DX, Consts.INIT_DY);
        ball2.setVelocity(Consts.INIT_DX, Consts.INIT_DY);
        ball3.setVelocity(Consts.INIT_DX, Consts.INIT_DY);

        /* adding objects to the game */
        ball1.addToGame(this);
        ball2.addToGame(this);
        ball3.addToGame(this);
    }

    /**
     * adds blocks to the game in a piramid shape.
     */
    private void addBlockToTheGame() {

        int screenWidth = Consts.SCREEN_WIDTH;
        int numOfBlock = Consts.NUM_BLOCKS_IN_ROW1;

        /* inital x and y of the block at (0,0) */
        double initialX = (screenWidth - numOfBlock * Consts.BLOCK_WIDTH) / 2;
        double initialY = Consts.BLOCK_PADDING + Consts.BLOCK_HEIGHT;
        double upperY;
        double upperX;
        Point upperLeft;

        /* create listeners */
        BlockRemover br = new BlockRemover(this, blocksCounter);
        ScoreTrackingListener st = new ScoreTrackingListener(scoreCounter);

        /* loop for each row */
        for (int i = 0; i < Consts.NUM_OF_BLOCKS_ROWS; i++) {

            /* calculate the upperleft point of the rectangle */
            upperX = initialX + i * (Consts.BLOCK_WIDTH
                + Consts.BLOCK_PADDING);
            upperY = initialY + i * (Consts.BLOCK_HEIGHT
                + Consts.BLOCK_PADDING);
            Color color = generateRandomColor();

            /* loop for each column */
            for (int j = 0; j < Consts.NUM_BLOCKS_IN_ROW1 - 2 * i; j++) {

                /* create rectangle with the point then a block from the
                * rectangle then and add it to the game.
                */
                upperLeft = new Point(upperX, upperY);
                Rectangle rect = new Rectangle(upperLeft, Consts.BLOCK_WIDTH,
                    Consts.BLOCK_HEIGHT);
                Block block = new Block(rect, color);

                /* register the listener to every block */
                block.addHitListener(br);
                block.addHitListener(st);

                /* add the block to the game */
                block.addToGame(this);

                /* incerent the statring point for the next rectanlge */
                upperX = upperX + Consts.BLOCK_WIDTH + Consts.BLOCK_PADDING;
            }
        }
    }

    /**
     * recursive method to generates random non black color.
     * @return random color.
     */
    private Color generateRandomColor() {

        /* chose randomly the rgb */
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        Color c = new Color(r, g, b);

        /* filttering out the black color */
        if (c.equals(Color.BLACK)) {
            return generateRandomColor();
        }

        /* return the color */
        return c;
    }

    /**
     * Run the game i.e start the animation loop.
     */
    public void run() {

        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        /* run */
        while (true) {
           long startTime = System.currentTimeMillis(); // timing

           DrawSurface d = gui.getDrawSurface();
           this.sprites.drawAllOn(d);
           gui.show(d);
           this.sprites.notifyAllTimePassed();

           /* checks if the're no block left */
           if (blocksCounter.getValue() == 0) {

                /* incremet score by 100 */
                this.scoreCounter.increase(100);

                /* exit from tha game */
                gui.close();
                return;
                /* check if the're no balls left */
           } else if (ballsCounter.getValue() == 0) {

                /* exit from the game */
                gui.close();
                return;
           }

           // timing
           long usedTime = System.currentTimeMillis() - startTime;
           long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
           if (milliSecondLeftToSleep > 0) {
               sleeper.sleepFor(milliSecondLeftToSleep);
           }
        }
    }
}
