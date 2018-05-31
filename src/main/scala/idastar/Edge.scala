package idastar

/** Structure which represents edge of graph, edge contains only information about to which node its lead,
  * its part of [[idastar.Graph Graph structure]] and doesn't need to contain information about initial node
  *
  * @constructor gets target node to which edge is leading and weight of the path
  * @param targetVertex index of target node
  * @param weight weight of the edge
  */
case class Edge(targetVertex: Int, weight: Double) {}