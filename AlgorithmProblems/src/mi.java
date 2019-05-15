import java.util.Scanner;

/**
 * Created by ZingBug on 2018/12/25.
 */
public class mi {
    private static void s()
    {
        int[] a = {2,0,1,0,0,3,4};
        int k = 0;

        //规则：每次条k ， 然后与当前元素相加，继续向后跳  ，求次数
        int count = 0;
        int temp = 0;
//      System.out.println("数组的长度为："+a.length);
        int i=0;
        for(;;){
            //到达终点的条件是：次数大于数组长度
            if(temp>a.length){
                System.out.println(true);
                break;
            }
            i = a[i]+k;
            count++;    //循环几次  就代表跳了几次
//          System.out.println("a[i]的值为："+a[i]);
            temp+=i;    //记录所跳的总长度
        }
        System.out.println(false);
    }
    private static boolean f(int[] a,int i,int N)
    {
        if(i==N-1)
        {
            return true;
        }
        if(i>(N-1))
        {
            return false;
        }
        if(a[i]==0)
        {
            return false;
        }
        boolean r=false;
        for(int j=1;j<=a[i];j++)
        {
            r=r|f(a,i+j,N);
        }
        return r;
    }
    private static String solution(String line) {
        int[] a=new int[10];
        String[] strs=line.split(" ");
        int i=0;
        for(;i<strs.length;i++)
        {
            a[i]=Integer.valueOf(strs[i]);
        }
        return String.valueOf(f(a,0,i));
    }

    private static boolean ss(int[] nums,int n)
    {
        int f;
        if(n==1)
            return true;
        if(nums[0]==0)
            return false;
        for(int i=1;i<n;i++){
            if(nums[i]==0){
                f=0;
                for(int j=i-1;j>=0;j--){
                    if(nums[j]+j>i||(i==n-1&&nums[j]+j==i)){
                        f=1;
                        break;
                    }
                }
                if(f==0)
                    return false;
            }
        }
        return true;
    }

    private static String sss(String line)
    {
        int[] a=new int[10];
        String[] strs=line.split(" ");
        int i=0;
        for(;i<strs.length;i++)
        {
            a[i]=Integer.valueOf(strs[i]);
        }
        return String.valueOf(ss(a,i));
    }

    public static void main(String[] args)
    {
        String line="2 0 1 0 0 3 4";
        //System.out.println(solution(line));

        System.out.println(sss(line));

    }
}
