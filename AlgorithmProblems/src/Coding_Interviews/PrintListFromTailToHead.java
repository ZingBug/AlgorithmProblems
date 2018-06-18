package Coding_Interviews;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 从尾到头打印链表
 * 题目描述
 * 输入一个链表，从尾到头打印链表每个节点的值。
 *
 * https://www.nowcoder.com/practice/d0267f7f55b3412ba93bd35cfa8e8035?tpId=13&tqId=11156&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class PrintListFromTailToHead {
    private static class ListNode
    {
        int val;
        ListNode next=null;

        ListNode(int val)
        {
            this.val=val;
        }
    }
    private ArrayList<Integer> solution1(ListNode listNode)
    {
        //第一种方法,使用栈，让listnode从头到尾进栈，然后出栈
        Stack<Integer> stack=new Stack<>();
        ArrayList<Integer> list=new ArrayList<>();

        while (listNode!=null)
        {
            stack.push(listNode.val);
            listNode=listNode.next;
        }
        while (!stack.isEmpty())
        {
            list.add(stack.pop());
        }
        return list;

    }
    ArrayList<Integer> list2=new ArrayList<>();
    private void solution2(ListNode listNode)
    {
        //第二种方法，通过递归，模拟进栈出栈的过程，达到方法1类似的效果
        if(listNode!=null)
        {
            if(listNode.next!=null)
            {
                solution2(listNode.next);
            }
            list2.add(listNode.val);
        }
    }
    public ArrayList<Integer> solution3(ListNode listNode)
    {
        //第三种方法，通过新建链表，将原链表从头到尾遍历，同时采用倒序插入的方式将每个值插入到新链表，再遍历输出新链表,这种方法最快。
        ArrayList<Integer> list=new ArrayList<>();
        ListNode preNode=null;
        ListNode newNode=null;
        while (listNode!=null)
        {
            newNode=new ListNode(listNode.val);
            newNode.next=preNode;
            preNode=newNode;
            listNode=listNode.next;
        }
        while (preNode!=null)
        {
            list.add(preNode.val);
            preNode=preNode.next;
        }
        return list;

    }
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode)
    {
        //return solution1(listNode);

        //solution2(listNode);
        //return list2;

        return solution3(listNode);
    }
    public static void main(String[] args)
    {
        ListNode listNode=new ListNode(58);
        listNode.next=new ListNode(24);
        listNode.next.next=new ListNode(0);
        listNode.next.next.next=new ListNode(67);
        PrintListFromTailToHead p=new PrintListFromTailToHead();
        ArrayList<Integer> list=p.printListFromTailToHead(listNode);
        for(int val:list)
        {
            System.out.print(val+" ");
        }
    }
}
