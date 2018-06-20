package Coding_Interviews;

/**
 * 数值的整数次方
 * 题目描述
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 *
 * https://www.nowcoder.com/practice/1a834e5e3e1a4b7ba251417554e07c00?tpId=13&tqId=11165&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 */
public class Power {
    public double solution1(double base,int exponent)
    {
        //第一种方法最简单，直接用java内置的Math类实现
        return Math.pow(base,exponent);
    }
    public double solution2(double base,int exponent)
    {
        //第二种方法，是依次相乘得到，但从速度来说，还是第一种快
        double result=1.0;
        if(exponent==0)
        {
            //0次幂
            return 1;
        }
        else if(exponent>0)
        {
            //幂>0
            for(int i=0;i<exponent;i++)
            {
                result*=base;
            }
        }
        else
        {
            if(base==0)
            {
                throw new RuntimeException("分母不能为0");
            }
            //幂<0
            for(int i=0;i<-exponent;i++)
            {
                result*=base;
            }
            result=1.0/result;
        }
        return result;
    }
    public double Power(double base,int exponent)
    {
        return solution1(base,exponent);
    }
    public static void main(String[] args)
    {
        Power p=new Power();
        System.out.println(p.solution1(3.5,-7));
        System.out.println(p.solution2(3.5,-7));
    }
}
