# JPS_IDAStar
IDA* algorithm implemented in Scala using functional programming

Project structure:
- <i>src/main/scala/idastar/Edge</i> structure which represents edge in graph
- <i>src/main/scala/idastar/Graph</i> structure which represents graph using adjacency list of vertices
- <i>src/main/scala/idastar/IDAStar</i> class implements algorithm IDA* on graph and list of heuristic values for vertices
- <i>src/test/scala/idastar/IDAStarTest</i> tests perfomed on algorithm to check its correctness

To use IDA* algorithm call:
<b>IDAStar(graph, heuristicVerticesList).IDAStar(firstVertex)</b>
  
where:
- graph - is idastar.Graph, graph constructed as adjacency list
- heuristicVerticesList - is Stream of heuristic values for vertices in graph
- firstVertex - index of first vertex from which searching will start
  
As a result you'll receive tuple of 3 elements:
  - _.1 = length: <i>Double</i> - length of the found path, 0 if not found
  - _.2 = path: <i>Stream[Int]</i> - found path as a stream, empty if not found
  - _.3 = found: <i>Boolean</i> - variable informs if found solution, false if not found
  
Use <i>index.html</i> to check documentation of the project
