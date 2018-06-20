package Coding_Interviews;

/**
 * 链表中倒数第k个结点
 * 题目描述
 * 输入一个链表，输出该链表中倒数第k个结点。
 *
 * https://www.nowcoder.com/practice/529d3ae5a407492994ad2a246518148a?tpId=13&tqId=11167&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * Created by ZingBug on 2018/6/19.
 */
public class FindKthToTail {
    private ListNode solution1(ListNode head,int k)
    {
        //第一种方法，思路简单直接，先通过一次循环得到链表长度num，然后再进行一次循环得到num-k处节点，时间复杂度为O(2n-k)
        ListNode head_copy=head;
        int num=0;
        while (head_copy!=null)
        {
            num++;
            head_copy=head_copy.next;
        }
        if(k>num)
        {
            return null;
        }
        for(int i=0;i<num-k;i++)
        {
            head=head.next;
        }
        return head;
    }

    private ListNode solution2(ListNode head,int k)
    {
        //第二种方法，得到两个头节点进行循环，第二个节点比第一个节点慢k个，当第一个节点到达节点尾部时，第二个节点则正好处于倒数k个节点位置。时间复杂度为O(n)
        ListNode head_copy1=head;
        ListNode head_copy2=head;
        head=null;
        if(k<=0)
        {
            return null;
        }
        for(int i=0;head_copy1!=null;i++)
        {
            if(i==k-1)
            {
                head=head_copy2;
            }
            else if(i>=k)
            {
                head=head.next;
            }
            head_copy1=head_copy1.next;
        }
        return head;
    }

    public ListNode FindKthToTail(ListNode head,int k)
    {
        return solution2(head,k);
    }

    public static void main(String[] args)
    {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        FindKthToTail f=new FindKthToTail();
        ListNode result=f.FindKthToTail(head,3);
        while (result!=null)
        {
            System.out.print(result.val+" ");
            result=result.next;
        }

    }
}
