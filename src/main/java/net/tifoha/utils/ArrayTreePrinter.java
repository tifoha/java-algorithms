package net.tifoha.utils;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author Vitalii Sereda
 */
public class ArrayTreePrinter {
    public static class Node<T extends Comparable<T>> {
        T value;
        Node<T> left, right;

        public void insertToTree(T v) {
            if (value == null) {
                value = v;
                return;
            }
            if (v.compareTo(value) < 0) {
                if (left == null) {
                    left = new Node<T>();
                }
                left.insertToTree(v);
            } else {
                if (right == null) {
                    right = new Node<T>();
                }
                right.insertToTree(v);
            }
        }

        public void printTree(OutputStreamWriter out) throws IOException {
            if (right != null) {
                right.printTree(out, true, "");
            }
            printNodeValue(out);
            if (left != null) {
                left.printTree(out, false, "");
            }
        }

        private void printNodeValue(OutputStreamWriter out) throws IOException {
            if (value == null) {
                out.write("<null>");
            } else {
                out.write(value.toString());
            }
            out.write('\n');
        }
        // use string and not stringbuffer on purpose as we need to change the indent at each recursion
        private void printTree(OutputStreamWriter out, boolean isRight, String indent) throws IOException {
            if (right != null) {
                right.printTree(out, true, indent + (isRight ? "        " : " |      "));
            }
            out.write(indent);
            if (isRight) {
                out.write(" /");
            } else {
                out.write(" \\");
            }
            out.write("----- ");
            printNodeValue(out);
            if (left != null) {
                left.printTree(out, false, indent + (isRight ? " |      " : "        "));
            }
        }

    }
}
