package Algorithms_4th.Graphs;

/**
 * 深度优先搜索查找顶点连通
 * by ZingBug 2017/9/24
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    private Graph G;
    public DepthFirstSearch(Graph G,int s)
    {
        this.G=G;
        this.count=0;
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
                dfs(w);
            }
        }
    }
    public boolean marked(int w)
    {
        return marked[w];
    }
    public int count()
    {
        return count;
    }

}
