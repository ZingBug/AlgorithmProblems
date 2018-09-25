package Algorithms_4th.PriorityQueues;

import java.util.NoSuchElementException;

/**
 * 优先队列
 * 最小优先级
 * by ZingBug 2017/10/9
 */
public class MinPQ<Key extends Comparable<Key>>{
    private Key[] pq;
    private int n;

    public MinPQ(int initCapacity)
    {
        pq=(Key[])new Comparable[initCapacity+1];
        n=0;
    }
    public MinPQ()
    {
        this(1);
    }
    public boolean isEmpty()
    {
        return n==0;
    }
    public int size()
    {
        return n;
    }
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    private void exch(int i,int j)
    {
        Key swap=pq[i];
        pq[i]=pq[j];
        pq[j]=swap;
    }
    private boolean greater(int i,int j)
    {
        return pq[i].compareTo(pq[j])>0;
    }
    private void swim(int k)
    {
        //上浮
        while (k>1&&greater(k/2,k))
        {
            //将小的元素往上挪
            exch(k,k/2);
            k=k/2;
        }
    }
    private void sink(int k)
    {
        //下沉
        while (2*k<=n)
        {
            //将大的元素往下挪
            int j=2*k;
            if(j<n&&greater(j,j+1))
            {
                j++;
            }
            if(!greater(k,j))
            {
                break;
            }
            exch(k,j);
            k=j;
        }
    }
    private void resize(int capacity)
    {
        //重新设计数组大小
        assert capacity>n;//选用断言
        Key[] temp=(Key[]) new Comparable[capacity];
        for(int i=1;i<=n;i++)
        {
            temp[i]=pq[i];
        }
        pq=temp;
    }
    public void insert(Key x)
    {
        //插入新的元素
        if(n==pq.length-1)
        {
            resize(2*pq.length);
        }
        pq[++n]=x;
        swim(n);
    }
    public Key delMin()
    {
        //删除最小元素
        if(isEmpty())
        {
            throw new NoSuchElementException("Priority queue underflow");
        }
        Key min=pq[1];
        exch(1,n--);
        sink(1);
        pq[n+1]=null;
        if((n>0)&&(n==(pq.length-1)/4))
        {
            resize(pq.length/2);
        }
        return min;
    }
    public static void main(String[] agrs)
    {
        MinPQ<Integer> minPQ=new MinPQ<>();
        for(int i=1;i<10;i++)
        {
            minPQ.insert(i);
        }
        minPQ.delMin();
        System.out.println(minPQ.delMin());
    }
}
