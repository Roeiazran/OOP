This OOP programm represntes mathematical logical expressions as a tree.
for example `((x & y) ^ T)` will be represnted as:


```
        Xor
       /   \
      &     T
    /   \
   x     y 
```

and in the programm itself as:

```
Expression e = new Xor(new And(new Var("x"), new Var("y")), new Val(true));
```

Given an expression the programm will provide the following features:

1. ``` e.toString()``` Represnts the expression as a string.

3. ```e.getVariables()``` Get the variables in the expression: x y
4.  ```e.assign("y", e2)``` Assign's expression to variable.

5.  ```e.assign("y", new Val(false))``` Assing's value to variable.

6. ```e.evaluate()``` Evaluate's the experssion truth value for a given variable's assignment to values.

7. ```e.nandify(), e.norify()``` Nandifies and Norifies the expression.

8. ```e.simplify()``` Simplify the expression. (i.e ```((x & F) ^ (y | F)) --> (y)```)

We agree on the following symbols:
```
And(x,y) = (x & y)
Or(x,y) = (x | y)
Xor(x,y) = (x ^ y)
Nand(x,y) = (x A y)
Nor(x,y) = (x V y)
Xnor(x,y) = (x # y)
Not(x) = ~(x)
```
and the programm will supports the following simplifications:

```
x & 1 = x
x & 0 = 0
x & x = x
x | 1 = 1
x | 0 = x
x | x = x
x ^ 1 = ~(x)
x ^ 0 = x
x ^ x = 0
x A 1 = ~(x)
x A 0 = 1
x A x = ~(x)
x V 1 = 0
x V 0 = ~(x)
x V x = ~(x)
x # x = 1
```

By the nature of the expression's repsented as a tree the programm uses recursion
to implement the features mentioned. Also the programm handle's errors by throwing execptions and catching them.

----

To run the programm you'll need to open the ExpressionTest file and decalre an expression,
then run one or more of the function's described above and in the Expression interface.

Create the bin directory (from the root directory):
```
javac -d bin src/*.java
```

Then change the current directory to the bin directory and run:

```
java ../src/ExpressionTest.java -cp .
```








