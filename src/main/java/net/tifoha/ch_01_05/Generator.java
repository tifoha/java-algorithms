package net.tifoha.ch_01_05;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * @author Vitalii Sereda
 */
public class Generator {
    private static final Random RND = new Random();
    private static final Path ROOT_PATH = Paths.get("C:\\Work\\IdeaProjects\\java-algorithms\\src\\main\\resources\\ch_01_05");

    public static Path generate(int size) throws IOException {
        Path path = pathToData(size);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (int i = 0; i < size; i++) {
                int p = RND.nextInt(size);
                int q = RND.nextInt(size);
                while (p == q) {
                    q = RND.nextInt(size);
                }
                writer.write(String.format("%s %s%n", p, q));
            }
            return path;
        }
    }

    public static Path pathToData(int size) {
        return ROOT_PATH.resolve("UF_" + size + ".dat");
    }

    public static void main(String[] args) throws IOException {
        Generator.generate(1000000);
    }
}
