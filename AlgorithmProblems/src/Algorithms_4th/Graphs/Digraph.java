package Algorithms_4th.Graphs;
/**
 * 有向图
 * 邻接表数组表示
 * by ZingBug 2017/9/27
 */
public class Digraph {
    private final int V;//顶点数目
    private int E;//边数目
    private Bag<Integer>[] adj;//邻接表
    public Digraph(int V)
    {
        this.V=V;
        this.E=0;
        adj=(Bag<Integer>[])new Bag[V];//创建邻接表
        for(int v=0;v<V;v++)
        {
            adj[v]=new Bag<>();
        }
    }
    public int V()
    {
        return this.V;
    }
    public int E()
    {
        return this.E;
    }
    public void addEdge(int v,int w)
    {
        adj[v].add(w);
        E++;
    }
    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }
    public Digraph reverse()
    {
        //有向图取反
        Digraph R=new Digraph(this.V);
        for(int v=0;v<this.V;v++)
        {
            for(int w:adj(v))
            {
                R.addEdge(w,v);
            }
        }
        return R;
    }

}
