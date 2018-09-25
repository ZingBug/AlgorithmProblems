package Algorithms_4th.Searching;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 基于二叉查找树的符号表
 * 能够将链表插入的灵活性和有序数组查找的高效性结合起来
 * 此符号表实现了所有的API函数，可供二叉树学习参考
 * 每个公有方法都对应着一个私有方法，多数方法都是通过递归实现的
 * 值得学习！！！！！
 * by ZingBug 2017/9/4
 */
public class BST<Key extends Comparable<Key>,Value> {
    private Node root;//二叉查找树的跟结点
    private class Node
    {
        private Key key;//键
        private Value val;//值
        private Node left,right;//指向子树的链接
        private int N;
        public Node(Key key,Value val,int N)
        {
            this.key=key;
            this.val=val;
            this.N=N;
        }
    }
    public int size()
    {
        return size(root);
    }
    private int size(Node x)
    {
        if(x==null)
            return 0;
        else
            return x.N;
    }
    public Value get(Key key)
    {
        return get(root,key);
    }
    private Value get(Node x,Key key)
    {
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp<0)
        {
            //小于根节点，在左侧
            return get(x.left,key);
        }
        else if(cmp>0)
        {
            //大于根节点，在右侧
            return get(x.right,key);
        }
        else
        {
            //等于，就是根节点
            return x.val;
        }
    }
    public void put(Key key,Value val)
    {
        root=put(root,key,val);
    }
    private Node put(Node x,Key key,Value val)
    {
        if(x==null) return new Node(key,val,1);
        int cmp=key.compareTo(x.key);
        if(cmp<0)
        {
            //小于根节点，在左侧插入
            x.left=put(x.left,key,val);//递归回来一层一层往上去
        }
        else if(cmp>0)
        {
            //大于根节点，在右侧插入
            x.right=put(x.right,key,val);
        }
        else
        {
            //等于根节点，更新根节点值
            x.val=val;
        }
        x.N=size(x.right)+size(x.left)+1;
        return x;
    }
    public Key min()
    {
        //获得最小的key
        return min(root).key;
    }
    private Node min(Node x)
    {
        if(x.left==null) return x;
        return min(x.left);
    }
    public Key max()
    {
        //获得最大的key
        return max(root).key;
    }
    private Node max(Node x)
    {
        if(x.right==null) return x;
        return max(x.right);
    }
    public Key floor(Key key)
    {
        //小于等于key的最大键
        Node x=floor(root,key);
        if(x==null) return null;
        return x.key;
    }
    private Node floor(Node x,Key key)
    {
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp==0)
        {
            return x;
        }
        if(cmp<0)
        {
            //key比这个节点小
            return floor(x.left,key);
        }
        //key比这个节点大
        Node t=floor(x.right,key);
        if(t!=null) return t;
        else return x;
    }
    public Key ceiling(Key key)
    {
        //大于等于key的最小键
        Node x=ceiling(root,key);
        if(x==null) return null;
        else return x.key;

    }
    private Node ceiling(Node x,Key key)
    {
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp==0)
        {
            return x;
        }
        if(cmp>0)
        {
            //key比这个节点大
            return ceiling(x.right,key);
        }
        //key比这个节点小
        Node t=ceiling(x.left,key);
        if(t!=null) return t;
        else return x;
    }
    public Key select(int k)
    {
        //返回排名为k的结点
        return select(root,k).key;
    }
    private Node select(Node x,int k)
    {
        if(x==null) return null;
        int t=size(x.left);
        if(t>k)
        {
            //左子树中的结点数t大于k
            return select(x.left,k);
        }
        else if(t<k)
        {
            //左子树的结点数t小于k
            return select(x.right,k);
        }
        else
        {
            return x;
        }
    }
    public int rank(Key key)
    {
        //返回key的排名
        return rank(root,key);
    }
    private int rank(Node x,Key key)
    {
        //返回以x为根节点的子树中小于key的键的数量
        if(x==null) return 0;
        int cmp=key.compareTo(x.key);
        if(cmp<0)
        {
            //key在左子树
            return rank(x.left,key);
        }
        else if(cmp>0)
        {
            //key在右子树
            return size(x.left)+1+rank(x.right,key);
        }
        else
        {
            //key就是本根结点
            return size(x.left);
        }
    }
    public void deleteMin()
    {
        root=deleteMin(root);
    }
    private Node deleteMin(Node x)
    {
        if(x.left==null)
        {
            return x.right;
        }
        x.left=deleteMin(x.left);
        x.N=size(x.left)+size(x.right)+1;
        return x;
    }
    public void deleteMax()
    {
        root=deleteMax(root);
    }
    private Node deleteMax(Node x)
    {
        if(x.right==null)
        {
            return x.left;
        }
        x.right=deleteMax(x.right);
        x.N=size(x.left)+size(x.right)+1;
        return x;
    }
    public void delete(Key key)
    {
        //删除操作
        root=delete(root,key);
    }
    private Node delete(Node x,Key key)
    {
        if(x==null) return null;
        int cmp=key.compareTo(x.key);
        if(cmp>0)
        {
            x.right=delete(x.right,key);
        }
        else if(cmp<0)
        {
            x.left=delete(x.left,key);
        }
        else
        {
            //找到key
            if(x.right==null) return x.left;
            if(x.left==null) return x.right;
            Node t=x;
            x=min(t.right);
            x.right=deleteMin(t.right);
            x.left=t.left;
        }
        x.N=size(x.left)+size(x.right)+1;
        return x;
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
        int cmplo=lo.compareTo(x.key);//与最小值比较
        int cmphi=hi.compareTo(x.key);//与最大值比较
        if(cmplo<0)
        {
            //当前结点大于最小值
            keys(x.left,list,lo,hi);//去寻找最小值结点
        }
        if(cmplo<=0&&cmphi>=0)
        {
            //当前结点大于等于最小值，并且小于等于最大值
            list.add(x.key);
        }
        if(cmphi>0)
        {
            //当前结点小于最大值
            keys(x.right,list,lo,hi);//去寻找最大值结点
        }
    }
    public boolean contains(Key key)
    {
        //是否包含某个键
        Key k=contains(root,key);
        if(k==null)
        {
            return false;
        }
        else if(k.equals(key))
        {
            return true;
        }
        return false;
    }
    private Key contains(Node x,Key key)
    {
        if(x==null)
            return null;
        int cmp=key.compareTo(x.key);
        if(cmp<0)
        {
            return contains(x.left,key);
        }
        else if(cmp>0)
        {
            return contains(x.right,key);
        }
        else
        {
            return x.key;
        }
    }
    public static void main(String[] agrs)
    {
        BST<Integer,String> st=new BST<>();
        st.put(5,"5GitHub");
        for(int i=0;i<10;i++)
        {
            st.put(i,i+"Github");
        }
        System.out.println("当前符号表大小为："+st.size());
        st.put(5,"ZingBug");
        for(int key:st.keys())
        {
            System.out.print(st.get(key)+" ");
        }
        System.out.println();
        System.out.println("排名第5的是"+st.get(st.select(5)));
        System.out.println("key是5的排名是"+st.rank(5));
        st.deleteMin();//删除最小元素
        st.deleteMax();//删除最大元素
        st.delete(3);//删除key为3的元素
        System.out.println("小于等于3的最大键为："+st.floor(3));
        System.out.println("大于等于3的最小键为："+st.ceiling(3));
        System.out.println("当前符号表大小为："+st.size());
        for(int key:st.keys())
        {
            System.out.print(st.get(key)+" ");
        }
        System.out.println();
        for(int key:st.keys(3,6))
        {
            System.out.print(st.get(key)+" ");
        }
        System.out.println();
        System.out.println(st.contains(4));

    }
}
