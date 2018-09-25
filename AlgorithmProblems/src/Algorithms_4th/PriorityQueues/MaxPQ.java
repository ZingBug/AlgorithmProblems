package Algorithms_4th.PriorityQueues;

/**
 * 优先队列
 * 最大优先级
 * by ZingBug 2017/8/31
 */
public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;//基于堆的完全二叉树
    private int N=0;//存储于pq[1..N]中，pq[0]没有使用

    public MaxPQ(int maxN)
    {
        pq=(Key[]) new Comparable[maxN+1];
    }
    public boolean isEmpty()
    {
        return N==0;
    }
    public int size()
    {
        return N;
    }
    public void insert(Key v)
    {
        if(N+1==pq.length)
            resize(2*pq.length);
        pq[++N]=v;
        swim(N);
    }
    public Key delMax()
    {
        Key max=pq[1];
        exch(1,N--);
        pq[N++]=null;//防止对象游离
        sink(1);
        if(N>0&&N==pq.length/4)
        {
            resize(pq.length/2);
        }
        return max;
    }
    private boolean less(int i,int j)
    {
        return pq[i].compareTo(pq[j])<0;
    }
    private void exch(int i,int j)
    {
        Key t=pq[i];
        pq[i]=pq[j];
        pq[j]=t;
    }
    /**
     * 上浮，由下至上的堆有序化
     * @param k
     */
    private void swim(int k)
    {
        while (k>1&&less(k/2,k))
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
            if(j+1<=N&&less(j,j+1))
            {
                //选择二叉树两个枝叶中大的那个元素作为交换
                j++;
            }
            if(less(j,k))
                break;
            exch(j,k);
            k=j;
        }
    }
    /**
     * 调整数组大小
     * @param max
     */
    private void resize(int max)
    {
        Key[] temp=(Key[]) new Comparable[max];
        for(int i=0;i<N;i++)
        {
            temp[i]=pq[i];
        }
        pq=temp;
    }
}
