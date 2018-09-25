package Algorithms_4th.Graphs;

/**
 * 加权右向边的数据类型
 * Created by ZingBug on 2017/10/9.
 */
public class DirectedEdge {
    private final int v;//边的起点
    private final int w;//边的终点
    private final double weight;//边的权重
    public DirectedEdge(int v,int w,double weight)
    {
        this.v=v;
        this.w=w;
        this.weight=weight;
    }
    public double weight()
    {
        return this.weight;
    }
    public int from()
    {
        return v;
    }
    public int to()
    {
        return w;
    }
    public String toString()
    {
        return String.format("%d->%d %.2f",v,w,weight);
    }
}
