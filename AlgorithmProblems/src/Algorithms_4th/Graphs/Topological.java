package Algorithms_4th.Graphs;

/**
 * 拓扑排序
 * 当且仅当一幅有向图是无环图时它才能进行拓扑排序
 * 使用深度优先搜索来对有向无环图进行拓扑排序
 * by ZingBug 2017/9/27
 */
public class Topological {
    private Iterable<Integer> order;//顶点的拓扑程序
    public Topological(Digraph G)
    {
        DirectedCycle cyclefinder=new DirectedCycle(G);
        if(!cyclefinder.hasCycle())
        {
            //拓扑排序建立在没有右向环的基础上
            DepthFirstOrder dfs=new DepthFirstOrder(G);
            order=dfs.reversePost();
        }
    }
    public Iterable<Integer> order()
    {
        return order;
    }
    public boolean isDAG()
    {
        return order!=null;
    }
}
