package Coding_Interviews;

import java.util.ArrayList;
import java.util.Collections;


/**
 * 字符串的排列
 * 题目描述
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * 输入描述:
 * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 *
 * https://www.nowcoder.com/practice/fe6b651b66ae47d7acce78ffdd9a96c7?tpId=13&tqId=11180&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 *
 * 解题思路
 * 把一个字符串看成两部分组成：第一部分为第一个字符，第二部分为后面的所有字符。
 * 求整个字符串的排列，可以看出两步：
 * 1、首先求所有可能出现在第一个位置的字符，即把第一个字符和后面的所有字符交换；
 * 2、然后固定第一个字符，求后面所有字符的排序。此时仍把后面的字符看成两部分，第一个字符和后面的字符，然后重复上述步骤。（递归）
 *
 * 但是要注意，这样做改变了字符数组，必须进行完一次dfs过程后，将数组复原，便于确定i位置其他元素时，不会受到影响。
 *
 * Created by ZingBug on 2018/6/25.
 */
public class StringPermutation {
    public ArrayList<String> Permutation(String str)
    {
        char[] chars=str.toCharArray();
        ArrayList<String> arrayList=new ArrayList<>();
        prem(chars,0,arrayList);
        Collections.sort(arrayList);//注意排序，一定要注意

        return arrayList;
    }
    private void prem(char[] list,int index,ArrayList<String> arrayList)
    {
        if(index==list.length)
        {
            arrayList.add(String.valueOf(list));
        }
        else
        {
            for(int i=index;i<list.length;i++)
            {
                if(i!=index&&list[i]==list[index])//去掉重复的排列
                {
                    continue;
                }
                //交换
                swap(list,i,index);
                //确定好index位置，对list[index+1~length-1]范围内的字符数组完成全排列
                prem(list,index+1,arrayList);
                //恢复原数组
                swap(list,index,i);

            }
        }
    }
    private void swap(char[] list,int i,int j)
    {
        char c=list[i];
        list[i]=list[j];
        list[j]=c;
    }

    public static void main(String[] args)
    {
        StringPermutation s=new StringPermutation();
        ArrayList<String> list=s.Permutation("abc");
        for(String val:list)
        {
            System.out.println(val);
        }
    }

}
