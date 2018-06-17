package CodeM_2018.Qualification;

import java.util.Scanner;

public class City {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        int m=in.nextInt();

        int[] x=new int[m];
        int[] y=new int[m];
        int[] c=new int[m];
        int[] ts=new int[m];
        int[] td=new int[m];

        for(int i=0;i<m;i++)
        {
            x[i]=in.nextInt();
            y[i]=in.nextInt();
            c[i]=in.nextInt();
            String[] t1=in.next().split(":");
            String[] t2=in.next().split(":");
            ts[i]=Integer.parseInt(t1[0])*60+Integer.parseInt(t1[1]);
            td[i]=Integer.parseInt(t2[0])*60+Integer.parseInt(t2[1]);
        }

    }
}
