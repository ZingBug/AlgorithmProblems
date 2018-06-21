package Coding_Interviews;

/**
 * 二叉搜索树的后序遍历序列
 * 题目描述
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * https://www.nowcoder.com/practice/a861533d45854474ac791d90e447bafd?tpId=13&tqId=11176&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 二叉查找树(Binary Search Tree)
 * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
 *
 * 解题思路
 * 对于一个二叉树的后序遍历序列来说，最后一个数一定是根节点，然后前面的数中，从最开始到第一个大于根节点的数都是左子树中的数，而后面到倒数第二个数应该都是大于根节点的，是右子树。
 * 如果后面的数中有小于根节点的，那么说明这个序列不是二叉搜索树的后序遍历序列，依次递归判断
 *
 * Created by ZingBug on 2018/6/21.
 */
public class VerifySquenceOfBST {
    public boolean VerifySquenceOfBST(int[] sequence)
    {

        int len=sequence.length;
        if(len==0)
        {
            return false;
        }
        return verify(sequence,0,len-1);
    }

    private boolean verify(int[] sequence,int start,int end)
    {
        if(start>=end)
        {
            return true;
        }
        int root=sequence[end];
        int index=0;
        for(int i=start;i<end;i++)
        {
            if(sequence[i]>root)
            {
                index=i;
                for(int j=index;j<end;j++)
                {
                    if(sequence[j]<root)
                    {
                        return false;
                    }
                }
                break;
            }
        }
        return verify(sequence,start,index-1)&&verify(sequence,index,end-1);
    }

    public static void main(String[] args)
    {
        VerifySquenceOfBST v=new VerifySquenceOfBST();
        int[] sequence1={2,3,9,5,15,19,18,12};
        int[] sequence2={4,6,7,5};
        int[] sequence3={7,4,5,6};
        System.out.println(v.VerifySquenceOfBST(sequence3));
    }
}
