package Coding_Interviews;

/**
 * 不用加减乘除做加法
 * 题目描述
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 *
 * https://www.nowcoder.com/practice/59ac416b4b944300b617d4f7f111b215?tpId=13&tqId=11201&rp=3&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 主要是三部分，第一步各位相加但不记进位。第二步是记下进位。第三步是相加。
 * Created by ZingBug on 2018/7/5.
 */
public class Add {
    public int Add(int num1,int num2)
    {
        //用位运算方法
        int sum=num1;
        while (num2!=0)
        {
            sum=num1^num2;
            num2=(num1&num2)<<1;
            num1=sum;
        }
        return sum;
    }

    public static void main(String[] args)
    {
        Add a=new Add();
        System.out.println(a.Add(10,24));
    }
}
