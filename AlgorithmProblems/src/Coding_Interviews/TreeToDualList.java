package Coding_Interviews;


import java.util.Stack;

/**
 * 二叉搜索树与双向链表
 * 题目描述
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * https://www.nowcoder.com/practice/947f6eb80d944a84850b0538bf0ec3a5?tpId=13&tqId=11179&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 主要有两种做法，一种是递归，递归每一个左右子树。另一种方法是利用循环，来仿照中序遍历。毕竟这个顺序就是中序遍历的顺序。
 * 对于递归方法来说
 * 1、将左子树构成双链表，并返回该链表的头节点(左子树最左边的节点)
 * 2、定位到左链表的最后一个节点(左子树最右边的节点)
 * 3、如果左子树链表不为空，则将当前pRootOfTree追加到左子树链表后
 * 4、将右子树构造成双向链表，并返回链表头结点(右子树最左边的节点)
 * 5、如果右子树链表不为空，将右子树链表追加到当前pRootOfTree后
 * 6，根据左子树链表是否为空返回的整体双向链表的头节点
 *
 * Created by ZingBug on 2018/6/24.
 */
public class TreeToDualList {
    private TreeNode solution1(TreeNode pRootOfTree)
    {
        //第一种方法，用递归方式实现
        if(pRootOfTree==null)//假设根节点为空，则返回空
        {
            return null;
        }
        if(pRootOfTree.right==null&&pRootOfTree.left==null)//假设只有一个根节点，则返回根节点
        {
            return pRootOfTree;
        }
        //将左子树构成双链表，并返回该链表头结点left
        TreeNode left=solution1(pRootOfTree.left);

        TreeNode left1=left;//新建一个临时节点，用于找到左子树链表的最右节点
        while (left1!=null&&left1.right!=null)
        {
            left1=left1.right;
        }
        if(left!=null)//当左子树链表不为空时
        {
            left1.right=pRootOfTree;//左子树链表的最后一个节点的右指针指向当前pRootOfTree节点
            pRootOfTree.left=left1;//当前pRootOfTree节点的左指针指向左子树链表的最后一个节点
        }

        TreeNode right=solution1(pRootOfTree.right);
        if(right!=null)//当右子树链表不为空时
        {
            pRootOfTree.right=right;//当前pRootOfTree节点的右指针指向右子树链表的第一个节点
            right.left=pRootOfTree;//右子树链表的第一个节点的左指针指向当前pRootOfTree节点
        }

        return left!=null?left:pRootOfTree;//返回链表
    }
    private TreeNode solution2(TreeNode pRootOfTree)
    {
        //第二种方法，用循环方式实现
        if(pRootOfTree==null)
        {
            return null;
        }
        Stack<TreeNode> stack=new Stack<>();
        TreeNode p=pRootOfTree;//临时节点，用来遍历树，初始值为跟节点root
        TreeNode rootList=null;//用于记录双向链表的头节点
        TreeNode node=null;//用于保存中序遍历序列的上一个节点，即p的上一个节点
        boolean isFirst=true;//判断是否为头节点（二叉树的最左节点）
        while (p!=null||!stack.isEmpty())
        {
            while (p!= null)
            {
                stack.push(p);
                p=p.left;
            }
            p=stack.pop();
            if(isFirst)
            {
                rootList=p;
                node=rootList;
                isFirst=false;
            }
            else
            {
                node.right=p;
                p.left=node;
                node=node.right;
            }
            p=p.right;
        }
        return rootList;

    }
    public TreeNode Convert(TreeNode pRootOfTree)
    {
        return solution2(pRootOfTree);
    }
}
