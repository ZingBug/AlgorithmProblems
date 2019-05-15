package Coding_Interview_Guide.BinaryTree;

import java.util.Stack;

/**
 * 通过有序数组生成平衡搜索二叉树
 * 给定一个有序数组sortArr，已知其中没有重复值，用这个有序数组生成一颗平衡搜索二叉树，并且该搜索二叉树中序遍历的结果与sortArr一致。
 * 用数组中最中间的树数生成搜索二叉树的头节点，用这个数左边的数生成左子树，用这个数右边的数生成右子树
 * Created by ZingBug on 2018/12/21.
 */
public class GenerateTree {
    private Node generateTree(int[] sortArr)
    {
        if(sortArr==null||sortArr.length==0)
        {
            return null;
        }
        return generate(sortArr,0,sortArr.length-1);
    }
    private Node generate(int[] sortArr,int start,int end)
    {
        if(start>end)
        {
            return null;
        }
        int mid=(start+end)/2;
        Node node=new Node(sortArr[mid]);
        node.left=generate(sortArr,start,mid-1);
        node.right=generate(sortArr,mid+1,end);
        return node;

    }

    private void inorderByStack(Node node)
    {
        if(node==null)
        {
            return;
        }
        Stack<Node> stack=new Stack<>();
        while (!stack.isEmpty()||node!=null)
        {
            if(node!=null)
            {
                stack.push(node);
                node=node.left;
            }
            else
            {
                node=stack.pop();
                System.out.print(node.value+" ");
                node=node.right;
            }
        }
    }

    public static void main(String[] args)
    {
        int[] sortArr={1,2,3,4,5,6,7};

        GenerateTree g=new GenerateTree();
        Node r=g.generateTree(sortArr);
        g.inorderByStack(r);
    }
}
