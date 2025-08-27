This OOP program represnts mathematical logical expressions in the form of a tree.
For example the represntation of ((x & y) ^ T) is:

```
        Xor
       /   \
      &     T
    /   \
   x     y
```

This experssion is and an instance, and instactiated like this:

```
Expression e = new Xor(new And(new Var("x"), new Var("y")), new Val(true));
```

Given a mathematical logical expression, the program provides the following features:

1. ```e.toString() ``` represnt the expression as a string: ((x & y) ^ T)  
2. ```e.getVariables()``` get the variables in the expression.  
3.  ```e.assign("y", e2)``` assign expression to variables.  
4. ```e.assign("y", new Val(false))``` assing value to variable.  
5. ```e.evaluate()``` evaluate the truth value of the expression over some variable assignment.  
6. ```e.nandify(), e.norify()``` Nandify and Norify the expression.
7. ```e.simplify()``` simplify the expression: ((x & F) ^ (y | F)) --> (y)  

Let's define some symbols:
```
And(x,y) = (x & y)
Or(x,y) = (x | y)
Xor(x,y) = (x ^ y)
Nand(x,y) = (x A y)
Nor(x,y) = (x V y)
Xnor(x,y) = (x # y)
Not(x) = ~(x)
```

The program supports the following simplifications:

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
Because the recursive nature of the expression, when implementing those features mentioned i used recursive functions.
Besides that the program has handeling errors mechanisem and it throwes execptions and catches them.

# 

To run the program you'll be needing to declare an expression in the ExpressionTest file, then run one or more of the functions described above.

In the expressionTest you'll find the following expression:

![](pic.png)

Create the bin directory (from the root directory):
```
javac -d bin src/*.java
```

After that navigate to the bin directory and run:
```
java ../src/ExpressionTest.java -cp .
```








