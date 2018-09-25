package Algorithms_4th.Searching;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 基于线性探测法的散列表
 * by ZingBug 2017/9/22
 * @param <Key>
 * @param <Value>
 */
public class LinearProbingHashST<Key,Value> {
    private int N;//符号表中键值对的总数
    private int M=16;//线性探测表的大小
    private Key[] keys;//键
    private Value[] vals;//值
    public LinearProbingHashST()
    {
        this(16);
    }
    public LinearProbingHashST(int M)
    {
        this.M=M;
        keys=(Key[])new Object[M];
        vals=(Value[])new Object[M];
    }
    private int hash(Key key)
    {
        return (key.hashCode()&0x7fffffff)%M;
    }
    public void put(Key key,Value val)
    {
        //如果一个新建的散列值是一个空元素，那么就将它保存在那里；
        //如果不是，我们就顺序查找一个空元素来保存它。
        if(N>M/2)
            resize(2*M);
        int i;
        for(i=hash(key);keys[i]!=null;i=(i+1)%M)
        {
            if(keys[i].equals(key))
            {
                vals[i]=val;
                return;
            }
        }
        keys[i]=key;
        vals[i]=val;
        N++;
    }
    public Value get(Key key)
    {
        for(int i=hash(key);keys[i]!=null;i=(i+1)%M)
        {
            if(keys[i].equals(key))
            {
                return vals[i];
            }
        }
        return null;
    }
    private void resize(int cap)
    {
        LinearProbingHashST<Key,Value> t;
        t=new LinearProbingHashST<>(cap);
        for(int i=0;i<M;i++)
        {
            if(keys[i]!=null)
            {
                t.put(keys[i],vals[i]);
            }
        }
        keys=t.keys;
        vals=t.vals;
        M=t.M;
    }
    public void delete(Key key)
    {
        //删除键
        if(!contains(key))
            return;
        int i=hash(key);
        while (!key.equals(keys[i]))
        {
            i=(i+1)%M;
        }
        keys[i]=null;
        vals[i]=null;
        i=(i+1)%M;
        while (keys[i]!=null)
        {
            Key keyToRedo=keys[i];
            Value valToRedo=vals[i];
            keys[i]=null;
            vals[i]=null;
            N--;
            put(keyToRedo,valToRedo);
            i=(i+1)%M;
        }
        N--;
        if(N>0&&N==M/8)
        {
            resize(M/2);
        }
    }
    private boolean contains(Key key)
    {
        //确定是否包含某键
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        for(int i=hash(key);keys[i]!=null;i=(i+1)%M)
        {
            if(keys[i].equals(key))
            {
                return true;
            }
        }
        return false;
    }
    public Iterable<Key> keys()
    {
        //遍历所有键
        List<Key> list=new ArrayList<>();
        for(int i=0;i<M;i++)
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
        LinearProbingHashST<Integer,String> st;
        st=new LinearProbingHashST<>(17);
        for(int i=0;i<10;i++)
        {
            st.put(i,i+"github");
        }
        st.delete(3 );
        for(int i:st.keys())
        {
            System.out.print(st.get(i)+" ");
        }
        System.out.println();
    }
}
