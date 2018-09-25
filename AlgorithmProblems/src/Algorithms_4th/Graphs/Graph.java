package Algorithms_4th.Graphs;

/**
 * 邻接表数组表示的无向图
 * by ZingBug 2017/9/23
 */
public class Graph {
    private final int V;//顶点数目
    private int E;//边的数目
    private Bag<Integer>[] adj;//邻接表
    public Graph(int V)
    {
        this.V=V;
        adj=(Bag<Integer>[])new Bag[V];//创建邻接表
        for(int v=0;v<V;v++)
        {
            adj[v]=new Bag<Integer>();//将所有链表初始化为空
        }
    }
    public int V()
    {
        return V;
    }
    public int E()
    {
        return E;
    }
    public void addEdge(int v,int w)
    {
        if(v>V||w>V||v<0||w<0)
            throw new IllegalArgumentException("this argument is incorrect.");
        adj[v].add(w);//将w添加到v的链表中
        adj[w].add(v);//将v添加到w的链表中
        E++;
    }
    public Iterable<Integer> adj(int v)
    {
        return adj[v];
    }
    public String toString()
    {
        String s=V+" vertices, "+E+" edges\n";
        for(int v=0;v<V;v++)
        {
            s+=v+": ";
            for(int w:this.adj[v])
            {
                s+=w+" ";
            }
            s+="\n";
        }
        return s;
    }
    public int degree(int v)
    {
        //计算v的度数
        int degree=0;
        for(int w:adj(v))
        {
            degree++;
        }
        return degree;
    }
    public int maxDegree()
    {
        //计算所有顶点的最大度数
        int max=0;
        for(int v=0;v<V;v++)
        {
            if(degree(v)>max)
            {
                max=degree(v);
            }
        }
        return max;
    }
}
