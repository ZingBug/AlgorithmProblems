package Coding_Interview_Guide.BinaryTree;

/**
 * 找到二叉树中的最大搜索二叉子树
 * 给定一颗二叉树的头节点head，已知其中所有节点的值都不一样，找到含有节点最多的搜索二叉树，并返回这颗子树的头节点。
 * Page117
 * Created by ZingBug on 2018/12/4.
 */
public class GetBiggestSubBST {
    private Node biggestSubBST(Node head)
    {
        int[] record=new int[3];
        return preOrder(head,record);

    }
    private Node preOrder(Node head,int[] record)
    {
        if(head==null)
        {
            record[0]=0;//存储当前节点大小
            record[1]=Integer.MAX_VALUE;//存储当前节点及以下的最小值
            record[2]=Integer.MIN_VALUE;//存储当前节点及以下的最大值
            return null;
        }
        int value=head.value;
        Node left=head.left;
        Node right=head.right;
        Node lBST=preOrder(left,record);
        int lSize=record[0];
        int lMin=record[1];
        int lMax=record[2];
        Node rBST=preOrder(right,record);
        int rSize=record[0];
        int rMin=record[1];
        int rMax=record[2];
        record[1]=Math.min(lMin,value);
        record[2]=Math.max(rMax,value);
        if(left==lBST&&right==rBST&&lMax<value&&value<rMin)//以head为头节点的整棵树都是搜索二叉树
        {
            record[0]=lSize+rSize+1;
            return head;
        }
        record[0]=Math.max(lSize,rSize);//返回节点数更多的搜索二叉树
        return lSize>rSize?lBST:rBST;
    }
    public static void main(String[] args)
    {
        Node head=new Node(6);
        head.left=new Node(1);
        head.left.left=new Node(0);
        head.left.right=new Node(3);
        Node root1=new Node(10);
        root1.left=new Node(4);
        root1.left.left=new Node(2);
        root1.left.right=new Node(5);
        root1.right=new Node(14);
        root1.right.left=new Node(11);
        root1.right.right=new Node(15);
        Node root2=new Node(13);
        root2.left=new Node(20);
        root2.right=new Node(16);
        head.right=new Node(12);
        head.right.left=root1;
        head.right.right=root2;

        GetBiggestSubBST g=new GetBiggestSubBST();
        Node node=g.biggestSubBST(head);

        System.out.println();
    }
}
