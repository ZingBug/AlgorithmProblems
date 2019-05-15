package Coding_Interview_Guide.LinkedList;

import java.util.Stack;

/**
 * 将单链表的每K个节点之间逆序
 * 给定一个单链表的头节点head，实现一个调整单链表的函数，使得每K个节点之间逆序，如果最后不够K个节点一组，则不调整最后几个节点
 * Page68
 * Created by ZingBug on 2018/10/28.
 */
public class ReverseKNodes {
    //利用栈来反转，空间复杂度为O(n)
    private Node reverseKNodes1(Node head, int K) {
        if (K < 2) {
            return head;
        }
        Node pre = null;
        Node next = null;
        Node cur = head;
        Node newHead = null;
        Stack<Node> stack = new Stack<>();
        while (cur != null) {
            next = cur.next;
            stack.push(cur);
            if (stack.size() == K) {
                pre = resign1(stack, pre, next);
                newHead = newHead == null ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    private Node resign1(Stack<Node> stack, Node left, Node right) {
        Node pre = stack.pop();
        if (left != null) {
            left.next = pre;
        }
        Node next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();
            pre.next = next;
            pre = next;
        }
        pre.next = right;
        return pre;
    }

    //利用几个变量来反转，空间复杂度为O(1)
    private Node reverseKNodes2(Node head, int K) {
        if (K < 2) {
            return head;
        }
        Node pre = null;
        Node next = null;
        Node cur = head;
        Node newHead = null;
        Node start = cur;
        int count = 0;
        while (cur != null) {
            next = cur.next;
            count++;
            if (count == K) {
                pre = resign2(pre, start, cur, next);
                start = pre.next;
                newHead = newHead == null ? cur : newHead;
                count = 0;
            }
            cur = next;
        }
        return newHead;
    }

    private Node resign2(Node left, Node start, Node end, Node right) {
        Node cur = start;
        Node next = null;
        Node pre = null;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = pre;
        }
        start.next = right;
        return start;
    }

    public static void main(String[] args) {
        Node next = null;
        Node cur = new Node(1);
        Node head = cur;
        for (int i = 2; i <= 8; i++) {
            next = new Node(i);
            cur.next = next;
            cur = next;
        }
        ReverseKNodes r = new ReverseKNodes();
        Node res = r.reverseKNodes2(head, 3);
        while (res != null) {
            System.out.print(res.value + " ");
            res = res.next;
        }
    }
}
