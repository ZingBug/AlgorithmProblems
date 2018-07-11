package Coding_Interviews;

/**
 * 二叉树的下一个节点
 * 题目描述
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 *
 * https://www.nowcoder.com/practice/9023a0c988684a53960365b889ceaf5e?tpId=13&tqId=11210&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 分几种情况分开讨论。比如有没有右子节点。
 * Created by ZingBug on 2018/7/10.
 */
public class GetNextInTreeLinkNode {

    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
         if(pNode==null)
         {
             return null;
         }

         //如果有右子树，那么它的下一个节点就是它的右子树种的最左子节点
         if (pNode.right!=null)
         {
             TreeLinkNode right=pNode.right;
             while (right.left!=null)
             {
                 right=right.left;
             }
             return right;
         }
         else if(pNode.next!=null)//如果节点有父节点
         {
             //如果节点是它父节点的左子节点，那么它下一个节点就是它的父子节点
             //如果节点是它父节点的左子节点，就需要一直向上遍历，找到一个是它父节点的左子节点的节点。
             //如果节点是它父节点的左子节点，并且向上遍历没有左子节点，那就返回null
             TreeLinkNode current=pNode;
             TreeLinkNode parent=pNode.next;
             while (parent!=null&&current==parent.right)
             {
                 current=parent;
                 parent=parent.next;
             }
             return parent;
         }
         return null;
    }
}
