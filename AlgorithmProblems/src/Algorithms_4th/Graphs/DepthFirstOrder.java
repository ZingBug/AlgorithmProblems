package Algorithms_4th.Graphs;

import javax.swing.*;

/**
 * 有向图中基于深度优先搜索的顶点排序
 * by ZingBug 2017/9/27
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;//所有顶点的前序排列
    private Queue<Integer> post;//所有顶点得后序排列
    private Stack<Integer> reversePost;//所有顶点得逆后序排列

    private final Digraph G;

    public DepthFirstOrder(Digraph G)
    {
        this.G=G;
        marked=new boolean[G.V()];
        pre=new Queue<>();
        post=new Queue<>();
        reversePost=new Stack<>();
        for(int v=0;v<G.V();v++)
        {
            if(!marked[v])
            {
                dfs(v);
            }
        }
    }
    private void dfs(int v)
    {
        pre.enqueue(v);
        marked[v]=true;
        for(int w:G.adj(v))
        {
            if(!marked[w])
            {
                dfs(w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);//逆后序的这种行为会导致在reversePost这个栈中，任意一个子节点存放皆后于父节点，也就是说只有当所有子节点都入栈了，父节点才入栈
    }
    public Iterable<Integer> pre()
    {
        return pre;
    }
    public Iterable<Integer> post()
    {
        return post;
    }
    public Iterable<Integer> reversePost()
    {
        return reversePost;
    }
}
