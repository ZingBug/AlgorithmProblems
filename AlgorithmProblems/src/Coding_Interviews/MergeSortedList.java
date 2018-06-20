package Coding_Interviews;

/**
 * 合并两个排序的链表
 * 题目描述
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 *
 * https://www.nowcoder.com/practice/d8b6b4358f774294a89de2a6ac4d9337?tpId=13&tqId=11169&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * Created by ZingBug on 2018/6/20.
 */
public class MergeSortedList {
    public ListNode Merge(ListNode list1,ListNode list2)
    {
        ListNode node=new ListNode(0);//初始节点
        ListNode head=node;
        while (list1!=null||list2!=null)
        {
            int val=0;
            if(list1==null)
            {
                val=list2.val;
                list2=list2.next;
            }
            else if(list2==null)
            {
                val=list1.val;
                list1=list1.next;
            }
            else
            {
                if(list1.val<list2.val)
                {
                    val=list1.val;
                    list1=list1.next;
                }
                else
                {
                    val=list2.val;
                    list2=list2.next;
                }
            }
            node.next=new ListNode(val);
            node=node.next;
        }
        return head.next;
    }
    public static void main(String[] args)
    {
        ListNode head1=new ListNode(1);
        head1.next=new ListNode(3);
        head1.next.next=new ListNode(7);
        ListNode head2=new ListNode(2);
        head2.next=new ListNode(3);
        head2.next.next=new ListNode(4);
        head2.next.next.next=new ListNode(6);

        MergeSortedList m=new MergeSortedList();
        ListNode result=m.Merge(head1,head2);
        while (result!=null)
        {
            System.out.print(result.val+" ");
            result=result.next;
        }
    }
}
