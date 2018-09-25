package Algorithms_4th.Searching;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 红黑二叉查找树
 */
public class RedBlackBST<Key extends Comparable<Key>,Value> {
    private Node root;

    private static final boolean RED=true;
    private static final boolean BLACK=false;
    private class Node
    {
        Key key;//键
        Value value;//值
        Node left,right;//左右子树
        int N;//这可子树中的结点总数
        boolean color;//由其父节点指向它的链接的颜色

        Node(Key key,Value val,int N,boolean color)
        {
            this.key=key;
            this.value=val;
            this.N=N;
            this.color=color;
        }
    }
    //测试一个结点和它的父节点之间的链接颜色
    private boolean isRed(Node x)
    {
        if(x==null)
            return false;
        return x.color==RED;
    }
    Node rotateLeft(Node h)
    {
        //左旋转
        Node x=h.right;
        h.right=x.left;
        x.left=h;
        x.color=h.color;
        h.color=RED;
        x.N=h.N;
        h.N=1+size(h.left)+size(h.right);
        return x;
    }
    Node rotateRight(Node h)
    {
        //右旋转
        Node x=h.left;
        h.left=x.right;
        x.right=h;
        x.color=h.color;
        h.color=RED;
        x.N=h.N;
        h.N=1+size(h.left)+size(h.right);
        return x;
    }
    private void flipColors(Node h)
    {
        h.color=RED;
        h.left.color=BLACK;
        h.right.color=BLACK;
    }
    public void put(Key key,Value val)
    {
        //查找key，找到则更新其值，否则为它新建一个结点
        root=put(root,key,val);
        root.color=BLACK;
    }
    private Node put(Node h,Key key,Value val)
    {
        if(h==null)
        {
            return new Node(key,val,1,RED);
        }
        int cmp=key.compareTo(h.key);
        if(cmp<0)
        {
            //key小于当前结点
            h.left=put(h.left,key,val);
        }
        else if(cmp>0)
        {
            //key大于当前结点
            h.right=put(h.right,key,val);
        }
        else
        {
            h.value=val;
        }
        if(isRed(h.right)&&!isRed(h.left))
        {
            //右链接是红链接，左链接是黑链接
            //左旋转
            h=rotateLeft(h);
        }
        if(isRed(h.left)&&isRed(h.left.left))
        {
            //连续两个左链接是红链接
            //右旋转
            h=rotateRight(h);
        }
        if(isRed(h.left)&&isRed(h.right))
        {
            //左右都是红链接
            flipColors(h);
        }
        h.N=1+size(h.left)+size(h.right);
        return h;
    }
    public Value get(Key key)
    {
        if(key==null)
        {
            throw new IllegalArgumentException("Null");
        }
        return get(root,key).value;
    }
    private Node get(Node h,Key key)
    {
        if(h==null)
        {
            return null;
        }
        int cmp=key.compareTo(h.key);
        if(cmp>0)
        {
            //key值大于当前结点
            return get(h.right,key);
        }
        else if(cmp<0)
        {
            //key值小于当前结点
            return get(h.left,key);
        }
        else
        {
            //key值等于当前结点
            return h;
        }
    }
    private int size(Node x)
    {
        if(x==null)
        {
            return 0;
        }
        return x.N;
    }
    public Key min()
    {
        //求最小值
        return min(root).key;
    }
    private Node min(Node x)
    {
        if(x.left==null)
        {
            return x;
        }
        return min(x.left);
    }
    public Key max()
    {
        //求最大值
        return max(root).key;
    }
    private Node max(Node x)
    {
        if(x.right==null)
        {
            return x;
        }
        return max(x.right);
    }
    public Iterable<Key> keys()
    {
        //遍历所有key
        return keys(min(),max());
    }
    public Iterable<Key> keys(Key lo,Key hi)
    {
        //范围查找
        List<Key> list=new ArrayList<>();
        keys(root,list,lo,hi);
        return list;

    }
    private void keys(Node x,List<Key> list,Key lo,Key hi)
    {
        if(x==null) return;
        int cmplo=lo.compareTo(x.key);
        int cmphi=hi.compareTo(x.key);
        if(cmplo<0)
        {
            //当前结点大于最小值
            keys(x.left,list,lo,hi);//去寻找最小值结点
        }
        if(cmplo<=0&&cmphi>=0)
        {
            //当前结点在最小值和最大值的范围之间
            list.add(x.key);
        }
        if(cmphi>0)
        {
            //当前结点小于最大值
            keys(x.right,list,lo,hi);
        }
    }
    public boolean isEmpty()
    {
        return root==null;
    }
    public boolean contains(Key key)
    {
        return get(key)!=null;
    }
    /*
    private Node delete(Node h,Key key)
    {
        if(key.compareTo(h.key)<0)
        {
            if(!isRed(h.left)&&!isRed(h.left.left))
            {
                h=moveRedLeft(h);
            }
            h.left=delete(h.left,key);
        }
        else
        {
            if(isRed(h.left))
            {
                h=rotateRight(h);
            }
            if(key.compareTo(h.key)==0&&h.right==null)
            {
                return null;
            }
            if(!isRed(h.right)&&!isRed(h.right.left))
            {
                h=moveRedRight(h);
            }
            if(key.compareTo(h.key)==0)
            {
                Node x=min(h.right);
                h.key=x.key;
                h.value=x.value;
                //h.right=
            }
        }
    }
    */
    private Node moveRedLeft(Node h)
    {
        flipColors(h);
        if(isRed(h.right.left))
        {
            h.right=rotateRight(h.right);
            h=rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
    private Node moveRedRight(Node h)
    {
        flipColors(h);
        if(isRed(h.left.left))
        {
            h=rotateRight(h);
            flipColors(h);
        }
        return h;
    }
    private Node balance(Node h)
    {
        if((isRed(h.right)))
        {
            h=rotateLeft(h);
        }
        if(isRed(h.left)&&isRed(h.left.left))
        {
            h=rotateRight(h);
        }
        if(isRed(h.left)&&isRed(h.right))
        {
            flipColors(h);
        }
        return h;
    }
    public static void main(String[] args)
    {
        RedBlackBST<Integer,String> st=new RedBlackBST<>();
        for(int i=0;i<10;i++)
        {
            st.put(i,i+"Github");
        }
        for(int i:st.keys())
        {
            System.out.print(st.get(i)+" ");
        }
        System.out.println();
    }

}
