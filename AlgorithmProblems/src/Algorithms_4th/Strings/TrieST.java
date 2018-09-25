package Algorithms_4th.Strings;

import Algorithms_4th.Graphs.Queue;

/**
 * 基于单词查找树的符号表
 * Created by ZingBug on 2017/10/18.
 */
public class TrieST<Value> {
    private static int R=256;//基数
    private Node root;

    private static class Node
    {
        private Object val;
        private Node[] next=new Node[R];
    }

    public Value get(String key)
    {
        Node x=get(root,key,0);
        if(x==null)
        {
            return null;
        }
        return (Value)x.val;
    }
    //返回以x作为根据点的子单词查找树中与key相关联的值
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
        char c=key.charAt(d);//找到第d个字符所对应的子单词查找树
        return get(x.next[c],key,d+1);
    }
    public void put(String key,Value val)
    {
        root=put(root,key,val,0);
    }
    private Node put(Node x,String key,Value val,int d)
    {
        if(x==null)
        {
            x=new Node();
        }
        if(d==key.length())
        {
            x.val=val;
            return x;
        }
        char c=key.charAt(d);
        x.next[c]=put(x.next[c],key,val,d+1);
        return x;
    }
    //符号表中的所有键
    public Iterable<String> keys()
    {
        return keysWithPrefix("");
    }
    //所有以pre为前缀的键
    public Iterable<String> keysWithPrefix(String pre)
    {
        Queue<String> q=new Queue<>();
        collect(get(root,pre,0),pre,q);
        return q;
    }
    //通配符匹配
    public Iterable<String> keysThatMatch(String pat)
    {
        Queue<String> q=new Queue<>();
        collect(root,"",pat,q);
        return q;
    }
    //保存从根节点出发的路径上所有的字符
    private void collect(Node x, String pre, Queue<String> q)
    {
        if(x==null)
        {
            return;
        }
        if(x.val!=null)
        {
            q.enqueue(pre);
        }
        for(char c=0;c<R;c++)
        {
            collect(x.next[c],pre+c,q);
        }
    }
    private void collect(Node x,String pre,String pat,Queue<String> q)
    {
        int d=pre.length();
        if(x==null)
        {
            return;
        }
        if(d==pat.length()&&x.val!=null)
        {
            q.enqueue(pre);
        }
        if(d==pat.length())
        {
            return;
        }

        char next=pat.charAt(d);
        for(char c=0;c<R;c++)
        {
            if(next=='.'||next==c)
            {
                collect(x.next[c],pre+c,pat,q);
            }
        }
    }
    //找到给定字符串的最长键前缀
    public String longestPrefixOf(String s)
    {
        int length=search(root,s,0,0);
        return s.substring(0,length);
    }
    //记录查找路径上找到的最长键的长度
    private int search(Node x,String s,int d,int length)
    {
        if(x==null)
        {
            return length;
        }
        if(x.val!=null)
        {
            length=d;
        }
        if(d==s.length())
        {
            return length;
        }
        char c=s.charAt(d);
        return search(x.next[c],s,d+1,length);
    }
    public void delete(String key)
    {
        root=delete(root,key,0);
    }
    private Node delete(Node x,String key,int d)
    {
        if(x==null)
        {
            return null;
        }
        if(d==key.length())
        {
            x.val=null;
        }
        else
        {
            char c=key.charAt(d);
            x.next[c]=delete(x.next[c],key,d+1);
        }
        if(x.val!=null)
        {
            return x;
        }
        for(char c=0;c<R;c++)
        {
            if(x.next[c]!=null)
            {
                return x;
            }
        }
        return null;
    }
    public static void main(String[] args)
    {
        TrieST<Integer> st=new TrieST<>();
        st.put("by",4);
        st.put("sea",2);
        st.put("sells",1);
        st.put("she",0);
        st.put("shells",3);
        st.put("the",5);
        for(String temp:st.keysWithPrefix("she"))
        {
            System.out.print(temp+" ");
        }
        System.out.println();
        st.delete("the");
        for(String temp:st.keys())
        {
            System.out.print(temp+" ");
        }
    }
}
