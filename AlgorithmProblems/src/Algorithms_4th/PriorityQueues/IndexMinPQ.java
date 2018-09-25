package Algorithms_4th.PriorityQueues;

import java.util.NoSuchElementException;

/**
 * 索引优先队列
 * 给每个元素提供一个索引。
 * 在已经有了总量为N的多个元素后，还同时使用了多个（平行数组）来存储这个元素的信息
 * 当堆发生变化时，用下沉（元素减小时）或上浮（元素变大时）操作来恢复堆的有序性
 * by ZingBug 2017/8/31
 * @param <T>
 */
public class IndexMinPQ<T extends Comparable<T>> {
    private int N;//PQ中的元素数量
    private int maxN;//PQ中最大数组元素
    private int[] pq;//索引二叉堆，由1开始，保存的是索引信息
    private int[] qp;//逆序：qp[pq[i]]=pq[qp[i]]=i
    private T[] keys;//有优先级之分的元素

    public IndexMinPQ(int maxN)
    {
        N=0;
        this.maxN=maxN;
        pq=new int[maxN+1];
        qp=new int[maxN+1];
        keys=(T[]) new Comparable[maxN+1];
        for(int i=0;i<=maxN;i++)
        {
            qp[i]=-1;
        }
    }
    /**
     * 插入一个元素t，使其与索引k相关联
     * @param k
     * @param t
     */
    public void insert(int k,T t)
    {
        if (k < 0 || k >= maxN) throw new IllegalArgumentException();
        if (contains(k)) throw new IllegalArgumentException("index is already in the priority queue");
        N++;
        qp[k]=N;
        pq[N]=k;
        keys[k]=t;
        swim(N);//上浮,恢复堆的有序性
    }
    /**
     * 将索引为k的元素设置为t
     * @param k
     * @param t
     */
    public void change(int k,T t)
    {
        if(k<0||k>=maxN)
            throw new IllegalArgumentException();
        if(!contains(k))
            throw new NoSuchElementException("index is not in the priority queue");
        keys[k]=t;
        swim(qp[k]);
        sink(qp[k]);
    }
    /**
     * 是否存在索引为k的元素
     * @param k
     * @return
     */
    public boolean contains(int k)
    {
        if(k<0||k>=maxN)
            throw new IllegalArgumentException();
        return qp[k]!=-1;
    }
    /**
     * 删去索引k及其相关联的元素
     * @param k
     */
    public void delete(int k)
    {
        if(k<0||k>=maxN)
            throw new IllegalArgumentException();
        if(!contains(k))
            throw new NoSuchElementException("index is not in the priority queue");
        int index=qp[k];
        exch(index,N--);
        sink(index);
        swim(index);
        keys[index]=null;
        qp[index]=-1;
        qp[N+1]=-1;
    }
    /**
     * 返回最小元素
     * @return
     */
    public T min()
    {
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[1]];
    }
    /**
     * 返回最小元素的索引
     * @return
     */
    public int minIndex()
    {
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }
    /**
     * 删除最小元素并返回它的索引
     * @return
     */
    public int delMin()
    {
        if (N == 0) throw new NoSuchElementException("Priority queue underflow");
        int min=pq[1];
        exch(1,N--);
        sink(1);
        keys[min]=null;
        qp[N+1]=-1;
        qp[min]=-1;
        return min;
    }
    /**
     * 优先队列是否为空
     * @return
     */
    public boolean isEmpty()
    {
        return N==0;
    }
    /**
     * 优先队列中的元素数量
     * @return
     */
    public int size()
    {
        return N;
    }
    /**
     * 上浮，由下至上的堆有序化
     * @param k
     */
    private void swim(int k)
    {
        while (k>1&&greater(k/2,k))
        {
            exch(k,k/2);
            k=k/2;
        }
    }
    /**
     * 下沉，由上至下的堆有序化
     * @param k
     */
    private void sink(int k)
    {
        while (2*k<=N)
        {
            int j=2*k;
            if(j+1<=N&&greater(j,j+1))
            {
                //选择二叉树两个枝叶中小的那个元素作为交换,小的元素往上走
                j++;
            }
            if(greater(j,k))
                break;
            exch(j,k);
            k=j;
        }
    }
    private boolean less(int i,int j)
    {
        return keys[pq[i]].compareTo(keys[pq[j]])<0;
    }
    private boolean greater(int i,int j)
    {
        return keys[pq[i]].compareTo(keys[pq[j]])>0;
    }
    private void exch(int i,int j)
    {
        int swap=pq[i];
        pq[i]=pq[j];
        pq[j]=swap;
        qp[pq[i]]=i;
        qp[pq[j]]=j;
    }

}
