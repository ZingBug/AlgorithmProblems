package Coding_Interview_Guide.BinaryTree;

import java.util.Stack;

/**
 * 根据后序数组重建搜索二叉树
 * 给定一个整形数组arr，已知其中没有重复值，判断arr是否可能是节点值类型为整形的搜索二叉树后序遍历的结果。并重构二叉树。
 *
 * 二叉树的后序遍历过程是左、右、中，所以头节点在最后一个值，那么前半部分小于头节点值的是左子树部分，后半部分大于头节点值的是右子树部分。递归判断即可。
 * Page145
 * Created by ZingBug on 2018/12/18.
 */
public class PosArrayToBST {

    private boolean isPosArray(int[] arr)
    {
        if(arr==null||arr.length<1)
        {
            return false;
        }
        if(arr.length==1)
        {
            return true;
        }
        return isPos(arr,0,arr.length-1);
    }

    private boolean isPos(int[] arr,int start,int end)
    {
        if(start==end)
        {
            return true;
        }

        int less=-1;
        int more=end;
        //for(int i=start;i<end&&arr[i]<arr[end];i++,mid++);

        for(int i=start;i<end;i++)
        {
            if(arr[i]<arr[end])
            {
                less=i;
            }
            else
            {
                more=more==end?i:more;
            }
        }

        if(less==-1||more==end)//只有单边情况
        {
            return isPos(arr,start,end-1);
        }
        if(less!=more-1)
        {
            return false;
        }
        return isPos(arr,start,less)&&isPos(arr,more,end-1);
    }
    private Node posArrayToBST(int[] arr)
    {
        return posToBST(arr,0,arr.length-1);
    }

    private Node posToBST(int[] arr,int start,int end)
    {
        if(start==end)
        {
            return new Node(arr[end]);
        }
        Node node=new Node(arr[end]);
        int less=-1;
        int more=end;

        for(int i=start;i<end;i++)
        {
            if(arr[i]<arr[end])
            {
                less=i;
            }
            else
            {
                more=more==end?i:more;
            }
        }
        if(less==-1)//只有右子树
        {
            node.left=null;
            node.right=posToBST(arr,start,end-1);
            return node;
        }
        if(more==end)//只有左子树
        {
            node.left=posToBST(arr,start,end-1);
            node.right=null;
            return node;
        }

        if(less!=more-1)
        {
            return null;//如果中间发现不能构建二叉树，则直接返回null
        }

        node.left=posToBST(arr,start,less);
        node.right=posToBST(arr,more,end-1);

        return node;
    }

    private void pos(Node root)//后序遍历，用来验证
    {
        if(root==null)
        {
            return;
        }
        pos(root.left);
        pos(root.right);
        System.out.print(root.value+" ");
    }

    private void posByStack(Node root)
    {
        Stack<Node> stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty())
        {
            Node node=stack.peek();
            if(node.left!=null&&root!=node.left&&root!=node.right)
            {
                stack.push(node.left);
            }
            else if(node.right!=null&&root!=node.right)
            {
                stack.push(node.right);
            }
            else
            {
                System.out.print(stack.pop().value+" ");
                root=node;
            }
        }
    }

    public static void main(String[] args)
    {
        int[] arr={2,1,0,3,6,5,7,4};
        PosArrayToBST p=new PosArrayToBST();
        System.out.println(p.isPosArray(arr));
        Node r=p.posArrayToBST(arr);
        System.out.println();
        p.posByStack(r);
    }
}
