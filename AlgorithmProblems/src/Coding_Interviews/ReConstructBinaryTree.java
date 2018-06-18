package Coding_Interviews;

/**
 * 重建二叉树
 * 题目描述
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=13&tqId=11157&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class ReConstructBinaryTree {
    private static class TreeNode
    {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x)
        {
            val=x;
        }
    }
    private TreeNode reConstructBinaryTree(int[] pre,int[] in)
    {
        if(pre==null)
        {
            return null;
        }
        int len=pre.length;
        TreeNode root=new TreeNode(pre[0]);

        if(len==1)
        {
            root.left=null;
            root.right=null;
            return root;
        }

        //找到中序的跟位置
        int rootval=root.val;
        int index=0;
        for(int i=0;i<len;i++)
        {
            if(rootval==in[i])
            {
                index=i;
                break;
            }
        }
        //构建左子树
        if(index>0)
        {
            //存在左子树
            int[] pr=new int[index];
            int[] ino=new int[index];
            //左子树的中序
            for(int i=0;i<index;i++)
            {
                ino[i]=in[i];
            }
            //左子数的先序
            for(int i=0;i<index;i++)
            {
                pr[i]=pre[i+1];
            }
            root.left=reConstructBinaryTree(pr,ino);
        }
        else
        {
            //没有左子树
            root.left=null;
        }

        //构建右子树
        if(len-index-1>0)
        {
            //存在右子树
            int[] pr=new int[len-index-1];
            int[] ino=new int[len-index-1];
            //右子树的中序
            for(int i=0;i<len-index-1;i++)
            {
                ino[i]=in[i+index+1];
            }
            //右子树的先序
            for(int i=0;i<len-index-1;i++)
            {
                pr[i]=pre[i+index+1];
            }
            root.right=reConstructBinaryTree(pr,ino);
        }
        else
        {
            root.right=null;
        }
        return root;

    }
    public static void main(String[] args)
    {
        int[] pre={1,2,4,7,3,5,6,8};
        int[] in={4,7,2,1,5,3,8,6};
        ReConstructBinaryTree r=new ReConstructBinaryTree();
        TreeNode root=r.reConstructBinaryTree(pre,in);
        System.out.print(root.val);
    }
}
