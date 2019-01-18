package net.tifoha.utils;

/**
 * @author Vitalii Sereda
 */

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import net.tifoha.ch_03._02.node.AbstractTreeNode;
import net.tifoha.ch_03._02.node.Balanced23Tree;
import net.tifoha.ch_03._02.node.TreeNode;

import javax.swing.*;
import java.util.*;

public class TreeWindow extends JFrame {
    Deque<AbstractTreeNode<?>> deque = new LinkedList<>();

    /**
     *
     */
    private static final long serialVersionUID = -2707712944901661771L;

    public TreeWindow(TreeNode<?> root) {
        super("Tree");
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        mxCompactTreeLayout layout = new mxCompactTreeLayout(graph);
        layout.setHorizontal(false);
        layout.setEdgeRouting(false);
//        layout.setUseBoundingBox(false);

        graph.getModel().beginUpdate();
        try {
            Map<AbstractTreeNode<?>, Object> nodes = new HashMap<>();
            AbstractTreeNode<?> node = (AbstractTreeNode<?>) root;
            addNode(node);
            while (!deque.isEmpty()) {
                node = deque.removeFirst();
                String value = node.toString();
                Object vertex = graph.insertVertex(parent, value, value, 0, 0, 50, 20);
                nodes.put(node, vertex);
                if (node.hasParent()) {
                    graph.insertEdge(parent, null, getEdgeName(node.getParent(), node), nodes.get(node.getParent()), vertex);
                }
                addNode(node.getLeft());
                if (node instanceof Balanced23Tree.Tree3Node) {
                    addNode(((Balanced23Tree.Tree3Node) node).getMiddle());
                }
                addNode(node.getRight());
            }
//
//            Object v1 = graph.insertVertex(parent, "1", "Hello", 0, 00, 80, 30);
//
//            Object v2 = graph.insertVertex(parent, "2", "World!", 0, 0, 80, 30);
//            graph.insertEdge(parent, null, "Edge", v1, v2);
//
//            Object v3 = graph.insertVertex(parent, "2", "World2", 0, 00, 80, 30);
//            graph.insertEdge(parent, null, "Edge", v1, v3);

            layout.execute(parent, nodes.get(root));

        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    private String getEdgeName(AbstractTreeNode<?> parent, AbstractTreeNode<?> node) {
        if (parent.getLeft() == node) {
            return "L";
        } else if (parent.getRight() == node) {
            return "R";
        } else {
            return "M";
        }
    }

    public void addNode(AbstractTreeNode<?> node) {
        if (node != null) {
            deque.addLast(node);
        }
    }

    public static void main(String[] args) {
        Balanced23Tree<String> tree = new Balanced23Tree<>(Comparator.<String>naturalOrder());
        Collection<String> strings = Arrays.asList("ABCDEFLAUWEOHNQWJLEFOWHLNWOFHOWNEFWOHOFWBEGGHIJK".split(""));
        tree.addAll(strings);
        TreeVisualizer.show(tree.root);
    }

}