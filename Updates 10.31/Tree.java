/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micevisualization;

/**
 *
 * @author jenniferpolack-wahladmin
 */

import java.util.ArrayList;
import java.util.List;
import singledecision.*;

/**
 * Generic n-ary tree.
 *
 * @param <T> Any class type
 */
public class Tree {

    private Node root;

    /**
     * Initialize a tree with the specified root node.
     *
     * @param root The root node of the tree
     */
    public Tree(Node root) {
        this.root = root;
    }

    /**
     * Checks if the tree is empty (root node is null)
     *
     * @return <code>true</code> if the tree is empty, <code>false</code>
     * otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Get the root node of the tree
     *
     * @return the root node.
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Set the root node of the tree. Replaces existing root node.
     *
     * @param root The root node to replace the existing root node with.
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     *
     * Check if given data is present in the tree.
     *
     * @param key The data to search for
     * @return <code>true</code> if the given key was found in the tree,
     * <code>false</code> otherwise.
     */
    public boolean exists(SingleMiceBehaviours key) {
        return find(root, key);
    }

    /**
     * Get the number of nodes (size) in the tree.
     *
     * @return The number of nodes in the tree
     */
    public int size() {
        return getNumberOfDescendants(root) + 1;
    }

    /**
     *
     * Get the number of descendants a given node has.
     *
     * @param node The node whose number of descendants is needed.
     * @return the number of descendants
     */
    public int getNumberOfDescendants(Node node) {
        int n = node.getChildren().size();
        for (Node child : node.getChildren()) {
            n += getNumberOfDescendants(child);
        }

        return n;

    }

    private boolean find(Node node, SingleMiceBehaviours keyNode) {
        boolean res = false;
        if (node.getData().equals(keyNode)) {
            return true;
        } else {
            for (Node child : node.getChildren()) {
                if (find(child, keyNode)) {
                    res = true;
                }
            }
        }

        return res;
    }

    private Node findNode(Node node, SingleMiceBehaviours keyNode) {
        if (node == null) {
            return null;
        }
        if (node.getData().equals(keyNode)) {
            return node;
        } else {
            Node cnode = null;
            for (Node child : node.getChildren()) {
                if ((cnode = findNode(child, keyNode)) != null) {
                    return cnode;
                }
            }
        }
        return null;
    }

    /**
     *
     * Get the list of nodes arranged by the pre-order traversal of the tree.
     *
     * @return The list of nodes in the tree, arranged in the pre-order
     */
    public ArrayList<Node> getPreOrderTraversal() {
        ArrayList<Node> preOrder = new ArrayList<Node>();
        buildPreOrder(root, preOrder);
        return preOrder;
    }

    /**
     *
     * Get the list of nodes arranged by the post-order traversal of the tree.
     *
     * @return The list of nodes in the tree, arranged in the post-order
     */
    public ArrayList<Node> getPostOrderTraversal() {
        ArrayList<Node> postOrder = new ArrayList<Node>();
        buildPostOrder(root, postOrder);
        return postOrder;
    }

    private void buildPreOrder(Node node, ArrayList<Node> preOrder) {
        preOrder.add(node);
        for (Node child : node.getChildren()) {
            buildPreOrder(child, preOrder);
        }
    }

    private void buildPostOrder(Node node, ArrayList<Node> postOrder) {
        for (Node child : node.getChildren()) {
            buildPostOrder(child, postOrder);
        }
        postOrder.add(node);
    }

    /**
     *
     * Get the list of nodes in the longest path from root to any leaf in the
     * tree.
     *
     * For example, for the below tree
     * <pre>
     *          A
     *         / \
     *        B   C
     *           / \
     *          D  E
     *              \
     *              F
     * </pre>
     *
     * The result would be [A, C, E, F]
     *
     * @return The list of nodes in the longest path.
     */
    public ArrayList<Node> getLongestPathFromRootToAnyLeaf() {
        ArrayList<Node> longestPath = null;
        int max = 0;
        for (ArrayList<Node> path : getPathsFromRootToAnyLeaf()) {
            if (path.size() > max) {
                max = path.size();
                longestPath = path;
            }
        }
        return longestPath;
    }

    /**
     *
     * Get the height of the tree (the number of nodes in the longest path from
     * root to a leaf)
     *
     * @return The height of the tree.
     */
    public int getHeight() {
        return getLongestPathFromRootToAnyLeaf().size();
    }

    /**
     *
     * Get a list of all the paths (which is again a list of nodes along a path)
     * from the root node to every leaf.
     *
     * @return List of paths.
     */
    public ArrayList<ArrayList<Node>> getPathsFromRootToAnyLeaf() {
        ArrayList<ArrayList<Node>> paths = new ArrayList<ArrayList<Node>>();
        ArrayList<Node> currentPath = new ArrayList<Node>();
        getPath(root, currentPath, paths);

        return paths;
    }

    private void getPath(Node node, ArrayList<Node> currentPath,
            ArrayList<ArrayList<Node>> paths) {
        if (currentPath == null) {
            return;
        }

        currentPath.add(node);

        if (node.getChildren().size() == 0) {
            // This is a leaf
            paths.add(clone(currentPath));
        }
        for (Node child : node.getChildren()) {
            getPath(child, currentPath, paths);
        }

        int index = currentPath.indexOf(node);
        for (int i = index; i < currentPath.size(); i++) {
            currentPath.remove(index);
        }
    }

    private ArrayList<Node> clone(ArrayList<Node> list) {
        ArrayList<Node> newList = new ArrayList<Node>();
        for (Node node : list) {
            newList.add(new Node(node));
        }

        return newList;
    }

    public String search(miceData keyNode) {
       
        return searchPrivate(root, keyNode);
    }

    private String searchPrivate(Node node, miceData keyNode) {
        String behaviour;
        List<Node> child;
        if (node.getChildren().isEmpty()) {
            // This is a leaf
            return node.getData().getBehaviour();

        }

        child = node.getChildren();
        int i = 0;
        while (i < child.size()) {
            //System.out.print(" Time = " + keyNode.time+ " " +child.get(i).getData().getTestValue() +" "+child.get(i).getData().getComparision());
            if (child.get(i).getData().getComparision().equalsIgnoreCase("less")) {
                if (keyNode.time < child.get(i).getData().getTestValue()) {
                    System.out.print(" less " + child.get(i).getData().getBehaviour() + " ");
                    node = child.get(i);
                    return searchPrivate(node, keyNode);
                }
            } else if (child.get(i).getData().getComparision().equalsIgnoreCase("greater")) {
                if (keyNode.time > child.get(i).getData().getTestValue()) {
                    System.out.print(" greater " + child.get(i).getData().getBehaviour() + " ");
                    node = child.get(i);
                    return searchPrivate(node, keyNode);
                }
            } else if (child.get(i).getData().getComparision().equalsIgnoreCase("equal")) {
                    if (keyNode.location == child.get(i).getData().getTestValue()) {
                    System.out.print(" equal " + child.get(i).getData().getBehaviour() + " ");
                    return child.get(i).getData().getBehaviour();
                }
            } else if (child.get(i).getData().getComparision().equalsIgnoreCase("null")) {
                System.out.print(" null " + child.get(i).getData().getBehaviour() + " ");
                return child.get(i).getData().getBehaviour();
            }
            i++;
        }

        return node.getData().getBehaviour();
    }
}
