package net.tifoha.ch_01._05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author Vitalii Sereda
 */
public class Client {
    public static void main(String[] args) throws IOException {
        int size = 1000_000;
        Collection<UnionFind> testCollection = Arrays.asList(
//                new FastSearch(size),
//                new FastUnion(size),
                new WeightedFastUnion(size),
                new WeightedFastUnionPathCompressing(size),
                new WeightedFastUnionPathCompressingRecursion(size),
                new WeightedFastUnionPathHalving(size)
        );
        testCollection.forEach(Client::test);
//        WeightedFastUnion wfu;
//        wfu = new WeightedFastUnion(size);
//        test(wfu);
//        System.out.println(Arrays.toString(wfu.getMaxSizes(100)));
//        wfu = new WeightedFastUnionPathCompressing(size);
//        test(wfu);
//        System.out.println(Arrays.toString(wfu.getMaxSizes(100)));
    }

    private static void test(UnionFind uf) {
        long start = System.currentTimeMillis();
        getLines(uf)
                .forEach(s -> {
                    int splitIndex = s.indexOf(' ');
                    int p = Integer.parseInt(s.substring(0, splitIndex));
                    int q = Integer.parseInt(s.substring(splitIndex + 1));
                    if (uf.connected(p, q)) {
                        return;
                    }
                    uf.union(p, q);
//                    System.out.printf("%s %s%n", p, q);
                });
//        System.out.println(uf);
        System.out.printf("%s Time: %sms.%n" ,uf, (System.currentTimeMillis() - start));
    }

    private static Stream<String> getLines(UnionFind uf) {
        Path path = Generator.pathToData(uf.size());
        try {
            if (Files.notExists(path)) {
                Generator.generate(uf.size());
            }
            return Files.lines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
