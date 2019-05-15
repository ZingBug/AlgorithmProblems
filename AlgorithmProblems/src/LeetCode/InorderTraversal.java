package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. Binary Tree Inorder Traversal
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 * 用栈来实现
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * Created by ZingBug on 2018/11/12.
 */
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root)
    {
        List<Integer> list=new ArrayList<>();
        if(root==null)
        {
            return list;
        }
        Stack<TreeNode> stack=new Stack<>();
        while (!stack.isEmpty()||root!=null)
        {
            if(root!=null)
            {
                stack.push(root);
                root=root.left;
            }
            else
            {
                TreeNode node=stack.pop();
                list.add(node.val);
                root=node.right;
            }
        }
        return list;
    }
}
