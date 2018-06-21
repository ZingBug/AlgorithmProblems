package Coding_Interviews;
import java.util.ArrayList;

/**
 * 二叉树中和未某一值的路径
 * 题目描述
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 *
 * https://www.nowcoder.com/practice/b736e784e3e34731af99065031301bca?tpId=13&tqId=11177&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 递归找到每一条路径的叶子节点，然后判断此条路径的所有节点值合是否等于目标值。如果是的话，就将这条路径压入链表中。
 * 注意，在递归每一个节点的时候，若当前节点不是叶子节点，且前面的值已经超过目标值，则没必要往下进行了。
 *
 * Created by ZingBug on 2018/6/21.
 */
public class FindPathInTree {
    private ArrayList<ArrayList<Integer>> lists=new ArrayList<>();
    private ArrayList<Integer> list=new ArrayList<>();//用于保存路径
    int sum=0;
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target)
    {
        if(root==null)
        {
            return lists;
        }
        boolean isLeaf=root.left==null&&root.right==null;//判断是否为叶子节点
        sum+=root.val;
        list.add(root.val);
        if(sum==target&&isLeaf)
        {
            //如果在叶子节点处达到目标值
            ArrayList<Integer> path=new ArrayList<>();
            for(int i=0;i<list.size();i++)
            {
                path.add(list.get(i));
            }
            lists.add(path);
        }
        //如果在非叶子节点中未达到目标值
        if(sum<target&&root.left!=null)
        {
            FindPath(root.left,target);
        }
        if(sum<target&&root.right!=null)
        {
            FindPath(root.right,target);
        }
        sum-=root.val;//去除当前节点
        list.remove(list.size()-1);


        return lists;
    }

    public static void main(String[] args)
    {
        FindPathInTree f=new FindPathInTree();
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
        ArrayList<ArrayList<Integer>> lists=f.FindPath(root1,7);
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
