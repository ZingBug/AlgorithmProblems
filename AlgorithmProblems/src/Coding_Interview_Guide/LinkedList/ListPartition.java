package Coding_Interview_Guide.LinkedList;

/**
 * 将单向链表按某值划分为左边小，中间相等，右边大
 * Page52
 * Created by ZingBug on 2018/10/23.
 */
public class ListPartition {
    //第一种方法，对调整后的节点没有顺序要求
    private Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        int i = 0;
        Node cur = head;
        //第一次遍历，得到链表长度
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodes = new Node[i];//建立数组，将每个节点放入数组
        cur = head;
        for (i = 0; i < nodes.length; i++) {
            nodes[i] = cur;
            cur = cur.next;
        }
        //进行节点分布
        arrPartition(nodes, pivot);
        //重新将数组节点连接起来
        for (i = 1; i < nodes.length; i++) {
            nodes[i - 1].next = nodes[i];
        }
        nodes[i - 1].next = null;//最后一个节点
        return nodes[0];
    }

    private void arrPartition(Node[] nodes, int pivot) {
        int small = -1;
        int big = nodes.length;
        int index = 0;
        while (index != big) {
            if (nodes[index].value < pivot) {
                swap(nodes, ++small, index++);
            } else if (nodes[index].value == pivot) {
                index++;
            } else {
                swap(nodes, --big, index++);
            }
        }
    }

    private void swap(Node[] nodes, int i, int j) {
        Node node = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = node;
    }

    //第二种方法，要求调整后的节点顺序与原链表的先后次序一致，主要是将小于、等于和大于各自划分为一个链表
    private Node listPartition2(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        Node sH = null;//小的头
        Node sT = null;//小的尾
        Node eH = null;//相等的头
        Node eT = null;//相等的尾
        Node bH = null;//大的头
        Node bT = null;//大的尾

        Node next = null;
        while (head != null) {
            next = head.next;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = sT.next;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = eT.next;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = bT.next;
                }
            }
            head = next;
        }
        //然后将三个链表拼接
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }

        return sH != null ? sH : eH != null ? eH : bH;
    }

    public static void main(String[] args) {
        Node head1 = null;
        head1 = new Node(9);
        head1.next = new Node(0);
        head1.next.next = new Node(4);
        head1.next.next.next = new Node(5);
        head1.next.next.next.next = new Node(1);
        head1.next.next.next.next.next = new Node(6);
        Node head2 = null;
        head2 = new Node(9);
        head2.next = new Node(0);
        head2.next.next = new Node(4);
        head2.next.next.next = new Node(5);
        head2.next.next.next.next = new Node(1);
        head2.next.next.next.next.next = new Node(6);

        ListPartition l = new ListPartition();
        Node node1 = l.listPartition1(head1, 5);
        Node node2 = l.listPartition2(head2, 5);

        while (node1 != null) {
            System.out.print(node1.value + " ");
            node1 = node1.next;
        }
        System.out.println();
        while (node2 != null) {
            System.out.print(node2.value + " ");
            node2 = node2.next;
        }
    }
}
