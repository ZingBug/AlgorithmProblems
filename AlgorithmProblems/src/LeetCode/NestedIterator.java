package LeetCode;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 341. Flatten Nested List Iterator
 *
 * Given a nested list of integers, implement an iterator to flatten it.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 *
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 *
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,4,6].
 *
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 *
 * 问题在于怎么去实现NestedInteger接口，注意，初始化是接口，实现时是类。类似于List<Double> list=new ArrayList<>();
 *
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 * Created by ZingBug on 2018/11/18.
 */
public class NestedIterator implements Iterator<Integer> {

    private interface NestedInteger{
        public boolean isInteger();
        public Integer getInteger();
        public List<NestedInteger> getList();
    }

    private static class NestedIntegerImpl implements NestedInteger
    {
        private Integer value;
        private List<NestedInteger> list;
        private boolean isInteger;
        private int size;

        public NestedIntegerImpl(boolean isInteger)
        {
            this.isInteger=isInteger;
            list=new ArrayList<>();
            size=0;
        }

        public void setValue(Integer v)
        {
            if(this.isInteger)
            {
                this.value=v;
                size=1;
            }
            else
            {
                throw new IllegalArgumentException("异常");
            }

        }

        public void addValue(NestedIntegerImpl nest)
        {
            if(nest!=null&&!this.isInteger)
            {
                list.add(nest);
                size++;
            }
        }

        public void addInteger(Integer v)
        {
            if(!this.isInteger)
            {
                NestedIntegerImpl n=new NestedIntegerImpl(true);
                n.setValue(v);
                list.add(n);
                size++;
            }
            else
            {
                throw new IllegalArgumentException("异常");
            }
        }

        public int size()
        {
            return size;
        }

        public boolean isEmpty()
        {
            return size==0;
        }

        @Override
        public boolean isInteger() {
            return this.isInteger;
        }

        @Override
        public Integer getInteger() {
            return value;
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }
    }

    private Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList)
    {
        stack=new Stack<>();
        for(int i=nestedList.size()-1;i>=0;i--)
        {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty())
        {
            NestedInteger t=stack.peek();
            if(t.isInteger())
            {
                return true;
            }
            else
            {
                stack.pop();
                for(int i=t.getList().size()-1;i>=0;i--)
                {
                    stack.push(t.getList().get(i));
                }
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    public static void main(String[] args)
    {
        //[1,[2,3],[4,[5]]]
        NestedIntegerImpl nest1=new NestedIntegerImpl(true);
        nest1.setValue(1);
        NestedIntegerImpl nest2=new NestedIntegerImpl(false);
        nest2.addInteger(2);
        nest2.addInteger(3);
        NestedIntegerImpl nest3=new NestedIntegerImpl(false);
        nest3.addInteger(4);
        NestedIntegerImpl nest4=new NestedIntegerImpl(true);
        nest4.setValue(5);
        nest3.addValue(nest4);

        List<NestedInteger> list=new ArrayList<>();
        list.add(nest1);
        list.add(nest2);
        list.add(nest3);

        NestedIterator n=new NestedIterator(list);
        while (n.hasNext())
        {
            System.out.print(n.next()+" ");
        }

    }

}
