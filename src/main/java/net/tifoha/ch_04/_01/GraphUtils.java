package net.tifoha.ch_04._01;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author Vitalii Sereda
 */
public class GraphUtils {

    private static final Path RESOURCE_PATH = Paths.get("src\\main\\resources\\");

    public static Scanner getScanner(String fileName) {
        try {
            InputStream in = Files.newInputStream(RESOURCE_PATH.resolve(fileName));
            return new Scanner(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
