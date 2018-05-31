package idastar

/**
  * A main class which solves graph problem by IDA* algorithm
  *
  * @constructor gets [[idastar.Graph graph]] structure and heuristic values of each node in Stream
  * @param graph  [[idastar.Graph graph]] structure contains stream of [[idastar.Edge edges]] for each node
  * @param verticesHeuristic  stream of heuristic values for each node in [[idastar.Graph graph]]
  */
case class IDAStar(graph: Graph, verticesHeuristic: Stream[Double]) {
  /** Starts finding shortest path in graph
    *
    * @param firstVertex  vertex from which algorithm starts searching
    * @return tuple of 3 elements
    *         - ''Double'': shortest path length, 0 if not found solution
    *         - ''Stream[Int]'': shortest path by stream of vertices, empty if not found solution
    *         - ''Boolean'': true if found solution, false if not found
    */
  def IDAStar(firstVertex: Int): (Double, Stream[Int], Boolean) = {
    try {
      getSolution(Stream(firstVertex), 0, verticesHeuristic(firstVertex))
    } catch {
      case _: Exception => (0, Stream(), false)
    }
  }

  /** Recursively searching graph and changing depth of searching (tail recursive)
    *
    * @param path current path to be searched
    * @param g  cost of traverse from first node to last node in path
    * @param bound  current bound - depth of searching based on heuristic
    * @return recursively itself or
    *         tuple of 3 elements if newPath returned from ''search'' contains target node
    *         - ''Double'': path length
    *         - ''Stream[Int]'': path by stream of vertices
    *         - ''Boolean'': true if newPath contains target node, false if not
    */
  def getSolution(path: Stream[Int], g: Double, bound: Double): (Double, Stream[Int], Boolean) = {
    val (t, newPath, isFound) = search(path, 0, bound)
    if (isFound) (t, newPath, isFound) else getSolution(newPath, 0, t)
  }

  /** Main body of algorithm
    *   1. Gets last node from path
    *   1. Check if bound is not exceeded - if yes return this path with estimated cost if not
    *   1. Check if last node is target node - if yes return this path with cost of all traversed edges if not
    *   1. Get node's neighbours from adjacencyList and get nodes not included in current path
    *   1. Sort them by sum of weight of edge and heuristic value of node
    *   1. For each neighbour (successor) call recursive method ''searchSuccessor''
    *   1. Sort them by cost of their path
    *   1. If there are nodes with true - ''isFound'' parameter, filter them to foundSolution value
    *
    * @param path current path
    * @param g  cost of traverse from first node to last node in path
    * @param bound  current bound - depth of searching based on heuristic
    * @return tuple of 3 elements, get as best one successor of passed node (with path of lowest cost)
    *         - ''Double'': path length
    *         - ''Stream[Int]'': path by stream of vertices
    *         - ''Boolean'': true if path containing target node, false if not
    */
  def search(path: Stream[Int], g: Double, bound: Double): (Double, Stream[Int], Boolean) = {
    val node = path.last
    val f = g + verticesHeuristic(node)
    if (f > bound) return (f, path, false)
    if (verticesHeuristic(node) == 0) return (g, path, true)
    val result = graph.adjacencyList(node)
      .filter((e: Edge) => !path.contains(e.targetVertex))
      .sortBy((e: Edge) => e.weight + verticesHeuristic(e.targetVertex))
      .map((e: Edge) => searchSuccessor(path.append(Stream(e.targetVertex)), g + e.weight, bound))
      .sortBy(_._1)

    val foundSolution = result
      .filter((p: (Double, Stream[Int], Boolean)) => p._3)

    if (foundSolution.nonEmpty) foundSolution.head else result.head
  }

  /** Recursively searching successors (neighbours) of passed node (tail recursive)
    *
    * @param path current path to be searched
    * @param g  cost of traverse from first node to last node in path
    * @param bound  current bound - depth of searching based on heuristic
    * @return recursively itself or tuple of 3 elements if:
    *         returned cost of newPath from ''search'' is greater than actual bound or
    *         newPath from ''search'' contains target node
    *         - ''Double'': path length
    *         - ''Stream[Int]'': path by stream of vertices
    *         - ''Boolean'': true if path containing target node, false if not
    */
  def searchSuccessor(path: Stream[Int], g: Double, bound: Double): (Double, Stream[Int], Boolean) = {
    val (t, newPath, isFound) = search(path, g, bound)
    if (t > bound || isFound) (t, newPath, isFound) else searchSuccessor(newPath.tail, g, bound)
  }
}