package Coding_Interviews;

/**
 * 平衡二叉树
 * 题目描述
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 *
 * https://www.nowcoder.com/practice/8b3b95850edb4115918ecebdf1b4d222?tpId=13&tqId=11192&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 具体解题思路如下。
 * Created by ZingBug on 2018/7/2.
 */
public class IsBalancedTree {
    private boolean solution1(TreeNode root)
    {
        //第一种方法，借助求二叉树的深度经验，在遍历树的每个结点的时候，调用函数getDepth得到它的左右子树的深度。
        // 如果每个结点的左右子树的深度相差都不超过1，按照定义它就是一棵平衡的二叉树。
        //注意：这种思路代码固然简洁，但由于一个结点会被重复遍历多次，这种思路的时间效率不高。
        if(root==null)
        {
            return true;
        }
        int left=getDepth(root.left);
        int right=getDepth(root.right);

        if(Math.abs(left-right)>1)
        {
            return false;
        }
        return solution1(root.left)&&solution1(root.right);
    }

    private int getDepth(TreeNode root)
    {
        if(root==null)
        {
            return 0;
        }
        int num=Math.max(getDepth(root.left),getDepth(root.right));
        return num+1;
    }

    private boolean solution2(TreeNode root)
    {
        //第二种方法，记录下来深度，不必遍历两次
        //我们用后序遍历的方式遍历二叉树的每一个结点，在遍历到一个结点之前我们就已经遍历了它的左右子树。
        // 只要在遍历每个结点的时候记录它的深度（某一结点的深度等于它到叶节点的路径的长度），我们就可以一边遍历一边判断每个结点是不是平衡的。
        Deepth d=new Deepth();
        return isBalanced(root,d);
    }

    private class Deepth{
        int val=0;
    }

    private boolean isBalanced(TreeNode root,Deepth d)
    {
        if(root==null)
        {
            d.val=0;
            return true;
        }
        Deepth left=new Deepth();
        Deepth right=new Deepth();
        if(isBalanced(root.left,left)&&isBalanced(root.right,right))
        {
            if(Math.abs(left.val-right.val)<=1)
            {
                d.val=Math.max(left.val,right.val)+1;
                return true;
            }
        }
        return false;
    }

    public boolean IsBalanced_Solution(TreeNode root)
    {
        return solution2(root);
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

        IsBalancedTree i=new IsBalancedTree();
        System.out.println(i.IsBalanced_Solution(root1));
    }




}
