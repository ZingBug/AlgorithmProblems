/**
 * Created by ZingBug on 2018/12/25.
 */
public class mi1 {
    private static int a[]=new int[19];
    private static int sg[]=new int[10001];

    private static void init()
    {
        a[0]=1;
        a[1]=2;
        int p = 0;
        int q = 1;
        for(int i = 2;i <= 18;i++)
        {
            a[i] = a[p] + a[q];
            p++;
            q++;
        }
    }

    private static int getsg(int v)
    {
        if(sg[v] != -1)
            return sg[v];
        int vis[]=new int[10001];
        for(int i = 0;i < 18;i++)
        {
            int tmp = v - a[i];
            if(tmp < 0)
                break;
            vis[getsg(tmp)] = 1;
        }
        for(int i = 0;;i++)
        {
            if(vis[i]==0)
            {
                sg[v] = i;
                return i;
            }
        }
    }

    private static String solution(String line)
    {
        int[] a=new int[10];
        String[] strs=line.split(" ");
        int i=0;
        for(;i<strs.length;i++)
        {
            a[i]=Integer.valueOf(strs[i]);
        }
        int m=a[0];
        int n=a[1];
        for(i=0;i<10001;i++)
        {
            sg[i]=-1;
        }
        init();
        int ans=0;
        ans^=getsg(m);
        ans^=getsg(n);
        if(ans!=0)
        {
            return "Xiaoai Win";
        }
        else
        {
            return "Xiaobing Win";
        }
    }

    public static void main(String[] args)
    {
        String line="9999 10000";
        System.out.println(solution(line));

    }
}
