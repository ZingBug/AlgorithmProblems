package Algorithms_4th.Searching;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 基于拉链法的散列表
 * by ZingBug 2017/9/21
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST<Key,Value>
{
    private int N;//键值对总数
    private int M;//散列表的大小
    private SequentialSearchST<Key,Value>[] st;//存放链表对象的数组

    public SeparateChainingHashST()
    {
        //默认构造函数
        this(997);
    }

    public SeparateChainingHashST(int M)
    {
        //创建M条链表
        this.M=M;
        st=(SequentialSearchST<Key,Value>[])new SequentialSearchST[M];
        for(int i=0;i<M;i++)
        {
            st[i]=new SequentialSearchST<>();
        }
    }
    private int hash(Key key)
    {
        return (key.hashCode()&0x7fffffff)%M;
    }
    public Value get(Key key)
    {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return (Value)st[hash(key)].get(key);
    }
    public void put(Key key,Value val)
    {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if(val==null)
        {
            delete(key);
            return;
        }
        if(N>=10*M)
            resize(2*M);

        int i=hash(key);
        if(!st[i].contains(key))
        {
            N++;
        }
        st[i].put(key,val);
    }
    public void delete(Key key)
    {
        if(key==null)
            throw new IllegalArgumentException("argument to delete() is null");
        int i=hash(key);
        if(st[i].contains(key))
        {
            st[i].delete(key);
            N--;
        }
        if(N<=2*M)
        {
            resize(M/2);
        }
    }
    public Iterable<Key> keys()
    {
        List<Key> list=new ArrayList<>();
        for(SequentialSearchST<Key,Value> searchST:st)
        {
            Iterator<Key> iterator=searchST.keys().iterator();//讲iterator元素转到list
            while (iterator.hasNext())
            {
                list.add(iterator.next());
            }
        }
        return list;
    }
    private void resize(int cap)
    {
        SeparateChainingHashST<Key,Value> t;
        t=new SeparateChainingHashST<>(cap);
        for(int i=0;i<M;i++)
        {
            for(Key key:st[i].keys())
            {
                t.put(key,st[i].get(key));
            }
        }
        this.M=t.M;
        this.N=t.N;
        this.st=t.st;
    }
    public int size()
    {
        return N;
    }
    public boolean isEmpty()
    {
        return size()==0;
    }
    public static void main(String[] args)
    {
        SeparateChainingHashST<Integer,String> st=new SeparateChainingHashST<>();
        for(int i=0;i<10;i++)
        {
            st.put(i,i+"github");
        }
        st.delete(2);
        System.out.println("size:"+st.size());
        for (int key:st.keys())
        {
            System.out.print(st.get(key)+" ");
        }
        System.out.println();
    }
}
