package Coding_Interview_Guide.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 复制含有随机指针节点的链表
 * Page56
 * Created by ZingBug on 2018/10/25.
 */
public class CopyListWithRand {
    //用哈希表来存储，但空间复杂度也是O(n)
    private RandomNode copyListWithRand1(RandomNode head)
    {
        if(head==null)
        {
            return null;
        }
        Map<RandomNode,RandomNode> map=new HashMap<>();
        RandomNode cur=head;
        while (cur!=null)
        {
            map.put(cur,new RandomNode(cur.value));
            cur=cur.next;
        }
        cur=head;
        while (cur!=null)
        {
            //map.get(null)=null
            map.get(cur).next=map.get(cur.next);
            map.get(cur).rand=map.get(cur.rand);
            cur=cur.next;
        }
        return map.get(head);
    }
    //在同一个链表中保存，空间复杂度为O(1)
    private RandomNode copyListWithRand2(RandomNode head)
    {
        if(head==null)
        {
            return null;
        }
        RandomNode cur=head;
        RandomNode next=null;
        //将旧节点和新复制的节点链成一个链表
        while (cur!=null)
        {
            next=cur.next;
            cur.next=new RandomNode(cur.value);
            cur.next.next=next;
            cur=cur.next.next;
        }
        //给新复制节点添加next和rand
        RandomNode curCopy=null;
        cur=head;
        while (cur!=null)
        {
            curCopy=cur.rand;
            cur.next.rand=curCopy==null?null:curCopy.next;
            cur=cur.next.next;
        }
        //将旧节点和新复制的节点分开
        cur=head.next;
        while (cur!=null)
        {
            next=cur.next==null?null:cur.next.next;
            cur.next=next;
            cur=next;
        }
        return head.next;
    }


    public static void main(String[] args)
    {
        RandomNode head=null;
        head=new RandomNode(1);
        head.next=new RandomNode(2);
        head.next.next=new RandomNode(3);
        head.rand=head.next;
        head.next.next.rand=head;

        CopyListWithRand c=new CopyListWithRand();
        //RandomNode node1=c.copyListWithRand1(head);
        RandomNode node2=c.copyListWithRand2(head);
        System.out.println();
    }
}
