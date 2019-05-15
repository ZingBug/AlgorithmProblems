package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal
 * Created by ZingBug on 2018/11/13.
 */
public class ZigzagLevelOrder {
    //用栈的方法，时间比较慢
    private List<List<Integer>> zigzagLevelOrder1(TreeNode root)
    {
        List<List<Integer>> lists=new ArrayList<>();
        if(root==null)
        {
            return lists;
        }
        Stack<TreeNode> s1=new Stack<>();
        Stack<TreeNode> s2=new Stack<>();
        s1.push(root);

        List<Integer> list=new ArrayList<>();
        while (!s1.isEmpty()||!s2.isEmpty())
        {
            while (!s1.empty())
            {
                TreeNode node=s1.pop();
                if(node.left!=null)
                {
                    s2.push(node.left);
                }
                if(node.right!=null)
                {
                    s2.push(node.right);
                }
                list.add(node.val);
                if(s1.isEmpty())
                {
                    lists.add(list);
                    list=new ArrayList<>();
                }
            }
            while (!s2.isEmpty())
            {
                TreeNode node=s2.pop();
                if(node.right!=null)
                {
                    s1.push(node.right);
                }
                if(node.left!=null)
                {
                    s1.push(node.left);
                }
                list.add(node.val);
                if(s2.isEmpty())
                {
                    lists.add(list);
                    list=new ArrayList<>();
                }
            }
        }
        return lists;
    }
    //用递归的方法
    private List<List<Integer>> zigzagLevelOrder2(TreeNode root)
    {
        List<List<Integer>> lists=new ArrayList<>();
        travel(lists,0,root);
        return lists;
    }
    private void travel(List<List<Integer>> lists,int level,TreeNode cur)
    {
        if(cur==null)
        {
            return;
        }
        if(lists.size()<=level)
        {
            lists.add(new ArrayList<>());
        }
        if(level%2==0)
        {
            lists.get(level).add(cur.val);
        }
        else
        {
            lists.get(level).add(0,cur.val);
        }
        travel(lists,level+1,cur.left);
        travel(lists,level+1,cur.right);
    }

    public static void main(String[] args)
    {
        TreeNode head=new TreeNode(3);
        head.left=new TreeNode(9);
        head.right=new TreeNode(20);
        head.right.left=new TreeNode(15);
        head.right.right=new TreeNode(7);

        ZigzagLevelOrder z=new ZigzagLevelOrder();
        List<List<Integer>> lists=z.zigzagLevelOrder1(head);
        for(List<Integer> list:lists)
        {
            for(int val:list)
            {
                System.out.print(val+" ");
            }
            System.out.println();
        }
    }

}
