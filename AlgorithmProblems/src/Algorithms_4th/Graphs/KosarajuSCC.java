package Algorithms_4th.Graphs;

/**
 * 计算有向图强连通分量的Kosaraju算法
 * 使用深度优先搜索查找给定有向图G的反向图G1，根据G1计算出所有顶点的逆后续排列，然后根据该逆后续排列去深度优先处理有向图G，其构造函数中的每一次递归调用所标记的顶点都在同一个联通分量中。
 * 证明过程见教材P381命题H
 * 没搞懂这个证明
 * by ZingBug 2017/9/28
 */
public class KosarajuSCC {
    private boolean[] marked;//已访问过的顶点
    private int[] id;//强连通分量的标识符
    private int count;//强连通分量的数量
    private final Digraph G;

    public KosarajuSCC(Digraph G)
    {
        this.G=G;
        marked=new boolean[G.V()];
        id=new int[G.V()];
        count=0;
        DepthFirstOrder order=new DepthFirstOrder(G.reverse());
        for(int s:order.reversePost())
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
    public boolean stronglyConnected(int v,int w)
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
