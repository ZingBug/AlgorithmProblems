package Coding_Interview_Guide.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层打印二叉树
 * 利用队列实现广度优先遍历即可，注意换行。
 * Page129
 * Created by ZingBug on 2018/11/13.
 */
public class PrintByLevel {
    private void printByLevel(Node head)
    {
        if(head==null)
        {
            return;
        }
        Queue<Node> queue=new LinkedList<>();
        queue.offer(head);
        Node last=head;
        Node nlast=null;
        while (!queue.isEmpty())
        {
            head=queue.poll();
            if(head.left!=null)
            {
                nlast=head.left;
                queue.offer(head.left);
            }
            if(head.right!=null)
            {
                nlast=head.right;
                queue.offer(head.right);
            }
            System.out.print(head.value+" ");
            if(last==head)
            {
                last=nlast;
                System.out.println();
            }

        }
    }

    public static void main(String[] args)
    {
        /*
         *     3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         */
        Node head=new Node(3);
        head.left=new Node(9);
        head.right=new Node(20);
        head.right.left=new Node(15);
        head.right.right=new Node(7);

        PrintByLevel p=new PrintByLevel();
        p.printByLevel(head);
    }
}
