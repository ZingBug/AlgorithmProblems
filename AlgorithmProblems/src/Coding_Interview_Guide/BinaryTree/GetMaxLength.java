package Coding_Interview_Guide.BinaryTree;

import java.util.HashMap;
import java.util.Map;

/**
 * 在二叉树中找到累加和为指定值的最长路径长度
 * 给定一颗二叉树的头结点head和一个32位整数sum，二叉树节点值类型为整形，求累加和为sum的最长路径长度。路径是指从某个节点往下，每次最多选择的一个孩子节点或者不选所形成的节点链。
 * 和“求未排序数组中累加和为规定值的最长子数组长度”问题类似
 * Page115
 * Created by ZingBug on 2018/12/3.
 */
public class GetMaxLength {
    private int getMaxLength(Node head,int sum)
    {
        Map<Integer,Integer> map=new HashMap<>();
        map.put(0,0);
        return preOrder(head,sum,1,0,0,map);
    }

    private int preOrder(Node head,int sum,int level,int maxLen,int preSum,Map<Integer,Integer> map)
    {
        if(head==null)
        {
            return maxLen;
        }
        int cursum=preSum+head.value;
        if(!map.containsKey(cursum))
        {
            map.put(cursum,level);
        }
        if(map.containsKey(cursum-sum))
        {
            maxLen=Math.max(maxLen,level-map.get(cursum-sum));//到当前层的最大长度
        }
        maxLen=preOrder(head.left,sum,level+1,maxLen,cursum,map);
        maxLen=preOrder(head.right,sum,level+1,maxLen,cursum,map);
        if(level==map.get(cursum))
        {
            map.remove(cursum);
        }
        return maxLen;
    }

    public static void main(String[] args)
    {
        Node root=new Node(-3);
        Node root1=new Node(3);
        Node root2=new Node(0);
        root2.left=new Node(1);
        root2.right=new Node(6);
        root1.left=new Node(1);
        root1.right=root2;
        root.left=root1;
        root.right=new Node(-9);
        root.right.left=new Node(2);
        root.right.right=new Node(1);

        GetMaxLength g=new GetMaxLength();

        System.out.println(g.getMaxLength(root,6));
    }
}
