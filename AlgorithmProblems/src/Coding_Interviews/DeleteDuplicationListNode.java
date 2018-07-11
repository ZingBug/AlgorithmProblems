package Coding_Interviews;

/**
 * 删除链表中重复的结点
 * 题目描述
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 *
 * https://www.nowcoder.com/practice/fc533c45b73a41b0b44ccba763f866ef?tpId=13&tqId=11209&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路：
 * 当遍历到每个元素时，搜索看下一个元素是否是重复元素，如果是重复元素的话则跳过此次循环，不是重复元素的话则记录下来。
 *
 * Created by ZingBug on 2018/7/10.
 */
public class DeleteDuplicationListNode {
    public ListNode deleteDuplication(ListNode pHead)
    {
        if(pHead==null)
        {
            return null;
        }
        ListNode node1=pHead;
        ListNode node2=null;

        ListNode root=new ListNode(0);
        ListNode root1=root;

        while (node1!=null)
        {

            node2=node1.next;
            boolean isDuplication=false;
            while (node2!=null&&node2.val==node1.val)
            {
                isDuplication=true;
                node2=node2.next;
            }
            if(isDuplication)
            {
                node1=node2;
                continue;
            }
            root.next=new ListNode(node1.val);
            root=root.next;
            node1=node2;
        }
        return root1.next;
    }

    public static void main(String[] args)
    {
        ListNode root=new ListNode(1);
        root.next=new ListNode(2);
        root.next.next=new ListNode(2);
        root.next.next.next=new ListNode(2);
        root.next.next.next.next=new ListNode(3);
        root.next.next.next.next.next=new ListNode(4);
        root.next.next.next.next.next.next=new ListNode(4);
        DeleteDuplicationListNode d=new DeleteDuplicationListNode();
        ListNode node=d.deleteDuplication(root);
        while (node!=null)
        {
            System.out.print(node.val+" ");
            node=node.next;
        }
    }
}
