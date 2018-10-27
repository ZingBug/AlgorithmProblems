package Coding_Interview_Guide.StackAndQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个没有重复元素的数组arr，定义arr上的MaxTree如下：MaxTree的根节点为arr中最大的数，
 * 根节点的左子树为数组中最大数左边部分的MaxTree，右子树为数组中最大数右边部分的MaxTree。
 * 请根据给定的数组arr，设计一个算法构造这个数组的MaxTree。
 *
 * 解题思路：
 * 1、用递归方法来实现，思路简单，但复杂度太高。
 * 2、用栈的方法实现。时间复杂度为O(N)，空间复杂度为O(N)。Page22
 *
 * Created by ZingBug on 2018/10/9.
 */
public class GetMaxTree {
    //用递归方法去做很简单，但是时间复杂度为O(N^2)，超时了。
    private Node getMaxTreeByRecursion(int[] arr)
    {
        if(arr==null||arr.length<1)
        {
            return null;
        }
        return recursion(arr,0,arr.length);
    }

    private Node recursion(int[] arr,int start,int end)
    {
        if(start<0||end<=start)
        {
            return null;
        }
        int max=Integer.MIN_VALUE;
        //先寻找最大的值
        int index=start;
        for(int i=start;i<end;i++)
        {
            if(arr[i]>max)
            {
                max=Math.max(max,arr[i]);
                index=i;
            }

        }
        Node node=new Node(max);
        node.left=recursion(arr,start,index);
        node.right=recursion(arr,index+1,end);
        return node;
    }

    //用两个hashMap分别来存左边的和右边的大于他的第一个数，然后再遍历一次数组构造MaxTree
    private Node getMaxTreeByStack(int[] arr)
    {
        Node[] nArr=new Node[arr.length];
        for(int i=0;i!=arr.length;i++)
        {
            nArr[i]=new Node(arr[i]);
        }
        Stack<Node> stack=new Stack<>();
        HashMap<Node,Node> lBigMap=new HashMap<>();
        HashMap<Node,Node> rBigMap=new HashMap<>();
        //在lBigMap中得到每个数往左第一个比它大的数字
        for(int i=0;i!=nArr.length;i++)
        {
            Node curNode=nArr[i];
            while (!stack.isEmpty()&&stack.peek().value<curNode.value)
            {
                popStackSetMap(stack,lBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty())
        {
            popStackSetMap(stack,lBigMap);
        }
        //在rBigMap中得到每个数往左第一个比它大的数字
        for(int i=nArr.length-1;i!=-1;i--)
        {
            Node curNode=nArr[i];
            while (!stack.isEmpty()&&stack.peek().value<curNode.value)
            {
                popStackSetMap(stack,rBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty())
        {
            popStackSetMap(stack,rBigMap);
        }
        Node head=null;
        for(int i=0;i!=nArr.length;i++)
        {
            Node curNode =nArr[i];
            Node left=lBigMap.get(curNode);
            Node right=rBigMap.get(curNode);
            if(left==null&&right==null)
            {
                head=curNode;
            }
            else if(left==null)//最大值在右边
            {
                if(right.left==null)
                {
                    right.left=curNode;
                }
                else
                {
                    right.right=curNode;
                }
            }
            else if(right==null)//最大值在左边
            {
                if(left.left==null)
                {
                    left.left=curNode;
                }
                else
                {
                    left.right=curNode;
                }
            }
            else
            {
                Node parent=left.value<right.value?left:right;
                if(parent.left==null)
                {
                    parent.left=curNode;
                }
                else
                {
                    parent.right=curNode;
                }
            }
        }
        return head;
    }

    private void popStackSetMap(Stack<Node> stack,HashMap<Node,Node> map)
    {
        Node popNode=stack.pop();
        if(stack.isEmpty())
        {
            map.put(popNode,null);
        }
        else
        {
            map.put(popNode,stack.peek());
        }
    }

    //也是通过栈实现的
    private Node getMaxTreeByStackSimple(int[] arr)
    {

        List<Integer> res=new ArrayList<>();// 数组元素在树中父亲节点的编号
        Stack<Integer> stack=new Stack<>();// 栈 以递减方式存放元素值的位置index
        for(int i=0;i<arr.length;i++)
        {
            int pos=-1;

            // 当前访问的元素比栈顶大 栈中元素需要出栈
            while (!stack.isEmpty()&&arr[i]>arr[stack.peek()])
            {
                int tmp=stack.pop();
                if(res.get(tmp)==-1||arr[i]<arr[res.get(tmp)])
                {
                    res.set(tmp,i);
                }
            }
            //找到了最近的比arr[i]大的数字
            if(!stack.isEmpty())
            {
                pos= stack.peek();
            }
            stack.push(i);
            res.add(pos);
        }
        Node head=null;
        Node[] nArr=new Node[arr.length];
        for(int i=0;i!=arr.length;i++)
        {
            nArr[i]=new Node(arr[i]);
        }
        for(int i=0;i!=res.size();i++)
        {
            Node curNode=nArr[i];
            if(res.get(i)==-1)
            {
                head=curNode;
            }
            else
            {
                Node parentNode=nArr[res.get(i)];
                if(parentNode.left==null)
                {
                    parentNode.left=curNode;
                }
                else
                {
                    parentNode.right=curNode;
                }
            }
        }
        return head;
    }

    public static void main(String[] args)
    {
        GetMaxTree g=new GetMaxTree();
        int[] arr={3,4,5,1,2};
        Node node1=g.getMaxTreeByRecursion(arr);
        Node node2=g.getMaxTreeByStack(arr);
        Node node3=g.getMaxTreeByStackSimple(arr);
        System.out.println();
    }
}
