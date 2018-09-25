package Algorithms_4th.Strings;

import Algorithms_4th.Graphs.Queue;

/**
 * 基于三向单词查找树的符号表
 * Created by ZingBug on 2017/10/24.
 */
public class TST<Vaule> {
    private Node root;//根节点

    private class Node
    {
        char c;//字符
        Node left,mid,right;//左中右三向单词查找树
        Vaule val;//和字符串相关联的值
    }
    //键key所对应的值
    public Vaule get(String key)
    {
        Node x=get(root,key,0);
        if(x==null)
        {
            return null;
        }
        else
        {
            return x.val;
        }
    }
    private Node get(Node x,String key,int d)
    {
        if(x==null)
        {
            return null;
        }
        if(d==key.length())
        {
            return x;
        }
        char c=key.charAt(d);
        if(c<x.c)
        {
            return get(x.left,key,d);
        }
        else if(c>x.c)
        {
            return get(x.right,key,d);
        }
        else if(d<key.length()-1)
        {
            return get(x.mid,key,d+1);
        }
        else
        {
            return x;
        }
    }
    //向表中插入键值对
    public void put(String key,Vaule val)
    {
        root=put(root,key,val,0);
    }
    private Node put(Node x,String key,Vaule val,int d)
    {
        char c=key.charAt(d);
        if(x==null)
        {
            x=new Node();
            x.c=c;
        }
        if(c<x.c)
        {
            x.left=put(x.left,key,val,d);
        }
        else if(c>x.c)
        {
            x.right=put(x.right,key,val,d);
        }
        else if(d<key.length()-1)
        {
            x.mid=put(x.mid,key,val,d+1);
        }
        else
        {
            x.val=val;
        }
        return x;
    }
    //返回符号表中所有键
    public Iterable<String> keys()
    {
        Queue<String> q=new Queue<>();
        collect(root,"",q);
        return q;
    }
    //返回所有以s为前缀的键
    public Iterable<String> keysWithPrefix(String pre)
    {
        Queue<String> q=new Queue<>();
        Node x=get(root,pre,0);
        if(x==null)
        {
            return q;
        }
        if(x.val!=null)
        {
            q.enqueue(pre);
        }
        collect(x.mid,pre,q);
        return q;
    }
    private void collect(Node x,String pre,Queue<String> q)
    {
        if(x==null)
        {
            return;
        }
        collect(x.left,pre,q);
        if(x.val!=null)
        {
            q.enqueue(pre+x.c);
        }
        collect(x.mid,pre+x.c,q);
        collect(x.right,pre,q);
    }
    public static void main(String[] args)
    {
        TST<Integer> st=new TST<>();
        st.put("by",4);
        st.put("sea",2);
        st.put("sells",1);
        st.put("she",0);
        st.put("shells",3);
        st.put("the",5);
        System.out.println(st.get("she"));
        for(String temp:st.keysWithPrefix("s"))
        {
            System.out.print(temp+" ");
        }
        System.out.println();
        for(String temp:st.keys())
        {
            System.out.print(temp+" ");
        }
    }
}
