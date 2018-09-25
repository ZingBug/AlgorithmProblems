package Algorithms_4th.Graphs;

import java.util.Stack;

/**
 * 寻找右向环
 * by ZingBug 2017/9/27
 */
public class DirectedCycle {
    private boolean[] marker;
    private int[] edgeTo;
    private Stack<Integer> cycle;//找到的第一个有向环的所有顶点（如果存在）
    private boolean[] onStack;//递归调用的栈上的所有顶点
    private final Digraph G;

    public DirectedCycle(Digraph G)
    {
        this.G=G;
        marker=new boolean[G.V()];
        edgeTo=new int[G.V()];
        onStack=new boolean[G.V()];
        for(int v=0;v<G.V();v++)
        {
            if(!marker[v])
            {
                dfs(v);
            }
        }
    }
    public void dfs(int v)
    {
        onStack[v]=true;
        marker[v]=true;
        for(int w:G.adj(v))
        {
            if(hasCycle())
                return;
            else if(!marker[w])
            {
                edgeTo[w]=v;
                dfs(w);
            }
            else if(onStack[w])
            {
                //如果往下找，找到了之前经过的顶点的话，就建立起圆
                cycle=new Stack<>();
                for(int x=v;x!=w;x=edgeTo[x])
                {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v]=false;
    }
    public boolean hasCycle()
    {
        return cycle!=null;
    }
    public Iterable<Integer> cycle()
    {
        //有向环中的所有顶点
        return cycle;
    }
}
