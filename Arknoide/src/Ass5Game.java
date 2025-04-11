// 204058366 Roei Azran
import environment.Game;

/**
 * Provides the main method to start the game.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class Ass5Game {

    private static Game game;

    /**
     * main called on start.
     * @param args
     */
    public static void main(String[] args) {
        game = new Game();
        game.initialize();
        game.run();
    }

    /**
     * getter.
     * @return the game instance.
     */
    public static Game getGame() {
        return game;
    }
}
