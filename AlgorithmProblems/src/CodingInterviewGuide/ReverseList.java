package CodingInterviewGuide;

/**
 * 反转单向链表
 * Created by ZingBug on 2018/8/8.
 */
public class ReverseList {

    public Node reverseList(Node head)
    {
        Node pre=null;
        Node next=null;
        while (head!=null)
        {
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }

    public static void main(String[] args)
    {
        ReverseList r=new ReverseList();
        Node head=new Node(1);
        head.next=new Node(2);
        head.next.next=new Node(3);
        Node node=r.reverseList(head);
        while (node!=null)
        {
            System.out.print(node.value+" ");
            node=node.next;
        }
        System.out.println();
    }
}
