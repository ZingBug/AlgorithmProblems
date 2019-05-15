package LeetCode;

/**
 * Created by ZingBug on 2018/12/5.
 */
public class SameTree {
    public boolean isSameTree(TreeNode p,TreeNode q)
    {
        if(p==null&&q==null)
        {
            return true;
        }
        if(p==null||q==null)
        {
            return false;
        }
        return (p.val==q.val)&&isSameTree(p.right,q.right)&&isSameTree(p.left,q.left);
    }

    public static void main(String[] args)
    {
        TreeNode head1=new TreeNode(1);
        head1.left=new TreeNode(2);
        head1.right=new TreeNode(3);
        TreeNode head2=new TreeNode(1);
        head2.left=new TreeNode(2);
        head2.right=new TreeNode(4);

        SameTree s=new SameTree();
        System.out.println(s.isSameTree(head1,head2));
    }
}
