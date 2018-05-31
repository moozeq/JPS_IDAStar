package idastar
import org.scalatest.FunSuite

/**
  * Tests performed to check if algorithm works properly
  */
class IDAStarTest extends FunSuite {
  /**
    * Stream of [[idastar.Edge edges]] streams for first [[idastar.Graph graph]]
    */
  val verticesList = Stream[Stream[Edge]](
    Stream(Edge(1,30), Edge(4,10)),
    Stream(Edge(0,30), Edge(2,20), Edge(3,20), Edge(4,10)),
    Stream(Edge(1,20), Edge(3,10)),
    Stream(Edge(1,20), Edge(2,10), Edge(4,20)),
    Stream(Edge(0,10), Edge(1,10), Edge(3,20))
  )

  /**
    * Heuristic values for each node for first [[idastar.Graph graph]]
    */
  val heuristicVerticesList = Stream[Double](
    50, 20, 0, 15, 30
  )

  /**
    * Stream of [[idastar.Edge edges]] streams for second [[idastar.Graph graph]]
    */
  val verticesList2 = Stream[Stream[Edge]](
    Stream(Edge(1,4), Edge(6,3)), //0
    Stream(Edge(2,6), Edge(0,4)), //1
    Stream(Edge(3,4), Edge(1,6)), //2
    Stream(Edge(4,8), Edge(2,4)), //3
    Stream(Edge(5,6), Edge(3,8)), //4
    Stream(Edge(6,4), Edge(4,6)), //5
    Stream(Edge(0,3), Edge(5,4))  //6
  )

  /**
    * Heuristic values for each node for second [[idastar.Graph graph]]
    */
  val heuristicVerticesList2 = Stream[Double](
    15, 9, 4, 0, 8, 4, 8
  )

  /**
    * Result of first graph IDA* algorithm as tuple of [[idastar.IDAStar.IDAStar* (Double, Stream[Int], Boolean)]]
    */
  val result = IDAStar(Graph(verticesList), heuristicVerticesList).IDAStar(0)
  /**
    * Result of second graph IDA* algorithm as tuple of [[idastar.IDAStar.IDAStar* (Double, Stream[Int], Boolean)]]
    */
  val result2 = IDAStar(Graph(verticesList2), heuristicVerticesList2).IDAStar(0)

  /**
    * Test checking shortest path length correctness
    */
  test("shortest path length") {
    assert(result._1 == 40.0)
    assert(result2._1 == 14.0)
  }

  /**
    * Test checking shortest path correctness
    */
  test("shortest path nodes") {
    assert(result._2 == Stream(0,4,1,2))
    assert(result2._2 == Stream(0,1,2,3))
  }

  /**
    * Test checking if solution found correctness
    */
  test("solution found") {
    assert(result._3 == true)
    assert(result2._3 == true)
  }
}
