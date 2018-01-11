import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import ch02.stacks.LinkedStack;
import ch02.stacks.StackInterface;
import ch04.queues.LinkedQueue;
import ch04.queues.QueueInterface;
import ch09.priorityQueues.HeapPriQ;
import ch09.priorityQueues.PriQueueInterface;


public class Main {

	public static void main(String[] args) {

		WeightedGraph<Inter> graph = new WeightedGraph<Inter>();
		System.out.println("Welcome to Shortest Path finder!");
		System.out.println("Reading from KansasManhattan file...");
		String csvFile1 = "KansasManhattan.csv";
		File file = new File(csvFile1);
		try {
			Scanner inputStream = new Scanner(file);
			while (inputStream.hasNext()) {
				String data = inputStream.next();
				String[] intersectionList = data.split(",");
				String firstCrossStreet = intersectionList[0];
				String secondCrossStreet = intersectionList[1];
				Inter vertex = new Inter(firstCrossStreet, secondCrossStreet);

				graph.addSmartVertex(vertex);
			}
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}


//		System.out.println("Reading from KansasIntersection.csv file...");
		String csvFile2 = "KansasIntersection.csv";
		File file2 = new File(csvFile2);
		try {
			Scanner inputStream = new Scanner(file2);
			while (inputStream.hasNext()) {
				String data = inputStream.next();
				String[] intersectionList = data.split(",");
				String firstCrossStreet = intersectionList[0];
				String secondCrossStreet = intersectionList[1];

				String secondInterfirstCrossStreet = intersectionList[2];
				String secondIntersecondCrossStreet = intersectionList[3];
				Inter vertex = new Inter(firstCrossStreet, secondCrossStreet);
				Inter vertex2 = new Inter(secondInterfirstCrossStreet, secondIntersecondCrossStreet);
				graph.addEdge(vertex, vertex2, 1);
			}
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		WeightedGraph<Inter> newYork = new WeightedGraph<Inter>();
		System.out.println("Reading from NewYorkManhattan.csv file...");
		String csvFileNY1 = "NewYorkManhattan.csv";
		File fileNY1 = new File(csvFileNY1);
		try {
			Scanner inputStream = new Scanner(fileNY1);
			while (inputStream.hasNext()) {
				String data = inputStream.next();
				String[] intersectionList = data.split(",");
				String firstCrossStreet = intersectionList[0];
				String secondCrossStreet = intersectionList[1];
				Inter vertex = new Inter(firstCrossStreet, secondCrossStreet);
				newYork.addSmartVertex(vertex); }
			inputStream.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

//		System.out.println("Reading from NewYorkIntersection.csv file...");
		String csvFileNY2 = "NewYorkIntersection.csv";
		File fileNY2 = new File(csvFileNY2);
		try {
			Scanner inputStream = new Scanner(fileNY2);
			while (inputStream.hasNext()) {
				String data = inputStream.next();
				String[] intersectionList = data.split(",");
				String firstCrossStreet = intersectionList[0];
				String secondCrossStreet = intersectionList[1];

				String secondInterfirstCrossStreet = intersectionList[2];
				String secondIntersecondCrossStreet = intersectionList[3];
				Inter vertex = new Inter(firstCrossStreet, secondCrossStreet);
				Inter vertex2 = new Inter(secondInterfirstCrossStreet, secondIntersecondCrossStreet);
			}
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Map 1 - Manhattan, Kansas");
		System.out.println("Instructions: Enter an intersection as 'Hudson,Kimball' + " +
				"and a deadend as 'College,College'");
		System.out.println("Enter origin intersection: ");
		Scanner s = new Scanner(System.in);
		String data = s.nextLine();
		String[] originIntersection = data.split(",");
		System.out.println("Enter destination intersection: ");
		data = s.nextLine();
		String[] destinationIntersection = data.split(",");
		Inter origin = new Inter(originIntersection[0], originIntersection[1]);
		Inter destination = new Inter(destinationIntersection[0],destinationIntersection[1]);
		findShortestPath(graph, origin, destination);




	}
	public static void findShortestPath(WeightedGraphInterface<Inter> graph,
									Inter startVertex, Inter endVertex)
// Writes the shortest distance from startVertex to every
// other reachable vertex in graph.
	{
		Flight flight;
		Flight saveFlight;         // for saving on priority queue
		int minDistance;
		int newDistance;

		PriQueueInterface<Flight> pq = new HeapPriQ<Flight>(1000);   // Assume at most 20 vertices
		Inter vertex;
		QueueInterface<Inter> vertexQueue = new LinkedQueue<Inter>();

		graph.clearMarks();
		saveFlight = new Flight(startVertex, startVertex, 0);
		pq.enqueue(saveFlight);

		int shortestDistance=100;
		int counter = 0;

		do
		{
			flight = pq.dequeue();
			if (!graph.isMarked(flight.getToVertex()))
			{
				graph.markVertex(flight.getToVertex());
//				System.out.println(flight);
				flight.setFromVertex(flight.getToVertex());

				minDistance = flight.getDistance();

				vertexQueue = graph.getToVertices(flight.getFromVertex());
				while (!vertexQueue.isEmpty())
				{
					vertex = vertexQueue.dequeue();
					if (!graph.isMarked(vertex))
					{
						newDistance = minDistance
								+ graph.weightIs(flight.getFromVertex(), vertex);
						saveFlight = new Flight(flight.getFromVertex(), vertex, newDistance);
						pq.enqueue(saveFlight);
						if (saveFlight.getToVertex().name.equals((endVertex).name) ||

								(saveFlight.getToVertex().reverseName.equals((endVertex).reverseName))) {
							counter++;
							if (shortestDistance > saveFlight.distance) {
								shortestDistance = saveFlight.distance;
							}
						}
					}

				}
			}
		} while (!pq.isEmpty());

		System.out.println("Minimum path length: " + shortestDistance);
		System.out.println("Counter: " + (counter-1));

		System.out.println();
	}

	public static void shortestPath(WeightedGraphInterface<Inter> graph,
							 Inter startVertex  )
// Writes the shortest distance from startVertex to every
// other reachable vertex in graph.
	{
		Flight flight;
		Flight saveFlight;         // for saving on priority queue
		int minDistance;
		int newDistance;

		PriQueueInterface<Flight> pq = new HeapPriQ<Flight>(1000);   // Assume at most 20 vertices
		Inter vertex;
		QueueInterface<Inter> vertexQueue = new LinkedQueue<Inter>();

		graph.clearMarks();
		saveFlight = new Flight(startVertex, startVertex, 0);
		pq.enqueue(saveFlight);

		System.out.println("Last Vertex   Destination   Distance");
		System.out.println("------------------------------------");

		do
		{
			flight = pq.dequeue();
			if (!graph.isMarked(flight.getToVertex()))
			{
				graph.markVertex(flight.getToVertex());
				System.out.println(flight);
//				System.out.println(flight.getToVertex() + " to vertex()");
				flight.setFromVertex(flight.getToVertex());
				minDistance = flight.getDistance();
				vertexQueue = graph.getToVertices(flight.getFromVertex());
				while (!vertexQueue.isEmpty())
				{
					vertex = vertexQueue.dequeue();
					if (!graph.isMarked(vertex))
					{
						newDistance = minDistance
								+ graph.weightIs(flight.getFromVertex(), vertex);
						saveFlight = new Flight(flight.getFromVertex(), vertex, newDistance);
						pq.enqueue(saveFlight);
					}
				}
			}
		} while (!pq.isEmpty());
		System.out.println();
		System.out.println("The unreachable vertices are:");
		vertex = graph.getUnmarked();
		while (vertex != null)
		{
			System.out.println(vertex);
			graph.markVertex(vertex);
			vertex = graph.getUnmarked();
		}
		System.out.println();
	}

	public   boolean isPathDFirst(WeightedGraphInterface<Inter> graph,
								  Inter startVertex,
								  Inter endVertex    )
// Returns true if a path exists on graph, from startVertex to endVertex;
// otherwise returns false. Uses depth-first search algorithm.
	{
		StackInterface<Inter> stack = new LinkedStack<Inter>();
		QueueInterface<Inter> vertexQueue = new LinkedQueue<Inter>();

		boolean found = false;
		Inter currVertex;      // vertex being processed
		Inter adjVertex;       // adjacent to currVertex

		graph.clearMarks();
		graph.markVertex(startVertex);
		stack.push(startVertex);

		do
		{
			currVertex = stack.top();
			stack.pop();
			//System.out.println(currVertex);
			if (currVertex.equals(endVertex))
				found = true;
			else
			{
				vertexQueue = graph.getToVertices(currVertex);
				while (!vertexQueue.isEmpty())
				{
					adjVertex = vertexQueue.dequeue();
					if (!graph.isMarked(adjVertex))
					{
						graph.markVertex(adjVertex);
						stack.push(adjVertex);
					}
				}
			}
		} while (!stack.isEmpty() && !found);

		return found;
	}

	public boolean isPathBFirst(WeightedGraphInterface<Inter> graph,
								Inter startVertex,
								Inter endVertex    )

// Returns true if a path exists on graph, from startVertex to endVertex;
// otherwise returns false. Uses breadth-first search algorithm.

	{
		QueueInterface<Inter> queue = new LinkedQueue<Inter>();
		QueueInterface<Inter> vertexQueue = new LinkedQueue<Inter>();

		boolean found = false;
		Inter currVertex;      // vertex being processed
		Inter adjVertex;       // adjacent to currVertex

		graph.clearMarks();
		graph.markVertex(startVertex);
		queue.enqueue(startVertex);

		do
		{
			currVertex = queue.dequeue();
			System.out.println(currVertex);
			if (currVertex.equals(endVertex))
				found = true;
			else
			{
				vertexQueue = graph.getToVertices(currVertex);
				while (!vertexQueue.isEmpty())
				{
					adjVertex = vertexQueue.dequeue();
					if (!graph.isMarked(adjVertex))
					{
						graph.markVertex(adjVertex);
						queue.enqueue(adjVertex);
					}
				}
			}
		} while (!queue.isEmpty() && !found);

		return found;





		}
//		System.out.println("Enter origin:");
	}


 
