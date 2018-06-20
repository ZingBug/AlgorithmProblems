package Coding_Interviews;

/**
 * 二叉树的镜像
 * 题目描述
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *
 * 输入描述:
 * 二叉树的镜像定义：源二叉树
 *     	    8
 *     	   /  \
 *     	  6   10
 *     	 / \  / \
 *     	5  7 9 11
 *     	镜像二叉树
 *     	    8
 *     	   /  \
 *     	  10   6
 *     	 / \  / \
 *     	11 9 7  5
 *
 * https://www.nowcoder.com/practice/564f4c26aa584921bc75623e48ca3011?tpId=13&tqId=11171&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 逐层递归来交换左右节点
 *
 * Created by ZingBug on 2018/6/20.
 */
public class TreeMirror {
    public void Mirror(TreeNode root)
    {
        TreeNode head=root;
        exchange(head);

    }
    private void exchange(TreeNode root)
    {
        if(root!=null)
        {
            TreeNode temp=root.left;
            root.left=root.right;
            root.right=temp;
            exchange(root.left);
            exchange(root.right);
        }
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


        TreeMirror t=new TreeMirror();
        t.Mirror(root1);
        System.out.println(root1.val);//通过断点来查看结果树
    }
}
