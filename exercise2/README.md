Simple Animations programm's.

--------------------

The first programm generates 10 lines, drawn in black. The middle point in each line is indicated in blue, while the intersection points between the lines are indicated in red.
If 3 lines intersects and create a rectangle this rectangle will be drawn in green.

to run the programm (from the root folder):
> javac -cp biuoop-1.4.jar:src src/AbstractArtDrawing.java -d bin
> java -cp biuoop-1.4.jar:bin AbstractArtDrawing

--------------------

The second programm generate a ball at a given coordinates x and y, the ball will travel the screen at velocity dx and dy given.

to run the programm (from the root folder):
> javac -cp biuoop-1.4.jar:src src/BouncingBallAnimation.java -d bin \n
> java -cp biuoop-1.4.jar:bin BouncingBallAnimation x y dx dy

--------------------

The third progranmm will create n balls with sized s1, s2, .. sn given. The balls will
travel the screen.

to run the programm (from the root folder):
> javac -cp biuoop-1.4.jar:src src/MultipleBouncingBallsAnimation.java -d bin
> java -cp biuoop-1.4.jar:bin MultipleBouncingBallsAnimation s1 s2 ... sn

--------------------

The firth programm will n balls halve of then will travel inside a frame and the other halve outside the frame without exiting of entering the frames.

to run the programm (from the root folder):
> javac -cp biuoop-1.4.jar:src src/MultipleFramesBouncingBallsAnimation.java -d bin
> java -cp biuoop-1.4.jar:bin MultipleFramesBouncingBallsAnimation s1 s2 ... sn

--------------------
