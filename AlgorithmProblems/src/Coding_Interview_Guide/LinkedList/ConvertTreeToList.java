package Coding_Interview_Guide.LinkedList;

import java.util.LinkedList;
import java.util.List;

/**
 * 将搜索二叉树转换为双向链表
 * Page74
 * Created by ZingBug on 2018/10/30.
 */
public class ConvertTreeToList {

    //用队列等容器收集二叉树中序遍历结果，时间复杂度为O(n)，空间复杂度为O(n)
    private DoubleNode convert1(DoubleNode head)
    {
        if(head==null)
        {
            return null;
        }
        List<DoubleNode> list=new LinkedList<>();
        inOrderToList(head,list);

        DoubleNode cur=((LinkedList<DoubleNode>) list).poll();
        DoubleNode pre=null;
        DoubleNode h=cur;

        while (!list.isEmpty())
        {
            pre=cur;
            cur=((LinkedList<DoubleNode>) list).poll();
            cur.left=pre;
            pre.right=cur;
        }
        cur.right=null;
        return h;
    }

    private void inOrderToList(DoubleNode head,List<DoubleNode> list)
    {
        if(head==null)
        {
            return;
        }
        inOrderToList(head.left,list);
        list.add(head);
        inOrderToList(head.right,list);
    }

    //利用递归来解决，时间复杂度为O(n)，空间复杂度为O(h)，其中h为树的高度
    private DoubleNode convert2(DoubleNode head)
    {
        if(head==null)
        {
            return null;
        }
        DoubleNode last=process(head);
        head=last.right;
        last.right=null;
        return head;

    }
    private DoubleNode process(DoubleNode head)
    {
        if(head==null)
        {
            return null;
        }
        DoubleNode leftE=process(head.left);//左边结束
        DoubleNode rightE=process(head.right);//右边结束
        DoubleNode leftS=leftE==null?null:leftE.right;//左边开始
        DoubleNode rightS=rightE==null?null:rightE.right;//右边开始

        if(leftE!=null&&rightE!=null)
        {
            leftE.right=head;
            head.left=leftE;
            head.right=rightS;
            rightS.left=head;
            rightE.right=leftS;
            return rightE;
        }
        else if(leftE!=null)
        {
            leftE.right=head;
            head.left=leftE;
            head.right=leftS;
            return head;
        }
        else if(rightE!=null)
        {
            head.right=rightS;
            rightS.left=head;
            rightE.right=head;
            return rightE;
        }
        else
        {
            head.right=head;
            return head;
        }

    }

    public static void main(String[] args)
    {
        DoubleNode head=new DoubleNode(6);
        head.left=new DoubleNode(4);
        head.left.left=new DoubleNode(2);
        head.left.right=new DoubleNode(5);
        head.right=new DoubleNode(7);
        head.right.right=new DoubleNode(9);

        ConvertTreeToList c=new ConvertTreeToList();
        DoubleNode node=c.convert2(head);
        while (node!=null)
        {
            System.out.print(node.value+" ");
            node=node.right;
        }
    }
}
