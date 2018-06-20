package Coding_Interviews;

/**
 * 树的子结构
 * 题目描述
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 *
 * https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13&tqId=11170&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路：
 * 1、找到A中和B的根节点相同的节点，然后进行判断是否相同。
 * 2、如果不同再拿A的左子树与B进行比较。
 * 3、如果仍不同再拿A的右子树与B进行比较。
 * 4、如果仍未找到，则A中不包含B。
 *
 * 判断两个根节点相同的两个树是否包含(AhasB)：
 * 1、先判断B，如果B为空说明包含。
 * 2、再判断A，如果A为空说明不包含。
 * 3、如果A的值与B的值相同然后继续进行此判断。
 *
 * Created by ZingBug on 2018/6/20.
 */
public class HasSubTree {
    public boolean HasSubtree(TreeNode root1,TreeNode root2)
    {
        boolean result=false;

        if(root1!=null&&root2!=null)
        {
            if(root1.val==root2.val)
            {
                //如果根节点相同，判断根节点下面的树
                result=AhasB(root1,root2);
            }
            if(!result)//这块不能用else，因为防止出现根节点不同，但根节点下面的树包含B的情况
            {
                result=HasSubtree(root1.left,root2)||HasSubtree(root1.right,root2);
            }
        }
        return result;
    }
    private boolean AhasB(TreeNode root1,TreeNode root2)
    {
        if(root2==null)
        {
            return true;
        }
        if(root1==null)
        {
            return false;
        }
        if(root1.val==root2.val)
        {
            return AhasB(root1.left,root2.left)&&AhasB(root1.right,root2.right);
        }
        return false;
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

        HasSubTree h=new HasSubTree();
        System.out.println(h.HasSubtree(root1,root2));
    }
}
