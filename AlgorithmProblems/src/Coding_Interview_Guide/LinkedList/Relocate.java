package Coding_Interview_Guide.LinkedList;

/**
 * 按照左右半区的方式重新组合单链表
 * Page86
 * Created by ZingBug on 2018/11/7.
 */
public class Relocate {
    private void relocate(Node head)
    {
        if(head==null||head.next==null)
        {
            return;
        }
        Node mid=head;
        Node right=head.next;
        while (right.next!=null&&right.next.next!=null)
        {
            mid=mid.next;
            right=right.next.next;
        }
        right=mid.next;
        mid.next=null;

        merge(head,right);
    }

    private void merge(Node left,Node right)
    {
        Node next=null;
        while (left.next!=null)
        {

            next=right.next;
            right.next=left.next;
            left.next=right;
            left=right.next;
            right=next;

        }
        left.next=right;
    }

    public static void main(String[] args)
    {
        Relocate r=new Relocate();
        Node head=null;
        head=new Node(1);
        head.next=new Node(2);
        head.next.next=new Node(3);
        head.next.next.next=new Node(4);
        head.next.next.next.next=new Node(5);

        r.relocate(head);

        while (head!=null)
        {
            System.out.print(head.value+" ");
            head=head.next;
        }
        System.out.println();
    }
}
