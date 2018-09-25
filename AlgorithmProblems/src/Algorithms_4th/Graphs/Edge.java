package Algorithms_4th.Graphs;

/**
 * 带权重的边
 * Created by ZingBug on 2017/9/29.
 */
public class Edge implements Comparable<Edge> {
    private final int v;//顶点之一
    private final int w;//另一个顶点
    private final double weight;//边的权重
    public Edge(int v,int w,double weight)
    {
        this.v=v;
        this.w=w;
        this.weight=weight;
    }
    public double weight()
    {
        return this.weight;
    }
    public int either()
    {
        return this.v;
    }
    public int other(int vertex)
    {
        if(vertex==this.v)
        {
            return this.w;
        }
        else if(vertex==this.w)
        {
            return this.v;
        }
        else
        {
            throw new RuntimeException("Inconsistent edge");
        }
    }
    @Override
    public int compareTo(Edge that)
    {
        if(this.weight()<that.weight())
        {
            return -1;
        }
        else if(this.weight()>that.weight())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    @Override
    public String toString()
    {
        return String.format("%d-%d %.2f",v,w,weight);
    }


}
