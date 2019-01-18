package net.tifoha.ch_04._01;

/**
 * @author Vitalii Sereda
 */
public interface Graph {

    int v();

    int e();

    void  addEdge(int v, int w);

    Iterable<Integer> adj(int v);

    static int degree(Graph graph, int v) {
        int degree = 0;
        for (Integer integer : graph.adj(v)) {
            degree++;
        }
        return degree;
    }

    static int maxDegree(Graph graph) {
        int maxDegree = 0;
        for (int i = 0; i < graph.v(); i++) {
            int degree = degree(graph, i);
            maxDegree = maxDegree < degree ? degree : maxDegree;
        }
        return maxDegree;
    }

    static int avgDegree(Graph graph) {
        return 2 * graph.e() / graph.e();
    }

    static int numberOfSelfLoops(Graph graph) {
        int count = 0;
        for (int v = 0; v < graph.v(); v++) {
            for (int w : graph.adj(v)) {
                if (v == w) count++;
            }
        }

        return count / 2; // каждое ребро посчитано дважды
    }
}
