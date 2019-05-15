package Coding_Interview_Guide.BinaryTree;

/**
 * Created by ZingBug on 2018/11/11.
 */
public class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
        this.value = data;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
