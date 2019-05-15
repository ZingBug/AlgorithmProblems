package Coding_Interview_Guide.BinaryTree;

/**
 * 完全二叉树的节点数
 * 给定一颗完全二叉树的头节点head，返回这棵树的节点个数，时间复杂度低于O(N).
 * Created by ZingBug on 2018/12/23.
 */
public class NodeNum {
    private int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    private int bs(Node node, int l, int h) {
        if (l == h) {
            return 1;
        }
        if (mostLeftLevel(node.right, l + 1) == h)//检查node右子树是否能到最下层
        {
            //此时左子树是满树
            return (1 << (h - l)) + bs(node.right, l + 1, h);
        } else {
            //此时右子树是满树
            return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
        }

    }

    private int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        /*
         *        3
         *       / \
         *      9  20
         *    /  \
         *  15   7
         */
        Node head = new Node(3);
        head.left = new Node(9);
        head.right = new Node(20);
        head.left.left = new Node(15);
        head.left.right = new Node(7);

        NodeNum n = new NodeNum();
        System.out.println(n.nodeNum(head));
    }
}
