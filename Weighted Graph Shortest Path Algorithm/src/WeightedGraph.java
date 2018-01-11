//----------------------------------------------------------------------------
// WeightedGraph.java            by Dale/Joyce/Weems                Chapter 10
//
// Implements a directed graph with weighted edges.
// Vertices are objects of class T and can be marked as having been visited.
// Edge weights are integers.
// Equivalence of vertices is determined by the vertices' equals method.
//
// General precondition: Except for the addVertex and hasVertex methods, 
// any vertex passed as an argument to a method is in this graph.
//----------------------------------------------------------------------------

//package ch10.graphs;

import ch04.queues.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;

public class WeightedGraph<T> implements WeightedGraphInterface<T>
{
  public static final int NULL_EDGE = 0;
  private static final int DEFCAP = 50;  // default capacity
  private int numVertices;
  private int maxVertices;
  public T[] vertices;
  private int[][] edges;
  private boolean[] marks;  // marks[i] is mark for vertices[i]

  @SuppressWarnings("unchecked")
public WeightedGraph()
  // Instantiates a graph with capacity DEFCAP vertices.
  {
    numVertices = 0;
    maxVertices = DEFCAP;
    vertices = (T[]) new Object[DEFCAP];
    marks = new boolean[DEFCAP];
    edges = new int[DEFCAP][DEFCAP];
  }
 
  public WeightedGraph(int maxV)
  // Instantiates a graph with capacity maxV.
  {
    numVertices = 0;
    maxVertices = maxV;
    vertices = (T[]) new Object[maxV];
    marks = new boolean[maxV];
    edges = new int[maxV][maxV];
  }

  public boolean isEmpty()
  // Returns true if this graph is empty; otherwise, returns false.
  {
	return false;
  }

  public boolean isFull()
  // Returns true if this graph is full; otherwise, returns false.
  {
	return false;
  }

  public void addVertex(T vertex)
  // Preconditions:   This graph is not full.
  //                  vertex is not already in this graph.
  //                  vertex is not null.
  //
  // Adds vertex to this graph.
  {
    vertices[numVertices] = vertex;
    for (int index = 0; index < numVertices; index++)
    {
      edges[numVertices][index] = NULL_EDGE;
      edges[index][numVertices] = NULL_EDGE;
    }
    numVertices++;
  }

  public void addSmartVertex(T vertex)
  // Preconditions:   This graph is not full.
  //                  vertex is not already in this graph.
  //                  vertex is not null.
  //
  // Adds vertex to this graph.
  {
    String[] streetList = vertex.toString().split(",");

    vertices[numVertices] = vertex;
    for (int index = 0; index < numVertices; index++)
    {
      edges[numVertices][index] = NULL_EDGE;
      edges[index][numVertices] = NULL_EDGE;
    }
    numVertices++; }

  public boolean hasVertex(T vertex)
  // Returns true if this graph contains vertex; otherwise, returns false.
  {
      if (indexIs(vertex) >= 0 ) {
          return true;
      }
      return false;
  }

  public int indexIs(T vertex)
  // Returns the index of vertex in vertices.
  {

      for (int index=0; index<vertices.length; index++) {

        if (vertices[index] == null) {
            continue;
        }

        if ((((Inter) vertex).name.equals(((Inter)vertices[index]).name)) ||
        ((Inter) vertex).name.equals(((Inter)vertices[index]).reverseName)){
            return index;

      }
       }
    return -1;
  }

  public boolean sameAs(T vertex){
    boolean found = false;
//    System.out.println(Arrays.toString(vertices) + " from sameAs");
//    System.out.println(vertex.toSt`ring()+" from sameAs");
    for (int index=0; index<vertices.length; index++) {
      if (vertices[index] == null) continue;
      if (vertex.toString().equals(vertices[index].toString())) {
        found = true;
      }
    }
    return found;

  }



  public void addEdge(T fromVertex, T toVertex, int weight)
  // Adds an edge with the specified weight from fromVertex to toVertex.
  {
      int row;
      int column;
      row = indexIs(fromVertex);
      column = indexIs(toVertex);
      edges[row][column] = weight;
      edges[column][row] = weight;
  }

  public int weightIs(T fromVertex, T toVertex)
  // If edge from fromVertex to toVertex exists, returns the weight of edge;
  // otherwise, returns a special �null-edge� value.
  {
    int row;
    int column;
 
    row = indexIs(fromVertex);
    column = indexIs(toVertex);
    return edges[row][column];

  }

  public QueueInterface<T> getToVertices(T vertex)
  // Returns a queue of the vertices that vertex is adjacent to.
  {
    QueueInterface<T> adjVertices = new LinkedQueue<T>();
    int fromIndex;
    int toIndex;
    fromIndex = indexIs(vertex);
    for (toIndex = 0; toIndex < numVertices; toIndex++)
      if (edges[fromIndex][toIndex] != NULL_EDGE)
        adjVertices.enqueue(vertices[toIndex]);
    return adjVertices;
  }

  public void clearMarks()
  // Unmarks all vertices.
  {
      for (int i = 0; i < marks.length; i++){
          marks[i] = false;
      }
  }

  public void markVertex(T vertex)
  // Marks vertex.
  {
      int index = indexIs(vertex);
      marks[index] = true;
  }

  public boolean isMarked(T vertex)
  // Returns true if vertex is marked; otherwise, returns false.
  {
	return marks[indexIs(vertex)];
  }
  
  public T getUnmarked()
  // Returns an unmarked vertex if any exist; otherwise, returns null.
  {
	return null;
  }
  
  public boolean edgeExists(T vertex1, T vertex2)
  // Preconditions:  vertex1 and vertex2 are in the set of vertices
  //
  // Return value = (vertex1, vertex2) is in the set of edges
  {
//      System.out.println(indexIs(vertex1));
////      System.out.println(indexIs(vertex2));
//      System.out.println(indexIs(vertex2));

    return (edges[indexIs(vertex1)][indexIs(vertex2)] != NULL_EDGE);
  }

  public void printEdges(){
      for (int i = 0; i < edges.length; i ++){
          for (int j = 0; j < edges.length; j++){
              System.out.print(" " + edges[i][j]);
          }
          System.out.println();
      }
  }

  public boolean removeEdge(T vertex1, T vertex2)
  // Preconditions:  vertex1 and vertex2 are in the set of vertices
  //
  // Return value = true if edge was in the graph (and has been removed)
  //              = false if edge was not in the graph
  {
    boolean existed = edgeExists(vertex1, vertex2);
    edges[indexIs(vertex1)][indexIs(vertex2)] = NULL_EDGE;
    return existed;
  }
  
}
