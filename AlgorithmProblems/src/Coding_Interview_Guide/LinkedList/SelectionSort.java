package Coding_Interview_Guide.LinkedList;

import Algorithms_4th.Sorting.Selection;

/**
 * 单链表的选择排序
 * 给定一个无序单链表的头节点head，实现单链表的选择排序
 * 要求：额外空间复杂度为O(1)
 * Page79
 * Created by ZingBug on 2018/11/1.
 */
public class SelectionSort {

    private Node selectionSort(Node head)
    {
        if(head==null)
        {
            return null;
        }
        Node cur=head;
        Node small=null;
        Node tail=null;//排序部分尾部，用于连接
        while (cur!=null)
        {
            small=getSmallestPreNode(cur);
            cur=cur==small?cur.next:cur;
            if(tail==null)
            {
                head=small;
            }
            else {
                tail.next=small;
            }
            tail=small;
        }
        return head;
    }

    //得到从head开始的链表中最小的一个节点，并将链表中该节点删除
    private Node getSmallestPreNode(Node head)
    {
        Node small= new Node(0);
        small.next=head;
        Node cur=head;
        while (cur.next!=null)
        {
            if(cur.next.value<small.next.value)
            {
                small=cur;
            }
            cur=cur.next;
        }
        cur=small.next;
        small.next=small.next.next;

        return cur;
    }

    public static void main(String[] args)
    {
        Node head=null;
        head=new Node(1);
        head.next=new Node(4);
        head.next.next=new Node(3);
        head.next.next.next=new Node(2);

        SelectionSort s=new SelectionSort();
        Node node=s.selectionSort(head);
        System.out.println();
    }
}
