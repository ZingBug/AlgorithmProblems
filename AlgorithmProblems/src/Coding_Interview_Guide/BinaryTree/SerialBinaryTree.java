package Coding_Interview_Guide.BinaryTree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 * 一种方法是通过先序遍历序列化
 * 另一种方法是通过宽度优先遍历实现序列化，按层打印
 * Page103
 *
 * Created by ZingBug on 2018/11/14.
 */
public class SerialBinaryTree {
    //通过先序顺序来序列化
    private String serialByPre(Node head)
    {
        if(head==null)
        {
            return "#!";
        }
        String res=head.value+"!";
        res+=serialByPre(head.left);
        res+=serialByPre(head.right);
        return res;
    }

    private Node reconByPreString(String res)
    {
        String[] values=res.split("!");
        Queue<String> queue=new LinkedList<>();
        for(String value:values)
        {
            queue.offer(value);
        }
        return reconPreOrder(queue);
    }

    private Node reconPreOrder(Queue<String> queue)
    {
        String value=queue.poll();
        if(value.equals("#"))
        {
            return null;
        }
        Node node=new Node(Integer.valueOf(value));
        node.left=reconPreOrder(queue);
        node.right=reconPreOrder(queue);
        return node;
    }
    //通过按层打印来序列化
    private String serialByLevel(Node head)
    {
        if(head==null)
        {
            return "#!";
        }
        Queue<Node> queue=new LinkedList<>();
        queue.offer(head);
        String res=head.value+"!";
        while (!queue.isEmpty())
        {
            head=queue.poll();
            if(head.left!=null)
            {
                res+=(head.left.value+"!");
                queue.offer(head.left);
            }
            else
            {
                res+="#!";
            }
            if(head.right!=null)
            {
                res+=(head.right.value+"!");
                queue.offer(head.right);
            }
            else
            {
                res+="#!";
            }
        }
        return res;
    }

    private Node reconByLevelString(String res)
    {
        String[] values=res.split("!");
        int index=0;
        Node head=reconLevelOrder(values[index++]);
        Queue<Node> queue=new LinkedList<>();
        if(head!=null)
        {
            queue.offer(head);
        }
        Node node=null;
        while (!queue.isEmpty())
        {
            node=queue.poll();
            node.left=reconLevelOrder(values[index++]);
            node.right=reconLevelOrder(values[index++]);
            if(node.left!=null)
            {
                queue.offer(node.left);
            }
            if(node.right!=null)
            {
                queue.offer(node.right);
            }
        }

        return head;

    }

    private Node reconLevelOrder(String res)
    {
        if(res.equals("#"))
            return null;
        return new Node(Integer.valueOf(res));
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

        SerialBinaryTree s=new SerialBinaryTree();
        String res=s.serialByLevel(head);
        Node node=s.reconByLevelString(res);
        System.out.println(res);

    }
}
