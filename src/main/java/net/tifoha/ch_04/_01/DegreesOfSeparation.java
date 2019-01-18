package net.tifoha.ch_04._01;

/**
 * @author Vitalii Sereda
 */
public class DegreesOfSeparation {
    public static void main(String[] args) {
        String filename = "C:\\Work\\IdeaProjects\\java-algorithms\\src\\main\\resources\\movies.txt";
        String delim = "/";
        SymbolGraph sg = new SymbolGraph(filename, delim);
        Graph graph = sg.graph();
        String source = "Bacon, Kevin";
        PathFinder pathFinder = new BreadthFirstSearch(graph, sg.index(source));
        String actor = "Quinlan, Kathleen";

//        for (int i = 0; i < graph.v(); i++) {
        int actorIndex = sg.index(actor);
        if (pathFinder.hasPathTo(actorIndex)) {
            System.out.print(source + " to " + actor + ": ");
            for (int v : pathFinder.pathTo(actorIndex)) {
                System.out.printf("'%s'%s", sg.name(v), (v != actorIndex ? " -> " : "\n"));
            }
        }
//        }
    }
}
