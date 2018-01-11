//----------------------------------------------------------------------
// Flights.java           by Dale/Joyce/Weems                  Chapter 9
//
// Supports flight objects having a "from" vertex, a "to" vertex, and a
// distance. Allows flights to be compared based on their distances.
//----------------------------------------------------------------------

public class Flight implements Comparable<Flight>
{
  protected Inter fromVertex;
  protected Inter toVertex;
  protected int distance;

  public Flight(Inter fromVertex, Inter toVertex, int distance)
  {
    this.fromVertex = fromVertex;
    this.toVertex = toVertex;
    this.distance = distance;
  }

  public Inter getFromVertex()
  {
    return fromVertex;
  }
  
  public Inter getToVertex()
  {
    return toVertex;
  }
  
  public int getDistance()
  {
    return distance;
  }
  
  public void setFromVertex(Inter vertex)
  {
    fromVertex = vertex;
  }

  public void setToVertex(Inter vertex)
  {
    toVertex = vertex;
  }

  public void setDistance(int distance)
  {
    this.distance = distance;
  }

  public int compareTo(Flight other)
  {
    return (other.distance - this.distance); // shorter is better 
  }
  
  @Override
  public String toString()
  {
    return (fromVertex + "    " + toVertex + "    " + distance);
  }
}
 