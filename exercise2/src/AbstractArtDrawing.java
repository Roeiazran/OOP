// 204058366 Roei Azran
import java.awt.Color;
import java.util.Random;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * AbstractArtDrawing class provides methods and displays 10 lines on
 * the screen.
 *
 * @version 21.0.1 17 Oct 2023
 * @author Roei Azran
 */
public class AbstractArtDrawing {

    /* the number of groups of 3 from a grop of 10 */
    private static final int MAX_RECTANLGE_LINES = 120;

    private static final int LINES_NUMBER = 10;
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 600;
    private static int radius = 3;

    /**
     * method to generate a random line.
     * @return randomly generated line.
     */
    public Line generateRandomLine() {
        Random rand = new Random();

        /* generate random coordinates */
        double x1 = (SCREEN_WIDTH - 1) * rand.nextDouble() + 1;
        double y1 = (SCREEN_HEIGHT - 1) * rand.nextDouble() + 1;
        double x2 = (SCREEN_WIDTH - 1) * rand.nextDouble() + 1;
        double y2 = (SCREEN_HEIGHT - 1) * rand.nextDouble() + 1;

        return new Line(x1, y1, x2, y2);
    }

    /**
     * method to draw the lines intersection points with red circle.
     * @param lines the lines array.
     * @param d the dislpay.
     */
    private void drawIntersectionPoint(Line[] lines, DrawSurface d) {

        /* loop for every line */
        for (int i = 0; i < lines.length; i++) {

            /* compare it with the other lines */
            for (int j = i + 1; j < lines.length; j++) {
                if (lines[i].isIntersecting(lines[j])) {

                    /* draws the intersection point */
                    Point intersection = lines[i].intersectionWith(lines[j]);
                    drawPoint(intersection, radius, Color.RED, d);
                }
            }
        }
    }

    /**
     * method to draw a point on the screen.
     * @param p the point to draw.
     * @param radius the radius of the circle.
     * @param color the color of the circle.
     * @param d the display.
     */
    void drawPoint(Point p, int radius, Color color, DrawSurface d) {

        /* get the x and y from the point and display it */
        int x = (int) p.getX();
        int y = (int) p.getY();
        d.setColor(color);
        d.fillCircle(x, y, radius);
    }

    /**
     * mathod to fill a random lines array.
     * @param n the lines number.
     * @param lines the line array to fill.
     */
    private void fillRandomLinesArray(int n, Line[] lines) {
        for (int i = 0; i < n; i++) {
            lines[i] = generateRandomLine();
        }
    }

    /**
     * method to draw a line all the line in the array.
     * @param lines the lines array.
     * @param d the display.
     * @param color the color to draw the lines.
     */
    private void drawLines(Line[] lines, DrawSurface d, Color color) {
        d.setColor(color);

        /* loop for every line */
        for (Line line : lines) {

            /* get it coordinate and display them */
            if (line != null) {
                int x1 = (int) line.start().getX();
                int y1 = (int) line.start().getY();
                int x2 = (int) line.end().getX();
                int y2 = (int) line.end().getY();
                d.drawLine(x1, y1, x2, y2);
            }
        }
    }

    /**
     * method to draw the line middle point.
     * @param lines the lines array.
     * @param d the display.
     * */
    private void drawMiddlePoints(Line[] lines, DrawSurface d) {
        for (int i = 0; i < lines.length; i++) {

            /* get the middle point from Point and display it */
            drawPoint(lines[i].middle(), radius, Color.BLUE, d);
        }
    }

    /**
     * method to draw the rectangle created from intersection of three lines.
     * @param lines the lines array.
     * @param d the display.
     */
    private void drawRectangles(Line[] lines, DrawSurface d) {

        /* generate new rectangle array and index to it */
        Line[] rectLines = new Line[MAX_RECTANLGE_LINES];
        int rectCounter = 0;

        /* loop for every line in the array */
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                for (int k = j + 1; k < lines.length; k++) {

                    /* check for the two lines intersecting with the third */
                    if (lines[i].isIntersecting(lines[j], lines[k])) {

                        /* they intersect then check for the closeing point  */
                        if (lines[j].isIntersecting(lines[k])) {

                            /* get the three intersection points and add them*/
                            Point intersecA = lines[i].intersectionWith(
                                lines[j]);
                            Point intersecB = lines[i].intersectionWith(
                                lines[k]);
                            Point intersecC = lines[j].intersectionWith(
                                lines[k]);

                            /* add the lines of those point to the array */
                            rectLines[rectCounter++] = new Line(intersecA,
                             intersecB);
                            rectLines[rectCounter++] = new Line(intersecB,
                             intersecC);
                            rectLines[rectCounter++] = new Line(intersecA,
                             intersecC);
                        }
                    }
                }
            }
        }
        drawLines(rectLines, d, Color.GREEN);
    }


    /**
     * the main method.
     * @param args
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Abstract Art Drawing",
            SCREEN_WIDTH, SCREEN_HEIGHT);
        DrawSurface d = gui.getDrawSurface();

        Line[] linesArray = new Line[LINES_NUMBER];
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.fillRandomLinesArray(LINES_NUMBER, linesArray);
        example.drawLines(linesArray, d, Color.BLACK);
        example.drawMiddlePoints(linesArray, d);
        example.drawIntersectionPoint(linesArray, d);
        example.drawRectangles(linesArray, d);
        gui.show(d);
    }
}
