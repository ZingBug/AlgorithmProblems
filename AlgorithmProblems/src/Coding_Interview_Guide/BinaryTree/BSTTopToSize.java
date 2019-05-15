package Coding_Interview_Guide.BinaryTree;

import java.util.HashMap;
import java.util.Map;

/**
 * 找到二叉树中符合搜索二叉树条件的最大拓扑结构
 * 给定一棵二叉树的头节点head，已知所有节点的值都不一样，返回其中最大的且符合搜索二叉树条件的最大拓扑结构的大小。
 * Page119
 * Created by ZingBug on 2018/12/5.
 */
public class BSTTopToSize {
    //第一种方法，时间复杂度O(N2)，需要对每个节点都进行检查
    private int bstTopSize1(Node head)
    {
        if(head==null)
        {
            return 0;
        }
        int maxLen=maxTopo(head,head);
        maxLen=Math.max(maxLen,bstTopSize1(head.left));
        maxLen=Math.max(maxLen,bstTopSize1(head.right));
        return maxLen;

    }
    private int maxTopo(Node h,Node n)
    {
        if(h!=null&&n!=null&&isBSTNode(h,n))
        {
            return maxTopo(h,n.left)+maxTopo(h,n.right)+1;
        }
        return 0;
    }

    private boolean isBSTNode(Node h,Node n)
    {
        if(h==null)
        {
            return false;
        }
        if(h==n)
        {
            return true;
        }
        return isBSTNode(h.value>n.value?h.left:h.right,n);
    }

    private class Record{
        public int l;
        public int r;
        public Record(int left,int right)
        {
            this.l=left;
            this.r=right;
        }
    }

    private int bstTopSize2(Node head)
    {
        Map<Node,Record> map=new HashMap<>();
        return posOrder(head,map);
    }
    private int posOrder(Node h,Map<Node,Record> map)
    {
        if(h==null)
        {
            return 0;
        }
        int ls=posOrder(h.left,map);
        int rs=posOrder(h.right,map);
        modifyMap(h.left,h.value,map,true);
        modifyMap(h.right,h.value,map,false);
        Record lr=map.get(h.left);
        Record rr=map.get(h.right);
        int lbst=lr==null?0:lr.l+lr.r+1;
        int rbst=rr==null?0:rr.l+rr.r+1;
        map.put(h,new Record(lbst,rbst));
        return Math.max(lbst+rbst+1,Math.max(ls,rs));
    }

    private int modifyMap(Node n,int v,Map<Node,Record> m,boolean s)
    {
        if(n==null||(!m.containsKey(n)))
        {
            return 0;
        }
        Record r=m.get(n);
        if((s&&n.value>v)||((!s)&&n.value<v))//需要删除的部分
        {
            m.remove(n);
            return r.l+r.r+1;
        }
        else
        {
            int minus=modifyMap(s?n.right:n.left,v,m,s);
            if(s)
            {
                r.l-=minus;
            }
            else
            {
                r.r-=minus;
            }
            m.put(n,r);
            return minus;
        }
    }

    public static void main(String[] args)
    {
        Node head=new Node(6);
        head.left=new Node(1);
        head.left.left=new Node(0);
        head.left.right=new Node(3);
        Node root1=new Node(10);
        root1.left=new Node(4);
        root1.left.left=new Node(2);
        root1.left.right=new Node(5);
        root1.right=new Node(14);
        root1.right.left=new Node(11);
        root1.right.right=new Node(15);
        Node root2=new Node(13);
        root2.left=new Node(20);
        root2.right=new Node(16);
        head.right=new Node(12);
        head.right.left=root1;
        head.right.right=root2;

        BSTTopToSize b=new BSTTopToSize();

        System.out.println(b.bstTopSize2(head));
    }

}
