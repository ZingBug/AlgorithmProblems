package Algorithms_4th.Graphs;

/**
 * 查找图中的连通分量
 * by ZingBug 2017/9/25
 */
public class CC {
    private boolean[] marked;
    private int[] id;
    private int count;
    private final Graph G;
    public CC(Graph G)
    {
        this.G=G;
        marked=new boolean[G.V()];
        id=new int[G.V()];
        for(int s=0;s<G.V();s++)
        {
            if(!marked[s])
            {
                dfs(s);
                count++;
            }
        }
    }
    private void dfs(int v)
    {
        marked[v]=true;
        id[v]=count;
        for(int w:G.adj(v))
        {
            if(!marked[w])
            {
                dfs(w);
            }
        }
    }
    public boolean connected(int v,int w)
    {
        return id[v]==id[w];
    }
    public int id(int v)
    {
        return id[v];
    }
    public int count()
    {
        return count;
    }
}
