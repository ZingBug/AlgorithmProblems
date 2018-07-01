package Coding_Interviews;
import java.util.Stack;

/**
 * 两个链表的第一个公共结点
 * 题目描述
 * 输入两个链表，找出它们的第一个公共结点。
 *
 * https://www.nowcoder.com/practice/6ab1d9a29e88450685099d45c9e31e46?tpId=13&tqId=11189&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 1、先求出两条链表的反向链表，然后再从尾部开始往前搜索，直到两个链表值不同。
 *
 * 2、首先遍历两个链表得到它们的长度，就能知道哪个链表比较长，以及长的链表比短的链表多几个节点。
 * 在第二次遍历的时候，先在较长的节点上走若干步，接着同时在两个链表上遍历，找到的第一个相同的节点就是它们的公共的节点。
 *
 * Created by ZingBug on 2018/6/28.
 */
public class FindFirstCommonNode {
    private ListNode solution1(ListNode pHead1,ListNode pHead2)
    {
        //第一种方法，建立反向链表，从反向开始走
        //先求反向链表
        ListNode pEnd1=reverseList(pHead1);
        ListNode pEnd2=reverseList(pHead2);


        Stack<Integer> stack=new Stack<>();
        while (pEnd1!=null&&pEnd2!=null)
        {
            if(pEnd1.val!=pEnd2.val)
            {
                break;
            }
            else
            {
                stack.add(pEnd1.val);
                pEnd1=pEnd1.next;
                pEnd2=pEnd2.next;
            }
        }
        ListNode result=new ListNode(0);
        ListNode result1=result;
        while (!stack.isEmpty())
        {
            result1.next=new ListNode(stack.pop());
            result1=result1.next;
        }
        return result.next;
    }

    private ListNode reverseList(ListNode head)
    {
        Stack<Integer> stack=new Stack<>();
        while (head!=null)
        {
            stack.push(head.val);
            head=head.next;
        }
        ListNode node=new ListNode(0);
        ListNode node1=node;
        while (!stack.isEmpty())
        {
            node1.next=new ListNode(stack.pop());
            node1=node1.next;
        }
        return node.next;
    }
    private ListNode solution2(ListNode pHead1,ListNode pHead2)
    {
        int num1=0;
        int num2=0;
        ListNode head1=pHead1;
        ListNode head2=pHead2;
        while (head1!=null)
        {
            num1++;
            head1=head1.next;
        }
        while (head2!=null)
        {
            num2++;
            head2=head2.next;
        }
        ListNode startNode=num1>num2?pHead1:pHead2;
        ListNode endNode=num1>num2?pHead2:pHead1;
        //开始第二次遍历
        int num=0;
        while (num<Math.abs(num1-num2))
        {
            startNode=startNode.next;
            num++;
        }
        //开始查找相同值
        while (startNode!=null)
        {
            if(startNode.val==endNode.val)
            {
                return startNode;
            }
            startNode=startNode.next;
            endNode=endNode.next;
        }
        return null;
    }
    public ListNode FindFirstCommonNode(ListNode pHead1,ListNode pHead2)
    {
        return solution2(pHead1,pHead2);
    }

    public static void main(String[] args)
    {
        ListNode head1=new ListNode(1);
        head1.next=new ListNode(6);
        head1.next.next=new ListNode(7);
        ListNode head2=new ListNode(4);
        head2.next=new ListNode(5);
        head2.next.next=new ListNode(6);
        head2.next.next.next=new ListNode(7);

        FindFirstCommonNode f=new FindFirstCommonNode();
        System.out.println(f.FindFirstCommonNode(head1,head2).val);
    }
}
