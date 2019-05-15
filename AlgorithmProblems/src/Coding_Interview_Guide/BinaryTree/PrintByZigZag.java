package Coding_Interview_Guide.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 之字形打印链表
 * 另一种方法是用两个ArrayList，但不推荐用，因为ArrayList为动态数组，数组扩容是的复杂度是很高的
 * 还一种方法是用递归来做。我在Leetcode试题中做过了
 * 现在用的是宽度优先遍历，但注意点是如何知道该换行，所以这个地方就添加了last和nlast变量。
 * Page131
 * Created by ZingBug on 2018/11/13.
 */
public class PrintByZigZag {
    private void printByZigZag(Node head)
    {
        if(head==null)
        {
            return;
        }
        Deque<Node> deque=new LinkedList<>();
        boolean lr=true;
        Node last=head;//当前行的最后一个节点
        Node nlast=null;//下一行的最后一个节点
        deque.addFirst(head);

        while (!deque.isEmpty())
        {
            if(lr)
            {
                head=deque.pollFirst();
                if(head.right!=null)
                {
                    nlast=nlast==null?head.right:nlast;
                    deque.addLast(head.right);
                }
                if(head.left!=null)
                {
                    nlast=nlast==null?head.left:nlast;
                    deque.addLast(head.left);
                }
            }
            else
            {
                head=deque.pollLast();
                if(head.left!=null)
                {
                    nlast=nlast==null?head.left:nlast;
                    deque.addFirst(head.left);
                }
                if(head.right!=null)
                {
                    nlast=nlast==null?head.right:nlast;
                    deque.addFirst(head.right);
                }
            }
            System.out.print(head.value+" ");
            if(head==last&&!deque.isEmpty())
            {
                lr=!lr;
                last=nlast;
                nlast=null;
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

        PrintByZigZag p=new PrintByZigZag();
        p.printByZigZag(head);
    }
}
