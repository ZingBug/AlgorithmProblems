package MIOJ_2018;
import java.util.*;
/**
 * 大数相减
 * 描述
 * 两个长度超出常规整形变量上限的大数相减，请避免使用各语言内置大数处理库，如 Java.math.BigInteger 等。
 *
 * 输入
 * 有 N 行测试数据，每一行有两个代表整数的字符串 a 和 b，长度超过百位。规定 a>=b，a, b > 0。
 * 测试结果可以用 linux 小工具 bc进行测试是否正确。
 *
 * 输出
 * 返回表示结果整数的字符串。
 *
 * 输入样例
 * 1231231237812739878951331231231237812739878951331231231237812739878951331231231237812739878951331231231237812739878951331231231237812739870 - 89513312312312378127398789513312312312378127398789513312312312378127398789513
 * 1231231237812739878951331231231237812739878951331231231237812739878951331230000000000000000000000001 - 331231231237812739878951331231231
 *
 * 输出样例
 * 1231231237812739878951331231231237812739878951331231231237812650365639018918853110413950365639018918853110413950365639018918853110413950357
 * 1231231237812739878951331231231237812739878951331231231237812739878620099998762187260121048668768770
 *
 * https://code.mi.com/problem/list/view?id=3
 * Created by ZingBug on 2018/9/3.
 */
public class BigNumber {
    //模拟小学时学的减法运算
    private static String solution(String line)
    {
        String[] array=line.split(" ");
        char[] num1=array[0].toCharArray();
        char[] num2=array[2].toCharArray();
        int len1=num1.length;
        int len2=num2.length;

        boolean flag=true;//true表示结果为正数，false表示结果为负数

        if(len1<len2)//被减数位数小于减数
        {
            flag=false;//结果为负数
            //交换两个数
            char[] temp=num1;
            num1=num2;
            num2=temp;
            int len=len1;
            len1=len2;
            len2=len;
        }
        else if(len1==len2)
        {
            //判断哪个数大
            for(int i=0;i<len1;i++)
            {
                if(num1[i]==num2[i])
                {
                    continue;
                }
                if(num1[i]>num2[i])
                {
                    flag=true;//正数
                    break;
                }
                else
                {
                    flag=false;//负数
                    //交换两个数
                    char[] temp=num1;
                    num1=num2;
                    num2=temp;
                    break;
                }
            }
        }
        int len=Math.max(len1,len2);//获取位数较大的位数
        //将num1字符数组的数字转换为整型数且逆向保存在整型数组sum中，即低位在前，高位在后
        int[] n1=new int[len];
        int[] n2=new int[len];
        int[] sum=new int[len];
        for(int i=len1-1,j=0;i>=0;i--,j++)
        {
            n1[j]=num1[i]-'0';
        }
        for(int i=len2-1,j=0;i>=0;i--,j++)
        {
            n2[j]=num2[i]-'0';
        }
        //将两个大数相减
        for(int i=0;i<len;i++)
        {
            sum[i]+=n1[i]-n2[i];//两个数从低位开始减
            if(sum[i]<0)// 判断是否有借位
            {
                sum[i]+=10;
                sum[i+1]--;
            }
        }
        StringBuilder sb=new StringBuilder();
        int end=0;
        for(end=len-1;end>=0&&sum[end]==0;end--);
        for(int i=end;i>=0;i--)
        {
            sb.append(sum[i]);
        }

        if(!flag)
        {
            sb.insert(0,'-');//插入负号
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        BigNumber b=new BigNumber();
        String line1="1231231237812739878951331231231237812739878951331231231237812739878951331231231237812739878951331231231237812739878951331231231237812739870 - 89513312312312378127398789513312312312378127398789513312312312378127398789513";
        String line2="1231231237812739878951331231231237812739878951331231231237812739878951331230000000000000000000000001 - 331231231237812739878951331231231";
        System.out.println(b.solution(line1));
        System.out.println(b.solution(line2));

    }
}
