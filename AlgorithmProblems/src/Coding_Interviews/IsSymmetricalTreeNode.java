package Coding_Interviews;

/**
 * 对称的二叉树
 * 题目描述
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 *
 * https://www.nowcoder.com/practice/ff05d44dfdb04e1d83bdbdab320efbcb?tpId=13&tqId=11211&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 逐层递归，来看是否对称。
 * Created by ZingBug on 2018/7/11.
 */
public class IsSymmetricalTreeNode {
    public boolean isSymmetrical(TreeNode pRoot)
    {
        return isSymmetrical(pRoot,pRoot);
    }
    private boolean isSymmetrical(TreeNode pRoot1,TreeNode pRoot2)
    {
        if(pRoot1==null&&pRoot2==null)
        {
            return true;
        }
        if(pRoot1==null||pRoot2==null)
        {
            return false;
        }
        if(pRoot1.val!=pRoot2.val)
        {
            return false;
        }
        return isSymmetrical(pRoot1.left,pRoot2.right)&&isSymmetrical(pRoot1.right,pRoot2.left);
    }

    public static void main(String[] args)
    {
        /**
         * root1
         *      1
         *    2   2
         *  4  3 3  4
         */
        TreeNode root1=new TreeNode(1);
        root1.left=new TreeNode(2);
        root1.right=new TreeNode(2);
        root1.left.left=new TreeNode(4);
        root1.left.right=new TreeNode(3);
        root1.right.left=new TreeNode(3);
        root1.right.right=new TreeNode(4);

        IsSymmetricalTreeNode i=new IsSymmetricalTreeNode();
        System.out.println(i.isSymmetrical(root1));
    }
}
