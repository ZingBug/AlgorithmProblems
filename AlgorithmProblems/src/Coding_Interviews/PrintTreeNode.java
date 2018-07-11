package Coding_Interviews;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 按之字形顺序打印二叉树
 * 题目描述
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 *
 * https://www.nowcoder.com/practice/91b69814117f4e8097390d107d2efbe0?tpId=13&tqId=11212&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 利用两个栈可以完成，一个栈负责奇数行，一个栈负责偶数行。
 * 类似的题目是 从上到下打印二叉树(PrintTreeFromTopToBottom)
 *
 * Created by ZingBug on 2018/7/11.
 */
public class PrintTreeNode {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot)
    {
        ArrayList<ArrayList<Integer>> lists=new ArrayList<>();
        if(pRoot==null)
        {
            return lists;
        }
        Stack<TreeNode> stack1=new Stack<>();//用来保存奇数行
        Stack<TreeNode> stack2=new Stack<>();//用来保存偶数行
        stack1.push(pRoot);
        while (!stack1.isEmpty()||!stack2.isEmpty())
        {
            ArrayList<Integer> list=new ArrayList<>();
            if(!stack1.isEmpty())
            {
                while (!stack1.isEmpty())
                {
                    TreeNode node=stack1.pop();
                    list.add(node.val);
                    //从左到右
                    if(node.left!=null)
                    {
                        stack2.push(node.left);
                    }
                    if(node.right!=null)
                    {
                        stack2.push(node.right);
                    }
                }
            }
            else if(!stack2.isEmpty())
            {
                while (!stack2.isEmpty())
                {
                    TreeNode node=stack2.pop();
                    list.add(node.val);
                    //从右到左
                    if(node.right!=null)
                    {
                        stack1.push(node.right);
                    }
                    if(node.left!=null)
                    {
                        stack1.push(node.left);
                    }
                }
            }
            lists.add(list);
        }
        return lists;
    }

    public static void main(String[] args)
    {
        PrintTreeNode p=new PrintTreeNode();
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

        ArrayList<ArrayList<Integer>> lists=p.Print(root1);
        for(ArrayList<Integer> list:lists)
        {
            for(int val:list)
            {
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }
}
