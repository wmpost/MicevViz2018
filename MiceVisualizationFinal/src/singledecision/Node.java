/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singledecision;

/**
 *
 * @author jenniferpolack-wahladmin
 */
import java.util.ArrayList;
import java.util.List;

/**
 * A node of any type. A node contains a data and links to it's children and it's parent.
 *
 * @param <T> The class type of the node
 */
public class Node{
    private SingleMiceBehaviours data;
    private List<Node> children;
    private Node parent;

    public Node(SingleMiceBehaviours data) {
        this.data = data;
        this.children = new ArrayList<Node>();
    }

    /**
     * Initialize a node with another node's data.
     * This does not copy the node's children.
     *
     * @param node The node whose data is to be copied.
     */
    public Node(Node node) {
        this.data = node.getData();
        children = new ArrayList<Node>();
    }

    /**
     *
     * Add a child to this node.
     *
     * @param child child node
     */
    public void addChild(Node child) {
        child.setParent(this);
        children.add(child);
    }

    /**
     *
     * Add a child node at the given index.
     *
     * @param index The index at which the child has to be inserted.
     * @param child The child node.
     */
    public void addChildAt(int index, Node child) {
        child.setParent(this);
        this.children.add(index, child);
    }

    public void setChildren(List<Node> children) {
        for (Node child : children)
            child.setParent(this);

        this.children = children;
    }

    /**
     * Remove all children of this node.
     */
    public void removeChildren() {
        this.children.clear();
    }

    /**
     *
     * Remove child at given index.
     *
     * @param index The index at which the child has to be removed.
     * @return the removed node.
     */
    public Node removeChildAt(int index) {
        return children.remove(index);
    }

    /**
     * Remove given child of this node.
     *
     * @param childToBeDeleted the child node to remove.
     * @return <code>true</code> if the given node was a child of this node and was deleted,
     * <code>false</code> otherwise.
     */
    public boolean removeChild(Node childToBeDeleted) {
        List<Node> list = getChildren();
        return list.remove(childToBeDeleted);
    }

    public SingleMiceBehaviours getData() {
        return this.data;
    }

    public void setData(SingleMiceBehaviours data) {
        this.data = data;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return this.children;
    }

    public Node getChildAt(int index) {
        return children.get(index);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj)
            return false;

        if (obj instanceof Node) {
            if (((Node) obj).getData().equals(this.data))
                return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

}