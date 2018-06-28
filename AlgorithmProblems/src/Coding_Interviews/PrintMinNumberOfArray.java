package Coding_Interviews;

/**
 * 把数组排成最小的数
 * 题目描述
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 *
 * https://www.nowcoder.com/practice/8fecd3f8ba334add803bf2a06af1b993?tpId=13&tqId=11185&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 这个题目类似于字典序的问题，而在这里，是比较两个数字组合之后的大小。
 * 即对于数字a和b，它们之间的组合有ab和ba两种，要求我们打印出最小的一个组合数字出来； 如果ab小于ba，则a是在b的前面; 如果ab大于ba，则b在a的前面;
 * 按照这种思路，对于一个输入数组中的所有数字，我们按照冒泡排序的思想，每一轮进行两两比较，找出一个放在前面的数字出来。
 * 考虑到整数拼接后可能会超过Integer范围，所以用String来做字典序判断。
 *
 * Created by ZingBug on 2018/6/27.
 */
public class PrintMinNumberOfArray {
    public String PrintMinNumber(int[] numbers)
    {

        if(numbers==null)
        {
            return null;
        }
        StringBuilder sb=new StringBuilder();

        int len=numbers.length;
        for(int i=0;i<len;i++)
        {
            for(int j=i;j<len;j++)
            {
                String pre=numbers[i]+""+numbers[j];
                String last=numbers[j]+""+numbers[i];
                if(pre.compareTo(last)>0)
                {
                    //替换
                    int temp=numbers[i];
                    numbers[i]=numbers[j];
                    numbers[j]=temp;
                }
            }
            sb.append(numbers[i]);
        }
        return sb.toString();
    }
    public static void main(String[] args)
    {
        PrintMinNumberOfArray p=new PrintMinNumberOfArray();
        int[] array={3,32,321};
        System.out.println(p.PrintMinNumber(array));
    }
}
