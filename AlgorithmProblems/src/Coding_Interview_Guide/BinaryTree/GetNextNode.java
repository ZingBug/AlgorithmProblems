package Coding_Interview_Guide.BinaryTree;

/**
 * 在二叉树中找到一个节点的后继节点，在二叉树的中序遍历序列中，node的下一个节点叫做node的后继节点。
 * 分为三种情况：
 * 1、如果该节点有右子树，则右子树就是后继节点
 * 2、如果没有右子树，则去看看该节点是否为其父节点的左孩子，如果是左孩子，则说明父节点就是该节点的后继节点。
 * 3、如果情况2一直网上寻找，移动到空节点都没发现符合节点，则返回null。
 * Page151
 *
 * Created by ZingBug on 2018/12/22.
 */
public class GetNextNode {
    private static class Node
    {
        int value;
        Node left;
        Node right;
        Node parent;
        public Node(int v)
        {
            value=v;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private Node getNextNode(Node node)
    {
        if(node==null)
        {
            return null;
        }
        else if(node.right!=null)
        {
            return node.right;
        }
        else
        {
            return getNext(node);
        }
    }

    private Node getNext(Node node)
    {
        if(node.parent==null)
        {
            return null;
        }
        else
        {
            if(node==node.parent.left)
            {
                return node.parent;
            }
            return getNext(node.parent);
        }
    }

    public static void main(String[] args)
    {
        /*
         *        3
         *       / \
         *      9  20
         *    /  \
         *  15   7
         */
        Node head=new Node(3);
        head.left=new Node(9);
        head.right=new Node(20);
        head.left.left=new Node(15);
        head.left.right=new Node(7);
        head.left.parent=head;
        head.right.parent=head;
        head.left.left.parent=head.left;
        head.left.right.parent=head.left;

        GetNextNode g=new GetNextNode();
        System.out.println(g.getNextNode(head.right));
    }
}
