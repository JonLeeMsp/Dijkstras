import com.leex1825.algorithm.Dijkstras
import com.leex1825.store.Edge
import com.leex1825.store.Graph
import com.leex1825.store.Node
import spock.lang.Ignore
import spock.lang.Specification

class DijkstrasSpec extends Specification {

    Dijkstras dijkstras 

    Graph graph

    def setup() {
        graph = createGraph();
        dijkstras = new Dijkstras()
    }

    @Ignore
    def "find Dijkstras shortest path"() {
        given:
            List<Node> nodes = graph.getNodes()
            Node startNode = nodes.get(0)
            Node endNode = nodes.get(nodes.size() - 1)

        when:
            List<Node> shortestPath = dijkstras.search(startNode, endNode)

        then:
            shortestPath != null
            shortestPath.size() == 2
            shortestPath.get(0).getId() == 0
            shortestPath.get(1).getId() == 4
    }


    def createGraph() {
        List<Node> nodes = new ArrayList<>();

        for (int i = 0; i < 5; ++i) {
            nodes.add(new Node(i, i * 5));
        }

        for (int j = 0; j < nodes.size(); ++j) {

            Node node = nodes.get(j);

            for (int k = j; k < nodes.size(); ++k) {
                Node node2 = nodes.get(k);

                Edge edge = new Edge();
                edge.setCost(j * k);
                edge.setTargetNode(node2);

                node.getNeighboringEdges().add(edge);
            }
        }

        Graph graph = new Graph(nodes);
        return graph;
    }
}
