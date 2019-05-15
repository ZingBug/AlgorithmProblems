package LeetCode;

import Algorithms_4th.Base.In;

import java.util.Stack;

/**
 * Created by ZingBug on 2018/11/14.
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix)
    {
        if(matrix==null||matrix.length==0)
        {
            return 0;
        }
        //先切片
        int height=matrix.length;
        int weight=matrix[0].length;
        int[] value=new int[weight];
        int maxArea=0;
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<weight;j++)
            {
                value[j]=(matrix[i][j]==48)?0:(value[j]+matrix[i][j]-48);
            }
            maxArea=Math.max(maxArea,largestRectangleArea(value));
        }
        return maxArea;
    }

    private int largestRectangleArea(int[] value)
    {
        Stack<Integer> stack=new Stack<>();
        int maxArea=0;
        int len=value.length;
        for(int i=0;i<len;i++)
        {
            while (!stack.isEmpty()&&value[stack.peek()]>=value[i])
            {
                int j=stack.pop();
                int k=stack.isEmpty()?-1:stack.peek();
                maxArea=Math.max(maxArea,(i-k-1)*value[j]);
            }
            stack.push(i);
        }

        while (!stack.isEmpty())
        {
            int j=stack.pop();
            int k=stack.isEmpty()?-1:stack.peek();
            maxArea=Math.max(maxArea,(len-k-1)*value[j]);
        }

        return maxArea;
    }

    public static void main(String[] args)
    {
        char[][] matrix1={
                {'1','2','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        char[][] matrix2={{'1'}};
        MaximalRectangle m=new MaximalRectangle();
        System.out.println(m.maximalRectangle(matrix1));

    }
}
