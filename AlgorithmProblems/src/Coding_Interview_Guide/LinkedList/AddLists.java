package Coding_Interview_Guide.LinkedList;

import java.util.Stack;

/**
 * 两个单链表生成相加链表
 * 例如9->3->7，代表整数937。6->3，代表整数63
 * 最后相加生成的链表为1->0->0->0
 * 最简单方法是先算出各自代表的整数，然后想加，但在转成系统的int类型时可能会溢出，放弃这种方法。
 * Page59
 * Created by ZingBug on 2018/10/26.
 */
public class AddLists {
    //用栈结构来求解
    private Node addLists1(Node head1,Node head2)
    {
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();

        while (head1!=null)
        {
            stack1.push(head1.value);
            head1=head1.next;
        }
        while (head2!=null)
        {
            stack2.push(head2.value);
            head2=head2.next;
        }

        int ca=0;//进位
        int n1;
        int n2;
        int n;
        Node node=null;
        Node pre=null;

        while (!stack1.isEmpty()||!stack2.isEmpty())
        {
            n1=stack1.isEmpty()?0:stack1.pop();
            n2=stack2.isEmpty()?0:stack2.pop();
            n=(n1+n2+ca)%10;
            ca=(n1+n2+ca)/10;
            pre=node;
            node=new Node(n);
            node.next=pre;
        }
        if(ca==1)
        {
            pre=node;
            node=new Node(ca);
            node.next=pre;
        }
        return node;
    }
    //利用链表的逆序求解，可以省掉用栈的空间
    private Node addLists2(Node head1,Node head2)
    {
        head1=reverseList(head1);
        head2=reverseList(head2);

        int n1;
        int n2;
        int ca=0;
        int n=0;

        Node node=null;
        Node pre=null;

        while (head1!=null||head2!=null)
        {
            n1=head1==null?0:head1.value;
            n2=head2==null?0:head2.value;
            n=(n1+n2+ca)%10;
            ca=(n1+n2+ca)/10;
            pre=node;
            node=new Node(n);
            node.next=pre;

            head1=head1==null?null:head1.next;
            head2=head2==null?null:head2.next;
        }
        if(ca==1)
        {
            pre=node;
            node=new Node(ca);
            node.next=pre;
        }
        return node;
    }

    private Node reverseList(Node head)
    {
        Node node=null;
        Node pre=null;
        while (head!=null)
        {
            node=head.next;
            head.next=pre;
            pre=head;
            head=node;
        }
        return pre;
    }

    public static void main(String[] args)
    {
        Node head1=new Node(9);
        head1.next=new Node(3);
        head1.next.next=new Node(7);

        Node head2=new Node(6);
        head2.next=new Node(3);

        AddLists a=new AddLists();
        Node node=a.addLists2(head1,head2);
        while (node!=null)
        {
            System.out.print(node.value+" ");
            node=node.next;
        }
        System.out.println();
    }
}
