package Algorithms_4th.Searching;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于二分法查找的集合
 * 相当于hashset
 * 只需要能够将键插入到表中并检测一个键在表中是否存在，不允许重复的键
 * 不需要处理值
 * by ZingBug 2017/9/23
 */
public class SET<Key extends Comparable<Key>> {
    private static final int INT_CAPACITY=4;//数组初始大小
    private Key[] keys;//键保存数组
    private int N;//键的数量
    public SET()
    {
        keys=(Key[])new Comparable[INT_CAPACITY];
        N=0;
    }
    public int size()
    {
        return N;
    }
    public boolean isEmpty()
    {
        return size()==0;
    }
    public void add(Key key)
    {
        int i=rank(key);
        if(i<N&&keys[i].compareTo(key)==0)
        {
            //重复写入
            return;
        }
        if(N+1==keys.length)
        {
            resize(keys.length*2);
        }
        for(int j=N;j>i;j--)
        {
            keys[j]=keys[j-1];
        }
        keys[i]=key;
        N++;
    }
    public void delete(Key key)
    {
        if(key==null)
            throw new IllegalArgumentException("argument to delete() is null");
        int i=rank(key);
        if(i<N&&keys[i].compareTo(key)==0)
        {
            //存在这个键
            for(int j=i;j<N;j++)
            {
                keys[j]=keys[j+1];
            }
            N--;
            keys[N]=null;
            //调整数组大小
            if(N>0&&N==keys.length/4)
            {
                resize(keys.length/2);
            }
        }
    }
    public boolean contains(Key key)
    {
        if(key==null)
            throw new IllegalArgumentException("argument to delete() is null");
        int i=rank(key);
        if(i<N&&keys[i].compareTo(key)==0)
        {
            return true;
        }
        return false;
    }
    private void resize(int cap)
    {
        Key[] keys1=(Key[]) new Comparable[cap];
        for(int i=0;i<N;i++)
        {
            keys1[i]=keys[i];
        }
        keys=keys1;
    }
    private int rank(Key key)
    {
        int lo=0;
        int hi=N-1;
        while (lo<=hi)
        {
            int mid=lo+(hi-lo)/2;
            int cmp=key.compareTo(keys[mid]);
            if(cmp<0)
            {
                hi=mid-1;
            }
            else if(cmp>0)
            {
                lo=mid+1;
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
            if(keys[i]!=null)
            {
                list.add(keys[i]);
            }
        }
        return list;
    }
    public static void main(String[] args)
    {
        SET<Integer> set=new SET<>();
        for(int i=0;i<10;i++)
        {
            set.add(i);
        }
        System.out.println("Contain 3:"+set.contains(3));
        set.delete(3);
        System.out.println("Contain 3:"+set.contains(3));
        for(int key:set.keys())
        {
            System.out.print(key+" ");
        }
        System.out.println();
    }
}
