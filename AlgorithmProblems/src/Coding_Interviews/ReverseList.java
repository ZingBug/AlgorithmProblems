package Coding_Interviews;

/**
 * 反转链表
 * 题目描述
 * 输入一个链表，反转链表后，输出链表的所有元素。
 *
 * https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=13&tqId=11168&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * Created by ZingBug on 2018/6/20.
 */
public class ReverseList {
    public ListNode ReverseList(ListNode head)
    {
        ListNode node=null;
        while (head!=null)
        {
            ListNode newNode=new ListNode(head.val);
            newNode.next=node;
            node=newNode;

            head=head.next;
        }
        return node;
    }

    public static void main(String[] args)
    {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        ReverseList r=new ReverseList();
        ListNode result=r.ReverseList(head);
        while (result!=null)
        {
            System.out.print(result.val+" ");
            result=result.next;
        }
    }
}
