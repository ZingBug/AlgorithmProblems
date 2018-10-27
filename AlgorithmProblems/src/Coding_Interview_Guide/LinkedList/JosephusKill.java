package Coding_Interview_Guide.LinkedList;

/**
 * 环形单链表的约瑟夫问题
 * 一个环形单向链表，从头开始删除第m个节点，然后再计数删除第m个节点，以此类推，剩下最有一个节点
 * Page43
 * Created by ZingBug on 2018/10/19.
 */
public class JosephusKill {

    private Node josephusKill1(Node head, int m) {
        //传统方法，依此删除
        if (head == null || head.next == null || m < 1) {
            return head;
        }
        Node last = head.next;//head的前一个
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;
        while (last != head) {
            count++;
            if (count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = head;
            }
            head = head.next;
        }
        return head;
    }

    private Node josephusKill2(Node head, int m) {
        if (head == null || head.next == null || m < 1) {
            return head;
        }
        int size = 1;
        Node cur = head;
        while (cur.next != head) {
            size++;
            cur = cur.next;
        }
        int tmp = getLive(size, m);

        while (--tmp != 0) {
            head = head.next;
        }

        return head;

    }

    private int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }

    public static void main(String args[]) {
        Node head = null;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = head;

        JosephusKill j = new JosephusKill();

        System.out.println(j.josephusKill2(head, 3).value);
    }
}
