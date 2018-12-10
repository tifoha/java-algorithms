package net.tifoha.ch_04._01;

/**
 * @author Vitalii Sereda
 */
public abstract class Graph {

    public abstract int v();

    public abstract int e();

    public abstract void  addEdge(int v, int w);

    public abstract Iterable<Integer> adj(int v);

    public String toString() {
        StringBuilder s = new StringBuilder(v() + " вершин, " + e() + " ребер\n");
        for (int v = 0; v < v(); v++) {
            s.append(v).append(": ");
            for (int w : this.adj(v)) {
                s.append(w).append(' ');
            }
            s.append('\n');
        }
        return s.toString();
    }

    public static int degree(Graph graph, int v) {
        int degree = 0;
        for (Integer integer : graph.adj(v)) {
            degree++;
        }
        return degree;
    }

    public static int maxDegree(Graph graph) {
        int maxDegree = 0;
        for (int i = 0; i < graph.v(); i++) {
            int degree = degree(graph, i);
            maxDegree = maxDegree < degree ? degree : maxDegree;
        }
        return maxDegree;
    }

    public static int avgDegree(Graph graph) {
        return 2 * graph.e() / graph.e();
    }

    public static int numberOfSelfLoops(Graph graph) {
        int count = 0;
        for (int v = 0; v < graph.v(); v++) {
            for (int w : graph.adj(v)) {
                if (v == w) count++;
            }
        }

        return count / 2; // каждое ребро посчитано дважды
    }
}
