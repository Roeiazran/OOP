Simple Animations program

--------------------

The program generates 10 lines, drawn in black. The midpoint of each line is marked in blue, while intersection points between lines are marked in red. If three lines intersect to form a rectangle, the rectangle is drawn in green.

To run the program (from the root folder):
> javac -cp biuoop-1.4.jar:src src/AbstractArtDrawing.java -d bin

> java -cp biuoop-1.4.jar:bin AbstractArtDrawing

--------------------

The program generates a ball at specified coordinates ```(x, y)```. The ball moves across the screen with a given velocity ```(dx, dy)```.

To run the program (from the root folder):
> javac -cp biuoop-1.4.jar:src src/BouncingBallAnimation.java -d bin

> java -cp biuoop-1.4.jar:bin BouncingBallAnimation x y dx dy

--------------------

The program creates n balls with specified sizes ```s1, s2, …, sn```. The balls move across the screen.

To run the programm (from the root folder):
> javac -cp biuoop-1.4.jar:src src/MultipleBouncingBallsAnimation.java -d bin

> java -cp biuoop-1.4.jar:bin MultipleBouncingBallsAnimation s1 s2 ... sn

--------------------

The program creates n balls such that half travel inside a frame and the other half travel outside it, while ensuring that the balls do not overlap with each other’s frames.

To run the program (from the root folder):
> javac -cp biuoop-1.4.jar:src src/MultipleFramesBouncingBallsAnimation.java -d bin

> java -cp biuoop-1.4.jar:bin MultipleFramesBouncingBallsAnimation s1 s2 ... sn

--------------------
