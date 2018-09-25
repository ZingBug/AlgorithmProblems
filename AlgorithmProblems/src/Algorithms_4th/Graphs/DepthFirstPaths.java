package Algorithms_4th.Graphs;

/**
 * 使用深度优先搜索查找图中的路径
 * by ZingBug 2017/9/34
 */
public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;//从起点到一个顶点的已知路径上的最后一个顶点
    private final int s;//起点
    private int count;
    private Graph G;
    public DepthFirstPaths(Graph G,int s)
    {
        this.G=G;
        this.count=0;
        this.s=s;
        edgeTo=new int[G.V()];
        marked=new boolean[G.V()];
        dfs(s);
    }
    private void dfs(int v)
    {
        marked[v]=true;
        count++;
        for(int w:G.adj(v))
        {
            if(!marked(w))
            {
                edgeTo[w]=v;
                dfs(w);
            }
        }
    }
    public boolean marked(int w)
    {
        return marked[w];
    }
    public boolean hasPathTo(int w)
    {
        return marked[w];
    }
    public int count()
    {
        return count;
    }
    public Iterable<Integer> pathTo(int v)
    {
        if(!hasPathTo(v))
            return null;//如果不能到达，则返回空
        Stack<Integer> path=new Stack<>();
        while (v!=s)
        {
            path.push(v);
            v=edgeTo[v];
        }
        path.push(s);
        return path;
    }
}
