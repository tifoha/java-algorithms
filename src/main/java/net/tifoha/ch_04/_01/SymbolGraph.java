//package net.tifoha.ch_04._01;
//
//import net.tifoha.utils.StdOut;
//import org.apache.commons.collections4.BidiMap;
//import org.apache.commons.collections4.bidimap.DualHashBidiMap;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.regex.Pattern;
//
///**
// * @author Vitalii Sereda
// */
//public class SymbolGraph {
//    private final Graph graph;
//    private BidiMap<String, Integer> indices = new DualHashBidiMap<>();
//
//    public SymbolGraph(String filename, String delimiter) {
//        try {
//            Pattern pattern = Pattern.compile(delimiter);
//            Path path = Paths.get(filename);
//            AtomicInteger index = new AtomicInteger();
//            Files.lines(path)
//                    .flatMap(pattern::splitAsStream)
//                    .forEach(name -> indices.computeIfAbsent(name, k -> index.getAndIncrement()));
//            graph = new ArrayGraph(indices.size());
//            Files.lines(path)
//                    .forEach(s -> {
//                        String[] names = pattern.split(s);
//                        int fromIndex = index(names[0]);
//                        for (int i = 1; i < names.length; i++) {
//                            int toIndex = index(names[i]);
//                            graph.addEdge(fromIndex, toIndex);
//                        }
//                    });
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public boolean contains(String key) {
//        return indices.containsKey(key);
//    }
//
//    public int index(String key) {
//        return indices.get(key);
//    }
//
//    public String name(int index) {
//        return indices.getKey(index);
//    }
//
//    public Graph graph() {
//        return graph;
//    }
//
//    public static void main(String[] args) {
//        String filename = "C:\\Work\\IdeaProjects\\java-algorithms\\src\\main\\resources\\movies.txt";
//        String delim = "/";
//        SymbolGraph sg = new SymbolGraph(filename, delim);
//        Graph graph = sg.graph();
////        while (StdIn.hasNextLine()) {
////            String source = StdIn.readLine();
//            String source = "Bacon, Kevin";
//            for (int w : graph.adj(sg.index(source))) {
//                StdOut.println("  " + sg.name(w));
//            }
////        }
//    }
//}
