package net.tifoha.ch_04._01;

import static net.tifoha.ch_04._01.GraphUtils.getScanner;

/**
 * @author Vitalii Sereda
 */
public class TestSearch {
    public static void main(String[] args) {
        ArrayGraph graph = new ArrayGraph(getScanner("tinyG.txt"));
        int mainVertex = 0;
//        DepthFirstSearch search = new DepthFirstSearch(graph, mainVertex);
        BreadthFirstSearch search = new BreadthFirstSearch(graph, mainVertex);
        for (int i = 0; i < graph.v(); i++) {
            if (search.marked(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        if (search.count() == graph.v()) {
            System.out.println("Связаный");
        } else {
            System.out.println("Не связаный");
        }

        for (int i = 0; i < graph.v(); i++) {
            if (search.hasPathTo(i)) {
                System.out.print(mainVertex + " to " + i + ": ");
                for (int v : search.pathTo(i)) {
                    System.out.print(v + (v != i ? "-" : "\n"));
                }
            }
        }

    }
}
