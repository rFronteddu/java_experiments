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

### Social Network
This exercise solves the problem of determining the earliest time at which all members of a social network are connected (i.e. every member is a friend of a friend ... of a friend). The problem solution is based on Weighted Quick Union and the network is considered fully connected when the number of elements that are rooted at a certain value is equal to the number of elements in the network.

### Canonical Union Find
This implementation of union find has a method so that find(i) returns the largest element in the connected component containing i. Find is implemented to be logartimic or better. To do that I store the max for each component.

### Percolation
This particular percolation implementation solves the problem of determining if there is a path between the top and the bottom of a grid. To do this, I used the CompressedWeightedUnion 4 times each time a new cell of the grid is opened. Percolation si then tested by checking if a top and a bottom virtual cell are connected. I mantained the CWU and the cell's grid separated so this solution uses a little more memory that what would otherwise be needed. The link between the CWU and the grid is an ID that I assign to each grid node and that correspond to a cell in the CWU array.

### Sum3
The 3SUM problem asks if a given set of n real numbers contains three elements that sum to zero. The provided algorithm tests recursively all the elements from an ordered array. If the sum is bigger than zero we try to get the next smaller from the end while if it's smaller than zero we increase the smaller element not under evaluation. Solutions are stored in a hashset to avoid duplicates.

### Bitonic Search
During the creation of the bitonic array I keep track fo the tipping point so that when it's time to search I can split the array in two ordered array and use a binary search.


### Merge Sort
This provides a recursive implementation of merge sort. The algorithm follows the classic divide and conquer paradigm of dividing the input array into two halves, recursively sort each half, and merge the two halves. Some improvements such as using insertion sort for small input arrays or avoid multiple copy of elements are also provided.

### Collinear Detector
The collinear detector solves the problem of detecting if 4 or more points are collinear. Two solutions are provided, the first one is a brute force apporach that check 4 points at a time if they are collinear. The tuples of four elements are extracted using a simple recursive implementation of k combination. The second fast implementation first orders the points by slope and then check all adjacent points to see if they are collinear. This is a much faster approach. This second version is also optimized to not have duplicated solutions.
