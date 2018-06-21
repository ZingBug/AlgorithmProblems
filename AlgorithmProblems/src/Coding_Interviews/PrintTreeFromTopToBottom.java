package Coding_Interviews;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 从上到下打印二叉树
 * 题目描述
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 *
 * https://www.nowcoder.com/practice/7fe2212963db4790b57431d9ed259701?tpId=13&tqId=11175&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 采用一个例子说明：
 *      1
 *    2   3
 *  4  5 6  7
 *
 * 对于第一层，只有根节点 “1”,第二层有节点“2”和“3”。
 * 从根节点分析，为了能够接下来打印节点为1的两个子节点，我们应该在遍历到该节点时把值为2和3的两个节点保存到一个容器里。
 * 按照从左向右打印的要求，取出2，保存其两个子节点“4”和“5”。随后取出3,保存其子节点“6”和“7”。
 * 可以看出，这是一个先进先出的容器。使用队列进行保存。
 *
 * Created by ZingBug on 2018/6/21.
 */
public class PrintTreeFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root)
    {
        Queue<TreeNode> queue=new LinkedBlockingQueue<>();
        ArrayList<Integer> list=new ArrayList<>();
        if(root==null)
        {
            return list;
        }
        ((LinkedBlockingQueue<TreeNode>) queue).offer(root);
        while (!queue.isEmpty())
        {
            TreeNode node=queue.poll();
            if(node.left!=null)
            {
                ((LinkedBlockingQueue<TreeNode>) queue).offer(node.left);
            }
            if(node.right!=null)
            {
                ((LinkedBlockingQueue<TreeNode>) queue).offer(node.right);
            }
            list.add(node.val);
        }
        return list;
    }

    public static void main(String[] args)
    {
        PrintTreeFromTopToBottom p=new PrintTreeFromTopToBottom();
        /**
         * root1
         *      1
         *    2   3
         *  4  5 6  7
         */
        TreeNode root1=new TreeNode(1);
        root1.left=new TreeNode(2);
        root1.right=new TreeNode(3);
        root1.left.left=new TreeNode(4);
        root1.left.right=new TreeNode(5);
        root1.right.left=new TreeNode(6);
        root1.right.right=new TreeNode(7);
        ArrayList<Integer> list=p.PrintFromTopToBottom(root1);
        for(int val:list)
        {
            System.out.print(val+" ");
        }
    }
}
