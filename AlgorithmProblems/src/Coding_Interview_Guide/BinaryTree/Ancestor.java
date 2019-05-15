package Coding_Interview_Guide.BinaryTree;

/**
 * 在二叉树中找到两个节点的最近公共祖先
 * Page153
 * Created by ZingBug on 2018/12/22.
 */
public class Ancestor {
    public String a="1";
    private Node lowestAncestor(Node head, Node o1, Node o2) {
        //通过后序遍历
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        Node l = lowestAncestor(head.left, o1, o2);
        Node r = lowestAncestor(head.right, o1, o2);
        if (l != null && r != null) {
            return head;
        }
        return l==null?r:l;
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


        Ancestor a=new Ancestor();
        System.out.println(a.lowestAncestor(head,head.left.right,head.right));


    }


}
