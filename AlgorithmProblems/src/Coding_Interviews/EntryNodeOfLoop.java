package Coding_Interviews;


/**
 * 链表中环的入口结点
 * 题目描述
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 *
 * https://www.nowcoder.com/practice/253d2c59ec3e4bc68da16833f79a38e4?tpId=13&tqId=11208&tPage=3&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 1、选择快慢指针，让快指针每次走两步，慢指针每次走一步，若是单链表中有环的话，那么两个指针会相遇，即指向的相同的节点的值相等来判断。
 *
 * 2、当相遇的时候，慢指针在环中走了k步，设环之外的部分长为x,环的长度为n,则快指针一共走了x+m*n+k步，（m为快指针在环中走的圈数），慢指针一共走了x+k步，
 * 因为快指针的速度是慢指针的两倍。那么可以得到2(x+k)=x+m*n+k;得到x为m*n-k ,慢指针在圈中还剩下的步数n-k;
 *
 * 3、第一次相遇后，让快指针从头开始，两个指针每次都走一步，当快指针走了想x(m*n-k)步的时候，到达环的入口，
 * 慢指针在圈中走m*n-k步的时候，也到达环入口那个节点，两个指针再次相遇，此刻的节点就是环的入口的节点。
 *
 * Created by ZingBug on 2018/7/7.
 */
public class EntryNodeOfLoop {
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if(pHead==null||pHead.next==null)
        {
            return null;
        }
        ListNode fast=pHead;//快指针每次走两步
        ListNode slow=pHead;//慢指针每次走一步
        while (fast!=null&&fast.next!=null)//因为fast每次要走两步，所有需要判断fast的下一个是否为空
        {
            slow=slow.next;
            fast=fast.next.next;
            //判断是否相遇 相遇后让快指针从头开始走，每次都是走一步，第二次相遇的节点就是环的入口
            if(fast.val==slow.val)
            {
                fast=pHead;
                while (fast.val!=slow.val)
                {
                    fast=fast.next;
                    slow=slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
