package Algorithms_4th.Graphs;

import java.util.Iterator;

/**
 * 背包
 * 是一种不支持从中删除元素的集合数据类型
 * by ZingBug 2017/9/23
 */
public class Bag<Item> implements Iterable<Item>
{
    private Node first;
    private class Node
    {
        Item item;
        Node next;
    }
    public void add(Item item)
    {
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
    }
    public Iterator<Item> iterator()
    {
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
}
