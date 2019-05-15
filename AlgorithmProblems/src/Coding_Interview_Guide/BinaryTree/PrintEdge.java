package Coding_Interview_Guide.BinaryTree;

/**
 * 打印二叉树的边界节点
 * 分别按照两种标准打印边界
 * Page95
 * Created by ZingBug on 2018/11/12.
 */
public class PrintEdge {
    private void printEdge1(Node head)
    {
        if(head==null)
        {
            return;
        }
        int height=getHeight(head,0);
        Node[][] edgeMap=new Node[height][2];
        setEdge(head,0,edgeMap);//找左右边界
        for(int i=0;i<height;i++)//打印左边界
        {
            System.out.print(edgeMap[i][0].value+" ");
        }
        //再找叶子节点
        printLeafNotInMap(head,0,edgeMap);
        for(int i=height-1;i>=0;i--)
        {
            if(edgeMap[i][0]!=edgeMap[i][1])
            {
                System.out.print(edgeMap[i][1].value+" ");
            }
        }

    }

    private int getHeight(Node head,int l)
    {
        if(head==null)
        {
            return 0;
        }
        return Math.max(getHeight(head.left,l+1)+1,getHeight(head.right,l+1)+1);
    }

    private void setEdge(Node head,int l,Node[][] edgeMap)
    {
        if(head==null)
        {
            return;
        }
        edgeMap[l][0]=edgeMap[l][0]==null?head:edgeMap[l][0];
        edgeMap[l][1]=head;
        setEdge(head.left,l+1,edgeMap);//先找最左边的边界
        setEdge(head.right,l+1,edgeMap);//再找最右边的边界
    }

    private void printLeafNotInMap(Node head,int l,Node[][] edgeMap)
    {
        if(head==null)
        {
            return;
        }
        if(head.left==null&&head.right==null&&head!=edgeMap[l][0]&&head!=edgeMap[l][1])
        {
            System.out.print(head.value+" ");
        }
        printLeafNotInMap(head.left,l+1,edgeMap);
        printLeafNotInMap(head.right,l+1,edgeMap);
    }

    private void printEdge2(Node head)
    {
        if(head==null)
        {
            return;
        }
        System.out.print(head.value+" ");
        if(head.left==null||head.right==null)//寻找第一个同时拥有左子树和右子树的节点
        {
            printEdge2(head.left==null?head.right:head.left);
        }
        else
        {
            printLeftEdge(head.left,true);//打印左边界和左半区的叶子节点
            printRightEdge(head.right,true);//打印右边界和右半区的叶子节点
        }

    }

    private void printLeftEdge(Node head,boolean print)
    {
        if(head==null)
        {
            return;
        }
        if(print||(head.left==null&&head.right==null))
        {
            System.out.print(head.value+" ");
        }
        printLeftEdge(head.left,print);
        printLeftEdge(head.right,print&&head.left==null);
    }
    private void printRightEdge(Node head,boolean print)
    {
        if(head==null)
        {
            return;
        }
        printRightEdge(head.left,print&&head.right==null);
        printRightEdge(head.right,print);
        if(print||(head.left==null&&head.right==null))
        {
            System.out.print(head.value+" ");
        }
    }

    public static void main(String[] args)
    {
        Node head=new Node(1);
        head.left=new Node(2);
        head.left.left=new Node(4);
        head.left.right=new Node(5);
        head.right=new Node(3);
        head.right.left=new Node(6);
        head.right.right=new Node(7);

        PrintEdge p=new PrintEdge();
        System.out.print("Edge: ");
        p.printEdge2(head);
        System.out.println();
    }
}
