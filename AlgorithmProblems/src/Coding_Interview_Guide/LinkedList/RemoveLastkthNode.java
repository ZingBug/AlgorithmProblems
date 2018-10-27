package Coding_Interview_Guide.LinkedList;


/**
 * 在单链表和双链表中删除倒数第K个节点
 * Page35
 * 和书中方法有些不同，这个是两个链表实现，早走的链表快K步。
 * Created by ZingBug on 2018/10/19.
 */
public class RemoveLastkthNode {

    private Node removeLastKthNode(Node head,int lashKth)
    {
        if(head==null||lashKth<1)
        {
            return head;
        }
        Node lastNode=new Node(0);
        lastNode.next=head;
        Node firstNode=head;
        int i=0;
        while (firstNode!=null)
        {
            firstNode=firstNode.next;
            i++;
            if(i>lashKth)
            {
                lastNode=lastNode.next;
            }
        }
        if(i>lashKth)
        {
            lastNode.next=lastNode.next.next;
        }
        else if(i==lashKth)
        {
            head=head.next;
        }

        return head;

    }

    public static void main(String[] args)
    {
        Node head=new Node(1);
        head.next=new Node(2);
        head.next.next=new Node(3);
        head.next.next.next=new Node(4);
        head.next.next.next.next=new Node(5);

        RemoveLastkthNode r=new RemoveLastkthNode();
        Node node=r.removeLastKthNode(head,6);
        while (node!=null)
        {
            System.out.print(node.value+" ");
            node=node.next;
        }
        System.out.println();
    }
}
