# Natera graph

Java code challenge for **Natera**

## Problem description

Simple Graph lib:
 
Should support 2 types of graphs - directed and undirected with 3 operations:
- ```addVertex``` - adds vertex to the graph
- ```addEdge``` - adds edge to the graph
- ```getPath``` - returns a list of edges between 2 vertices (path doesn’t have to be optimal)
 Vertices should be of a user defined type.

## Questions to be ready to answer (don’t have to implement):
- Add weighted edges support in your lib.
- Add traverse function that will take a user defined function and apply it on every vertex of the graph.
- Make you graphs thread safe.

## How to build and run

### Prerequisite

- Java 8+
- Gradle

#### Build

The project uses Gradle. To build this application run following command:

```
> gradlew clean build
```

It will build an executable jar file in `/build/libs/graph.jar`. 

