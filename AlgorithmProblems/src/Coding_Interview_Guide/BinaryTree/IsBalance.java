package Coding_Interview_Guide.BinaryTree;

/**
 * 判断一个二叉树是否为平衡二叉树
 * Page144
 * Created by ZingBug on 2018/12/17.
 */
public class IsBalance {

    private boolean isBalance(Node head)
    {
        boolean[] res={true};
        getHeight(head,0,res);
        return res[0];
    }

    private int getHeight(Node head,int level,boolean[] res)//res[]用来存储结果
    {
        if(head==null)
        {
            return level;
        }
        int lH=getHeight(head.left,level+1,res);
        if(!res[0])
        {
            return level;
        }
        int rH=getHeight(head.right,level+1,res);
        if(!res[0])
        {
            return rH;
        }
        if(Math.abs(lH-rH)>1)
        {
            res[0]=false;
        }
        return Math.max(lH,rH);
    }

    public static void main(String[] args)
    {
        /*
         *     3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         */
        Node head=new Node(3);
        //head.left=new Node(9);
        head.right=new Node(20);
        head.right.left=new Node(15);
        head.right.right=new Node(7);

        IsBalance i=new IsBalance();
        System.out.println(i.isBalance(head));
    }
}
