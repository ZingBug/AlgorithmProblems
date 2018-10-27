package Coding_Interview_Guide.LinkedList;

import java.util.Stack;

/**
 * 判断一个链表是否为回文
 * Page48
 * Created by ZingBug on 2018/10/22.
 */
public class IsPalindrome {
    //把整个链表的右半部分压入栈，压入完成后，再检查栈顶到栈底值出现的顺序是否和链表左半部分相同。
    //时间复杂度为O(n)，空间复杂度为O(n)
    private boolean isPalindrome1(Node head)
    {
        if(head==null||head.next==null)
        {
            return false;
        }
        Stack<Node> stack=new Stack<>();
        Node cur=head;
        Node right=head.next;//右半区第一个节点
        while (cur.next!=null&&cur.next.next!=null)
        {
            right=right.next;
            cur=cur.next.next;
        }
        while (right!=null)
        {
            stack.push(right);
            right=right.next;
        }
        while (!stack.isEmpty())
        {
            if(head.value!=stack.pop().value)
            {
                return false;
            }
            head=head.next;
        }
        return true;
    }
    //改变右半区链表结构，使整个右半区反转，最后指向中间节点，
    //然后最左侧和最右侧同时往中间移动，移动每一步时比较值
    //最后恢复链表为原来的样子
    //时间复杂度为O(n)，空间复杂度为O(1)
    private boolean isPalindrome2(Node head)
    {
        if(head==null||head.next==null)
        {
            return false;
        }
        Node n1=head;//中部节点
        Node n2=head;
        while (n2.next!=null&&n2.next.next!=null)
        {
            n1=n1.next;
            n2=n2.next.next;
        }
        Node n3=null;
        n2=n1.next;
        n1.next=null;
        //反转链表
        while (n2!=null)
        {
            n3=n2.next;
            n2.next=n1;
            n1=n2;
            n2=n3;
        }
        boolean res=true;
        n2=head;//保存左侧起点
        n3=n1;//保存右侧起点，最后一个节点
        while (n2!=null&&n3!=null)
        {
            if(n2.value!=n3.value)
            {
                res=false;
                break;
            }
            n3=n3.next;
            n2=n2.next;
        }
        //恢复链表原状态
        n3=n1.next;
        n1.next=null;
        while (n3!=null)
        {
            n2=n3.next;
            n3.next=n1;
            n1=n3;
            n3=n2;
        }
        return res;
    }

    public static void main(String[] args)
    {
        IsPalindrome i=new IsPalindrome();
        Node head1=null;
        head1=new Node(1);
        head1.next=new Node(2);
        head1.next.next=new Node(3);
        head1.next.next.next=new Node(2);
        head1.next.next.next.next=new Node(1);
        Node head2=null;
        head2=new Node(1);
        head2.next=new Node(2);
        head2.next.next=new Node(3);
        head2.next.next.next=new Node(3);
        head2.next.next.next.next=new Node(2);
        head2.next.next.next.next.next=new Node(1);

        System.out.println(i.isPalindrome2(head1));
    }
}
