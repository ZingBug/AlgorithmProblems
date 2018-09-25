package CodingInterviewGuide;

/**
 * 反转双向链表
 * Created by ZingBug on 2018/8/8.
 */
public class ReverseDoubleList {

    public DoubleNode reverseDoubleList(DoubleNode head)
    {
        DoubleNode pre=null;
        DoubleNode next=null;
        while (head!=null)
        {
            next=head.next;
            head.next=pre;
            head.last=next;
            pre=head;
            head=next;
        }
        return pre;
    }

    public static void main(String[] args)
    {
        ReverseDoubleList r=new ReverseDoubleList();
        DoubleNode head=new DoubleNode(1);
        head.next=new DoubleNode(2);
        head.next.last=head;
        head.next.next=new DoubleNode(3);
        head.next.next.last=head.next;

        DoubleNode node=r.reverseDoubleList(head);

        while (node!=null)
        {
            System.out.println(node.value+" ");
            node=node.next;
        }
        System.out.println();
    }
}
