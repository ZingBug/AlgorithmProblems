package Algorithms_4th.Graphs;

import java.util.Iterator;

/**
 * 下压堆栈
 * 链表实现
 * 后进先出(LIFO)
 * by ZingBug 2017/9/24
 * @param <Item>
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int N;
    private class Node
    {
        Item item;
        Node next;
    }
    public int size()
    {
        return N;
    }
    public boolean isEmpty()
    {
        return size()==0;
    }
    public void push(Item item)
    {
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        N++;
    }
    public Item pop()
    {
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
        @Override
        public boolean hasNext()
        {
            return current!=null;
        }
        @Override
        public Item next() {
            Item item=current.item;
            current=current.next;
            return item;
        }
    }


}
