package Algorithms_4th.Graphs;

import edu.princeton.cs.algs4.In;
import Algorithms_4th.Graphs.Graph;


/**
 * 使用广度优先搜索查找图中的路径
 * 队列用来保存所有已经被标记过但其邻接表还未被检查过的顶点
 * by ZingBug 2017/9/24
 */
public class BreadthFirstPaths {
    private boolean[] markerd;//到达该顶点的最短路径已知
    private int[] edgeTo;//达到该顶点的已知路径上的最后一个路径
    private final int s;
    private Graph G;
    public BreadthFirstPaths(Graph G,int s)
    {
        this.G=G;
        this.s=s;
        markerd=new boolean[G.V()];
        edgeTo=new int[G.V()];
        bfs(s);
    }

    private void bfs(int s)
    {
        Queue<Integer> queue=new Queue<>();
        markerd[s]=true;
        queue.enqueue(s);
        while (!queue.isEmpty())
        {
            int v=queue.dequeue();
            for(int w:G.adj(v))
            {
                if(!markerd[w])
                {
                    edgeTo[w]=v;
                    markerd[w]=true;
                    queue.enqueue(w);
                }
            }
        }
    }
    public boolean hasPathTo(int v)
    {
        return markerd[v];
    }
    public Iterable<Integer> pathTo(int v)
    {
        if(!hasPathTo(v))
            return null;//如果不能到达，则返回空
        Stack<Integer> stack=new Stack<>();
        while (v!=s)
        {
            stack.push(v);
            v=edgeTo[v];
        }
        stack.push(s);
        return stack;
    }

}
