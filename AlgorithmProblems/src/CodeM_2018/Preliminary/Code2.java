package CodeM_2018.Preliminary;

import java.util.*;

/**
 * Created by ZingBug on 2018/6/23.
 */
public class Code2 {
    static class c implements Comparable
    {
        int x;
        int y;
        int z;
        String s;

        @Override
        public int compareTo(Object o) {
            c c1=(c)o;
            return s.compareTo(c1.s);
        }
    }
    static class myc implements Comparator<c>
    {
        @Override
        public int compare(c o1, c o2) {
            return o2.s.compareTo(o1.s);
        }
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
        c[] cc=new c[k];
        for(int i=0;i<k;i++)
        {
            cc[i]=new c();
            cc[i].x=in.nextInt();
            cc[i].y=in.nextInt();
            cc[i].z=in.nextInt();
            cc[i].s=in.next();
        }

        Arrays.sort(cc);
        for(int i=0;i<k;i++)
        {
            int x=cc[i].x;
            int y=cc[i].y;
            int z=cc[i].z;

            if(z==0)
            {
                if(lights[x][y]==0)
                {
                    ++num;
                    lights[x][y]=1;

                    if(num>=max)
                    {
                        max=num;
                        for(int e=1;e<=n;e++)
                        {
                            for(int f=1;f<=m;f++)
                            {
                                maxLights[e][f]=lights[e][f];
                            }
                        }

                    }
                }
            }
            else
            {
                if(lights[x][y]==1)
                {
                    --num;
                    --lights[x][y];
                }
            }
        }
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=m;j++)
            {
                System.out.print(maxLights[i][j]);
            }
            System.out.println();


        }


    }
}
