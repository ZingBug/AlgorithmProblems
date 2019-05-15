package Coding_Interview_Guide.BinaryTree;
import java.util.Stack;

/**
 * 调整搜索二叉树中两个错误的节点
 * 一个二叉树原本是搜索二叉树，但是其中有两个节点调换了位置，使得这颗二叉树不再是搜索二叉树，请找到这两个错误节点并返回。
 * Page134
 * 解题思路
 * 一般搜索二叉树的中序遍历都是升序的，所以如果有两个节点错误的话，就肯定有降序的位置。
 * 第一个错误节点为第一次降序时较大的节点，第二个错误节点为最后一次降序时较小的节点。
 * Created by ZingBug on 2018/12/5.
 */
public class GetTwoErrNodes {
    private Node[] getTwoErrNodes(Node head)
    {
        Node[] errs=new Node[2];
        if(head==null)
        {
            return errs;
        }
        Stack<Node> stack=new Stack<>();//中序遍历
        Node pre=null;
        while (!stack.isEmpty()||head!=null)
        {
            if(head!=null)
            {
                stack.push(head);
                head=head.left;
            }
            else
            {
                head=stack.pop();
                if(pre!=null&&head.value<pre.value)
                {
                    errs[0]=errs[0]==null?pre:errs[0];
                    errs[1]=head;
                }
                pre=head;
                head=head.right;
            }
        }
        return errs;
    }
    public static void main(String[] args)
    {
        /*
         *     9
         *    / \
         *   21  20
         *     /  \
         *    15   7
         */
        Node head=new Node(9);
        head.left=new Node(21);
        head.right=new Node(20);
        head.right.left=new Node(15);
        head.right.right=new Node(7);

        GetTwoErrNodes g=new GetTwoErrNodes();
        Node[] err=g.getTwoErrNodes(head);
        for(Node node:err)
        {
            System.out.println(node.value);
        }
    }
}
