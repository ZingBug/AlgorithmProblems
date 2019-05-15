package Coding_Interview_Guide.RecurAndDP;

/**
 * 给定数组arr，arr中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个整数aim代表要找的钱数，求组成aim的最小货币数。
 * Page191
 * Created by ZingBug on 2019/1/4.
 */
public class MinCoins {
    private int minCoins1(int[] arr,int aim)
    {
        if(arr==null||arr.length<1||aim<0)
        {
            return 0;
        }
        int len=arr.length;
        int[][] dp=new int[len][aim+1];
        int max=Integer.MAX_VALUE;
        for(int i=0;i<len;i++)
        {
            dp[i][0]=0;
        }
        for(int i=0;i<=aim;i++)
        {
            dp[0][i]=i%arr[0]==0?i/arr[0]:max;
        }
        int left=0;
        for(int i=1;i<len;i++)
        {
            for(int j=1;j<=aim;j++)
            {
                left=max;
                if(j-arr[i]>=0&&dp[i][j-arr[i]]!=max)
                {
                    left=dp[i][j-arr[i]]+1;
                }
                dp[i][j]=Math.min(left,dp[i-1][j]);
            }
        }
        return dp[len-1][aim]!=max?dp[len-1][aim]:-1;
    }

    //压缩空间
    private int minCoins2(int[] arr,int aim)
    {
        if(arr==null||arr.length<1||aim<0)
        {
            return 0;
        }
        int len=arr.length;
        int[] dp=new int[aim+1];
        int max=Integer.MAX_VALUE;
        for(int i=0;i<=aim;i++)
        {
            dp[i]=i%arr[0]==0?i/arr[0]:max;
        }
        int left=0;
        for(int i=1;i<len;i++)
        {
            for(int j=1;j<=aim;j++)
            {
                left=max;
                if(j-arr[i]>=0&&dp[j-arr[i]]!=max)
                {
                    left=dp[j-arr[i]]+1;
                }
                dp[j]=Math.min(left,dp[j]);
            }
        }
        return dp[aim]!=max?dp[aim]:-1;
    }


    public static void main(String[] args)
    {
        MinCoins m=new MinCoins();
        int[] arr={5,2,3};
        int aim=20;
        System.out.println(m.minCoins2(arr,aim));
    }
}
