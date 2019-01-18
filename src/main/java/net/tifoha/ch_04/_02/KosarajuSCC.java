package net.tifoha.ch_04._02;

import static net.tifoha.ch_04.GraphUtils.tinyDG;

public class KosarajuSCC extends StrongConnectedComponents {
    public KosarajuSCC(Digraph graph) {
        super(graph.v());
        DepthFirstOrder dfo = new DepthFirstOrder(graph.reverse());
        for (int v : dfo.reversePost()) {
            if (notMarked(v)) {
                dfs(graph, v);
                count++;
            }
        }
    }

    public static void main(String[] args) {
        Digraph graph = tinyDG();
        System.out.println(graph);

            Digraph reverseGraph = graph.reverse();
        System.out.println(reverseGraph);

        DepthFirstOrder dfo = new DepthFirstOrder(graph);
        System.out.println(dfo.reversePost());

        DepthFirstOrder rdfo = new DepthFirstOrder(reverseGraph);
        System.out.println(rdfo.reversePost());

        StrongConnectedComponents scc = new KosarajuSCC(graph);
        System.out.println(scc.components());
    }

}
