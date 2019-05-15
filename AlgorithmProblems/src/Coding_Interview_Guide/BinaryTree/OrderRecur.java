package Coding_Interview_Guide.BinaryTree;

/**
 * 用递归实现二叉树先序、中序和后序遍历
 * Page88
 * Created by ZingBug on 2018/11/11.
 */
public class OrderRecur {
    //先序
    private void preOrderRecur(Node head)
    {
        System.out.print("pre-order: ");
        if(head==null)
        {
            return;
        }
        preOrderRecur(head.left);
        System.out.print(head.value+" ");
        preOrderRecur(head.right);
    }
    //中序
    private void inOrderRecur(Node head)
    {
        System.out.print("in-order: ");
        if(head==null)
        {
            return;
        }
        System.out.print(head.value+" ");
        inOrderRecur(head.left);
        inOrderRecur(head.right);
    }
    //后序
    private void posOrderRecur(Node head)
    {
        System.out.print("pos-order: ");
        if(head==null)
        {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value+" ");
    }

    public static void main(String[] args)
    {
        Node head=new Node(1);
        head.left=new Node(2);
        head.left.left=new Node(4);
        head.right=new Node(3);
        head.right.right=new Node(6);

        OrderRecur o=new OrderRecur();
        o.posOrderRecur(head);
    }


}
