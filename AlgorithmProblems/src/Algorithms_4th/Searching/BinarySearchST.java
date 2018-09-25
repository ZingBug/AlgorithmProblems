package Algorithms_4th.Searching;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找，基于有序数组
 * 使用的数据结构是一对平行的数组，一个存储键一个存储值
 * put()方法会在插入新元素前将所有较大的键向后移动一格
 * rank()方法实现了二分查找法
 * 可自适应调整数组大小
 * by ZingBug 2017/9/4
 */
public class BinarySearchST<Key extends Comparable<Key>,Value>
{
    private Key[] keys;
    private Value[] vals;
    private int N;
    public BinarySearchST(int capacity)
    {
        keys=(Key[]) new Comparable[capacity];
        vals=(Value[]) new Object[capacity];
    }
    public int size()
    {
        return N;
    }
    public Value get(Key key)
    {
        if(isEmpty()) return null;
        if(key==null) throw new IllegalArgumentException("argument to get() is null");
        int i=rank(key);
        if(i<N&&keys[i].compareTo(key)==0)
            return vals[i];
        else
            return null;
    }
    public void put(Key key,Value val)
    {
        //查找键，找到则更新值，否则创建新的元素
        int i=rank(key);
        if(i<N&&keys[i].compareTo(key)==0)
        {
            vals[i]=val;
            return;
        }
        if(N+1==keys.length)
        {
            //增大数组
            resize(2*keys.length);
        }
        for(int j=N;j>i;j--)//这个N正好是数组最大索引+1
        {
            keys[j]=keys[j-1];
            vals[j]=vals[j-1];
        }
        keys[i]=key;
        vals[i]=val;
        N++;
    }
    public void delete(Key key)
    {
        //即时性的delete，立刻从表中删除掉指定的键
        if(key==null)
            throw new IllegalArgumentException("argument to delete() is null");
        int i=rank(key);
        if(i<N&&keys[i].compareTo(key)==0)
        {
            //存在这个键
            for(int j=i;j<N-1;j++)
            {
                keys[j]=keys[j+1];
                vals[j]=vals[j+1];
            }
            N--;
            keys[N]=null;
            vals[N]=null;
            if(N>0&&N==keys.length/4)
            {
                //缩小数组大小
                resize(keys.length/2);
            }
        }
    }
    public int rank(Key key)
    {
        //如果表中存在该键，则放回该键的位置，也就是表中小于它的键的数量
        //如果表中不存在该键，则放回表中小于它的键的数量
        int lo=0;
        int hi=N-1;
        while (lo<=hi)
        {
            int mid=lo+(hi-lo)/2;
            int cmp=key.compareTo(keys[mid]);
            if(cmp>0)
            {
                lo=mid+1;
            }
            else if(cmp<0)
            {
                hi=mid-1;
            }
            else
            {
                return mid;
            }
        }
        return lo;
    }
    public Iterable<Key> keys()
    {
        List<Key> list=new ArrayList<>();
        for(int i=0;i<N;i++)
        {
            list.add(keys[i]);
        }
        return list;
    }
    public boolean isEmpty()
    {
        return N==0;
    }
    private void resize(int max)
    {
        //调整数组大小
        Key[] tempKey=(Key[]) new Comparable[max];
        Value[] tempVal=(Value[]) new Object[max];
        for(int i=0;i<N;i++)
        {
            tempKey[i]=keys[i];
            tempVal[i]=vals[i];
        }
        keys=tempKey;
        vals=tempVal;
    }
    public static void main(String[] args)
    {
        BinarySearchST<Integer,String> st=new BinarySearchST<>(6);
        for(int i=0;i<10;i++)
        {
            st.put(i,i+"Github");
        }
        st.put(2,"ZingBug");

        for(int key:st.keys())
        {
            System.out.print(st.get(key)+" ");
        }
        System.out.println();
        st.delete(2);
        st.delete(5);
        for(int key:st.keys())
        {
            System.out.print(st.get(key)+" ");
        }
        System.out.println();

    }
}
