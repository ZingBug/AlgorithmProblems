package Coding_Interviews;

import java.util.HashMap;
import java.util.Map;

/**
 * 复杂链表的复制
 * 题目描述
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 * 注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空。
 *
 * https://www.nowcoder.com/practice/f836b2c43afc4b35ad6adc41ec941dba?tpId=13&tqId=11178&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路(分别对应以下的solution1/2/3)
 *
 * 方法1：
 * 复制原始链表上的每一个结点，并通过pNext连接起来；然后再设置每个结点的pSibling指针。
 * 假设原始链表中某个结点N的pSibling指针指向结点S,那么就需要从头到尾遍历查找结点S，如果从原始链表的头指针开始，经过m步之后达到结点S，
 * 那么在复制链表中的结点N'的pSibling指针指向的结点也是距离复制链表s步的结点。通过这种办法就可以为复制链表上的每个结点设置pSibling指针。
 * 时间复杂度：O(N^2)
 *
 * 方法2：
 * 方法1是通过链表查找来得到pSibling指针所指向的结点，实际上我们可以通过空间换取时间，将原始链表和复制链表的结点通过哈希表对应起来，这样查找的时间就从O(N)变为O(1)。具体如下：
 * 复制原始链表上的每个结点N创建N'，然后把这些创建出来的结点用pNext连接起来。
 * 同时把<N,N'>的配对信息方法一个哈希表中；然后设置复制链表中的每个结点的pSibling指针，如果原始链表中结点N的pSibling指向结点S，那么在复制链表中，对应的N'应该指向S'。
 * 时间复杂度：O(N)
 *
 * 方法3：
 * 在不使用辅助空间的情况下实现O(N)的时间效率。
 * 第一步：根据原始链表的每个结点N创建对应的N'，然后将N‘通过pNext接到N的后面；
 * 第二步：设置复制出来的结点的pSibling。假设原始链表上的N的pSibling指向结点S，那么其对应复制出来的N'是N->pNext指向的结点，同样S'也是结点S->pNext指向的结点。
 * 第三步：把长链表拆分成两个链表，把奇数位置的结点用pNext连接起来的就是原始链表，把偶数位置的结点通过pNext连接起来的就是复制链表。
 *
 * Created by ZingBug on 2018/6/22.
 */
public class ComplexListClone {
    private static class RandomListNode
    {
        int label;
        RandomListNode next=null;
        RandomListNode random=null;

        RandomListNode(int label)
        {
            this.label=label;
        }
    }
    private RandomListNode solution1(RandomListNode pHead)
    {
        RandomListNode pHead1=pHead;
        RandomListNode head=new RandomListNode(0);
        RandomListNode head1=head;
        //先复制单序链表，不考虑随机链表
        while (pHead1!=null)
        {
            head1.next=new RandomListNode(pHead1.label);
            head1=head1.next;
            pHead1= pHead1.next;
        }
        //这个时候考虑随机链表
        pHead1=pHead;
        head1=head.next;
        while (pHead1!=null)
        {
            RandomListNode node=pHead1.random;//注意考虑这个随机节点为null的情况
            RandomListNode head2=head.next;
            while (head2!=null&&node!=null)
            {
                if(head2.label==node.label&&(head2.next==node.next||(head2.next!=null&&node.next!=null&&head2.next.label==node.next.label)))
                {
                    break;
                }
                head2=head2.next;
            }
            if(node==null)
            {
                head1.random=null;
            }
            else
            {
                head1.random=head2;
            }

            head1=head1.next;
            pHead1=pHead1.next;
        }
        return head.next;
    }
    private RandomListNode solution2(RandomListNode pHead)
    {
        Map<RandomListNode,RandomListNode> map=new HashMap<>();
        RandomListNode pHead1=pHead;
        RandomListNode head=new RandomListNode(0);
        RandomListNode head1=head;
        while (pHead1!=null)
        {
            head1.next=new RandomListNode(pHead1.label);
            head1=head1.next;
            map.put(pHead1,head1);
            pHead1=pHead1.next;
        }
        pHead1=pHead;
        head1=head.next;
        while (pHead1!=null)
        {
            head1.random=map.get(pHead1.random);
            head1=head1.next;
            pHead1=pHead1.next;
        }
        return head.next;
    }
    private RandomListNode solution3(RandomListNode pHead)
    {
        RandomListNode pHead1=pHead;
        RandomListNode head=new RandomListNode(0);
        RandomListNode head1=head;
        while (pHead1!=null)
        {
            RandomListNode node=new RandomListNode(pHead1.label);
            node.next=pHead1.next;

            pHead1.next=node;
            pHead1=node.next;
        }

        pHead1=pHead;
        while (pHead1!=null)
        {
            RandomListNode node= pHead1.random;
            if(node!=null)//注意考虑随机节点是null的情况
            {
                pHead1.next.random=node.next;
            }
            pHead1=pHead1.next.next;
        }
        pHead1=pHead;
        while (pHead1!=null)
        {
            head1.next=pHead1.next;
            head1=head1.next;
            pHead1.next=head1.next;
            pHead1=pHead1.next;//这块还需要保证pHead的链表不能被改变才行，还原链表
        }
        return head.next;
    }
    public RandomListNode Clone(RandomListNode pHead)
    {
        return solution3(pHead);
    }
    public static void main(String[] args)
    {

        RandomListNode head = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);
        RandomListNode node5 = new RandomListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        head.random = node3;
        node2.random = node5;
        node4.random = node2;
        ComplexListClone c=new ComplexListClone();
        RandomListNode copyNode=c.Clone(head);
        System.out.println(copyNode.label);
    }
}
