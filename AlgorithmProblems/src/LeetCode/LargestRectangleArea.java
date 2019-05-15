package LeetCode;

import java.util.Stack;

/**
 * 84. Largest Rectangle in Histogram
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * Example:
 * Input: [2,1,5,6,2,3]
 * Output: 10
 * 求最大子矩阵的大小
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 * Created by ZingBug on 2018/11/12.
 */
public class LargestRectangleArea {

    public int largestRectangleAren(int[] heights)
    {
        if(heights==null||heights.length==0)
        {
            return 0;
        }
        Stack<Integer> stack=new Stack<>();
        int len=heights.length;
        int maxArea=0;
        for(int i=0;i<len;i++)
        {
            while (!stack.isEmpty()&&heights[stack.peek()]>=heights[i])
            {
                int j=stack.pop();
                int k=stack.isEmpty()?-1:stack.peek();
                maxArea=Math.max(maxArea,(i-k-1)*heights[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty())
        {
            int j=stack.pop();
            int k=stack.isEmpty()?-1:stack.peek();
            maxArea=Math.max(maxArea,(len-k-1)*heights[j]);
        }
        return maxArea;
    }

    public static void main(String[] args)
    {
        int[] heights={1,2,0};

        LargestRectangleArea l=new LargestRectangleArea();

        System.out.println(l.largestRectangleAren(heights));
    }
}
