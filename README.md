# DatabaseAssignment2
Database Assignment using RandomAccessFile in Java

Zafer Khourdaji
EID: E01251928
URL: https://people.emich.edu/zkhourda/COSC471/Homework2.zip
 
This project is about storing records in a database using the RandomAccessFile class in Java.

My Project has 3 classes: Record, Database, Driver.

The Record class represents a single record to be inserted to the database,
its constructor takes in the 3 fields specified in the assignment, an int, a double, and a char.

The Database class has insert, retrieve, and find operations.
Its constructor takes in a file name which will store the records in.

The Driver class creates some Records objects and uses the Database
class to run some operations and output the results.

To run this program first compile all the java source code by running:
javac *.java

then run the Driver class by:
java Driver
