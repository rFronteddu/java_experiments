# java_experiments

## QUICK START
Project uses gradle so you will need it (https://gradle.org/). After having installed it from the main directory follow the following instructions:
1) gradle run
2) Then follow instructions in interactive terminal

## Tests: 
### Union Find
Union find API works as follows:
1) Upon creating the object, an algorithm and the number of elements will be requested.
2) After that, an user can enter pair of connected values. If there is not already a path between them the system will print give notice on the debug terminal.

This test has the following implementations of Union Find:
1) __QUICK FIND__: Quick find is an eager algorithm that works by having elements that are connected having the same connection ID.
2) __QUICK UNION__: Quick Union is an algorithm that works by having elements that are connected having the same root connection ID.
3) __WEIGHTED QUICK UNION__: Like quick union but forces copy of tree from smaller to bigger
4) __WEIGHTED WITH PATH COMPRESSION__: Like WQU but stores in each element the id of the root so the root search is executed only once

### Percolation
This particular percolation implementation solves the problem of determining if there is a path between the top and the bottom of a grid. To do this, I used the CompressedWeightedUnion 4 times each time a new cell of the grid is opened. Percolation si then tested by checking if a top and a bottom virtual cell are connected. I mantained the CWU and the cell's grid separated so this solution uses a little more memory that what would otherwise be needed. The link between the CWU and the grid is an ID that I assign to each grid node and that correspond to a cell in the CWU array.
