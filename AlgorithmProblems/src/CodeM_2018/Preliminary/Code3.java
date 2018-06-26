package CodeM_2018.Preliminary;

import java.util.*;

/**
 * Created by ZingBug on 2018/6/23.
 */
public class Code3 {
    class c
    {
        int x;
        int y;
        int z;
        String s;
    }
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);

        int n=in.nextInt();
        int m=in.nextInt();
        int k=in.nextInt();

        int[][] lights=new int[n+1][m+1];

        int num=0;
        int max=0;
        int[][] maxLights=new int[n+1][m+1];
        Map<String,Integer[]> map=new TreeMap<>();
        for(int i=0;i<k;i++)
        {
            Integer[] a=new Integer[3];
            a[0]=in.nextInt();
            a[1]=in.nextInt();
            a[2]=in.nextInt();
            String s=in.next();
            //String[] t=s.split(":");
            //String[] tt=t[2].split("\\.");
            //long ttt=Integer.parseInt(t[0])*3600000+Integer.parseInt(t[1])*60000+Integer.parseInt(tt[0])*1000+Integer.parseInt(tt[1]);
            map.put(s,a);
        }
        for(Integer[] a:map.values())
        {
            int x=a[0];
            int y=a[1];
            int z=a[2];

            if(z==0)
            {
                if(lights[x][y]==0)
                {
                    num++;
                    lights[x][y]++;
                    if(num>=max)
                    {
                        max=num;
                        for(int q=1;q<=n;q++)
                        {
                            for(int p=1;p<=m;p++)
                            {
                                maxLights[q][p]=lights[q][p];
                            }
                        }
                    }
                }
                else
                {
                    lights[x][y]++;
                }
            }

            else
            {
                lights[x][y]--;
                if(lights[x][y]==0)
                {
                    num--;
                }
            }
        }
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                if(maxLights[i][j]!=0)
                {
                    System.out.print(1);
                }
                else
                {
                    System.out.print(0);
                }
            }
            System.out.println();
        }


    }
}
