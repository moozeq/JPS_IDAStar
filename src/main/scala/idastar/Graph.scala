package idastar

/** Structure holding [[idastar.Edge edges]] stream for each node in graph, its unambiguously defined graph structure,
  * directed graphs structures are also supported
  *
  * @constructor  gets stream of edges streams for each node
  * @param adjacencyList  stream of edges streams for each node identified by index
  */
case class Graph(adjacencyList: Stream[Stream[Edge]]) {}