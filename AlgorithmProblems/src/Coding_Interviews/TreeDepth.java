package Coding_Interviews;

/**
 * 二叉树的深度
 * 题目描述
 * 输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * https://www.nowcoder.com/practice/435fb86331474282a3499955f0a41e8b?tpId=13&tqId=11191&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * Created by ZingBug on 2018/7/2.
 */
public class TreeDepth {
    private int solution1(TreeNode root)
    {
        //第一种方法，用递归来实现
        if(root==null)
        {
            return 0;
        }
        int num=Math.max(solution1(root.left),solution1(root.right));
        return num+1;
    }
    public int TreeDepth(TreeNode root)
    {
        return solution1(root);
    }

    public static void main(String[] args)
    {
        /**
         * root1
         *     1
         *    2  3
         *  4  5
         */
        TreeNode root1=new TreeNode(1);
        root1.left=new TreeNode(2);
        root1.right=new TreeNode(3);
        root1.left.left=new TreeNode(4);
        root1.left.right=new TreeNode(5);
        /**
         * root2
         *   2
         *  4  5
         */
        TreeNode root2=new TreeNode(2);
        root2.left=new TreeNode(4);
        root2.right=new TreeNode(5);

        TreeDepth t=new TreeDepth();
        System.out.println(t.TreeDepth(root2));
    }
}
