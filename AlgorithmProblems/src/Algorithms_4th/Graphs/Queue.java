package Algorithms_4th.Graphs;

import java.util.Iterator;

/**
 * 队列
 * 链表实现
 * 先进先出(FIFO)
 * @param <Item>
 */
public class Queue<Item> implements Iterable<Item>
{
    private Node first;
    private Node last;
    private int N;
    private class Node
    {
        Item item;
        Node next;
    }
    public Queue()
    {
        last=first;
    }
    public int size()
    {
        return N;
    }
    public boolean isEmpty()
    {
        return size()==0;
    }
    public void enqueue(Item item)
    {
        //向表尾添加元素
        Node oldlast=last;
        last=new Node();
        last.item=item;
        last.next=null;
        if(isEmpty())
        {
            first=last;
        }
        else
        {
            oldlast.next=last;
        }
        N++;
    }
    public Item dequeue()
    {
        //从表头删除元素
        if(isEmpty())
        {
            last=null;
            throw new IllegalArgumentException("the queue is null");
        }
        Item item=first.item;
        first=first.next;
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>
    {
        private Node current=first;
        public boolean hasNext()
        {
            return current!=null;
        }
        public Item next()
        {
            Item item=current.item;
            current=current.next;
            return item;
        }
    }
    public static void main(String[] args)
    {
        Queue<Integer> queue=new Queue<>();
        for(int i=0;i<10;i++)
        {
            queue.enqueue(i);
        }
        Iterator<Integer> iterator=queue.iterator();
        while (iterator.hasNext())
        {
            System.out.print(iterator.next()+" ");
        }
        System.out.println();
    }
}
