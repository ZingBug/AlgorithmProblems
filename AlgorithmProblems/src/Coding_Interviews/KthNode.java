package Coding_Interviews;

/**
 * 二叉搜索树的第k个节点
 * 题目描述
 * 给定一颗二叉搜索树，请找出其中的第k小的结点。例如， 5 / \ 3 7 /\ /\ 2 4 6 8 中，按结点数值大小顺序第三个结点的值为4。
 *
 * https://www.nowcoder.com/practice/ef068f602dde4d28aab2b210e859150a?tpId=13&tqId=11215&tPage=4&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 二叉搜索树的中序遍历数值是递增排序的，这块只需要中序遍历即可。
 *
 * Created by ZingBug on 2018/7/11.
 */
public class KthNode {
    private int index=0;
    private int k=0;
    public TreeNode KthNode(TreeNode pRoot,int k)
    {
        this.k=k;
        return kthNode(pRoot);
    }
    private TreeNode kthNode(TreeNode root)
    {
        if(root==null)
        {
            return null;
        }
        TreeNode target=null;
        if(root.left!=null)
        {
            target=kthNode(root.left);
        }
        if(target==null)
        {
            index++;
            if(index==k)
            {
                target=root;
            }

        }
        if(target==null&&root.right!=null)
        {
            target=kthNode(root.right);
        }

        return target;
    }

    public static void main(String[] args)
    {
        /**
         * root1
         *      5
         *    3   7
         *  2  4 6  8
         */
        TreeNode root1=new TreeNode(5);
        root1.left=new TreeNode(3);
        root1.right=new TreeNode(7);
        root1.left.left=new TreeNode(2);
        root1.left.right=new TreeNode(4);
        root1.right.left=new TreeNode(6);
        root1.right.right=new TreeNode(8);

        KthNode k=new KthNode();
        TreeNode node=k.KthNode(root1,3);
        System.out.println(node.val);
    }
}
