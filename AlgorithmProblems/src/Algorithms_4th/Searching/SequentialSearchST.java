package Algorithms_4th.Searching;
import java.util.ArrayList;
import java.util.List;
/**
 * 基于无序链表的符号表
 * 链表首元素在链表结尾处，在链表开头处插入
 * 着重看看即时性的delete函数
 * 另外，此类没有通过iterator()方法来返回一个实现了hasNext()和next()方法的迭代器
 * 反而是，定义了keys()方法来返回一个Iterable<Key>对象以方便用例遍历所有的键
 * 对应图3.1.2
 * @param <Key> 键
 * @param <Value> 值
 * by ZingBug 2017/9/3
 */
public class SequentialSearchST<Key,Value> {
    private Node first;//链表首结点
    private int N;//连接结点个数
    private class Node
    {
        private Key key;
        private Value val;
        private Node next;
        public Node(Key key,Value val,Node next)
        {
            this.key=key;
            this.val=val;
            this.next=next;
        }
    }
    public SequentialSearchST()
    {
        this.N=0;
    }
    public Value get(Key key)
    {
        //查找给定的键，返回相关联的值
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        for(Node x=first;x!=null;x=x.next)
        {
            if(key.equals(x.key))
            {
                return x.val;
            }
        }
        return null;
    }
    public void put(Key key, Value val)
    {
        //查找给定的键，找到则更新键，否则在表中新建结点
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        for(Node x=first;x!=null;x=x.next)
        {
            if(key.equals(x.key))
            {
                x.val=val;//找到给定的键，更新
                return;//返回
            }
        }
        first=new Node(key,val,first);//未找到给定的键，新建结点
        N++;
    }
    public int size()
    {
        return N;
    }
    public void delete(Key key)
    {
        //即时性的delete，立刻从表中删除掉指定的键
        if(key==null)
            throw new IllegalArgumentException("argument to delete() is null");
        first=delete(first,key);
    }
    private Node delete(Node x, Key key)
    {
        //这是一个递归函数
        if (x == null) return null;
        if (key.equals(x.key)) {
            N--;
            return x.next;//返回后会一层一层往上走
        }
        x.next = delete(x.next, key);//如果下一层命中键，则相当于x.next=x.next.next，相当于删除键后右侧整体左移
        return x;
    }
    public Iterable<Key> keys()
    {
        //实现了迭代方法，遍历了所有的键
        List<Key> list=new ArrayList<>();
        for(Node x=first;x!=null;x=x.next)
        {
            list.add(x.key);
        }
        return list;
    }
    public boolean contains(Key key)
    {
        if(key==null)
            throw new IllegalArgumentException("argument to contains() is null");
        for(Node x=first;x!=null;x=x.next)
        {
            if(key.equals(x.key))
            {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args)
    {
        SequentialSearchST<Integer,String> st=new SequentialSearchST<>();
        for(int i=0;i<10;i++)
        {
            st.put(i,i+"Github");
        }
        System.out.println(st.get(2));
        st.put(2,"ZingBug");
        System.out.println(st.get(2));
        st.delete(2);//删除操作
        System.out.println(st.get(3));
        for(int key:st.keys())
        {
            System.out.print(st.get(key)+" ");
        }
        System.out.println();
        System.out.println("The size is "+st.size());
    }
}
