package Coding_Interview_Guide.BinaryTree;

import java.util.Stack;

/**
 * 分别用非递归方式实现二叉树先序、中序和后序遍历
 * 利用栈来模拟递归过程
 * Page88
 * Created by ZingBug on 2018/11/11.
 */
public class OrderUnRecur {

    //先序
    private void preOrderUnRecur(Node head)
    {
        System.out.print("pre-order: ");
        Stack<Node> stack=new Stack<>();
        stack.push(head);
        while (!stack.isEmpty())
        {
            Node node=stack.pop();
            System.out.print(node.value+" ");
            if(node.right!=null)
            {
                stack.push(node.right);
            }
            if(node.left!=null)
            {
                stack.push(node.left);
            }
        }
    }
    //中序
    private void inOrderUnRecur(Node head)
    {
        System.out.print("in-order: ");
        Stack<Node> stack=new Stack<>();
        while (!stack.isEmpty()||head!=null)
        {
            if(head!=null)
            {
                stack.push(head);
                head=head.left;//左遍历
            }
            else
            {
                Node node=stack.pop();
                System.out.print(node.value+" ");
                head=node.right;
            }
        }
    }
    //后序
    private void posOrderUnRecur1(Node head)
    {
        //第一种方法，利用两个栈来实现
        System.out.print("pos-order: ");
        Stack<Node> s1=new Stack<>();
        Stack<Node> s2=new Stack<>();
        s1.push(head);
        while (!s1.isEmpty())
        {
            Node node=s1.pop();
            if(node.left!=null)
            {
                s1.push(node.left);
            }
            if(node.right!=null)
            {
                s1.push(node.right);
            }
            s2.push(node);
        }
        while (!s2.isEmpty())
        {
            System.out.print(s2.pop().value+" ");
        }
    }
    //后序
    private void posOrderUnRecur2(Node head)
    {
        System.out.print("pos-order: ");
        Stack<Node> stack=new Stack<>();
        stack.push(head);
        Node h=head;
        Node c=null;
        while (!stack.isEmpty())
        {
            c=stack.peek();
            if(c.left!=null&&h!=c.left&&h!=c.right)
            {
                stack.push(c.left);
            }
            else if(c.right!=null&&h!=c.right)
            {
                stack.push(c.right);
            }
            else
            {
                System.out.print(stack.pop().value+" ");
                h=c;
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

        OrderUnRecur o=new OrderUnRecur();
        o.posOrderUnRecur2(head);
    }
}
