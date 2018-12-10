package net.tifoha.ch_04._01;

/**
 * @author Vitalii Sereda
 */
public class GraphProperties {
    private final BreadthFirstSearch[] distances;
    private int center;
    private int diameter = Integer.MIN_VALUE;
    private int radius = Integer.MAX_VALUE;

    public GraphProperties(Graph graph) {
        distances = new BreadthFirstSearch[graph.v()];
        for (int v = 0; v < graph.v(); v++) {
            BreadthFirstSearch search = new BreadthFirstSearch(graph, v);
            int eccentricity = search.eccentricity();
            diameter = Math.max(diameter, eccentricity);
            if (radius > eccentricity) {
                radius = eccentricity;
                center = v;
            }
            distances[v] = search;
        }
    }

    public int eccentricity(int v) {
        return distances[v].eccentricity();
    }

    public int diameter() {
        return diameter;
    }

    public int radius() {
        return radius;
    }

    public int center() {
        return center;
    }

    public int girth() {
        return distances[0].girth();
    }

    public static void main(String[] args) {
        ArrayGraph graph = new ArrayGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(4, 2);
        graph.addEdge(4, 3);

        GraphProperties props = new GraphProperties(graph);
        System.out.println("props.radius() = " + props.radius());
        System.out.println("props.center() = " + props.center());
        System.out.println("props.diameter() = " + props.diameter());
        System.out.println("props.eccentricity(0) = " + props.eccentricity(0));
        System.out.println("props.girth() = " + props.girth());
    }

}