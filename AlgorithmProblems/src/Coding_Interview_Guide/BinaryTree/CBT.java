package Coding_Interview_Guide.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一棵树是否为完全二叉树
 * 按层遍历二叉树，从每层的左边向右边依次遍历所有的节点
 * 如果当前节点有右孩子，但没有左孩子，直接返回false
 * 如果当前节点并不是左右孩子全有，那之后的节点必须都是叶子节点，否则返回false
 * 遍历过程中如果不返回false，遍历结束后返回true
 * Created by ZingBug on 2018/12/20.
 */
public class CBT {
    private boolean isCBT(Node head)
    {
        if(head==null)
        {
            return true;
        }
        Queue<Node> queue=new LinkedList<>();
        queue.offer(head);
        boolean leaft=false;
        while (!queue.isEmpty())
        {
            Node node=queue.poll();
            Node left=node.left;
            Node right=node.right;

            if((left==null&&right!=null)||(leaft&&(left!=null||right!=null)))
            {
                return false;
            }
            if(left!=null)
            {
                queue.offer(left);
            }
            if(right!=null)
            {
                queue.offer(right);
            }
            else
            {
                leaft=true;
            }
        }
        return true;
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

        CBT c=new CBT();
        System.out.println(c.isCBT(head));
    }
}
