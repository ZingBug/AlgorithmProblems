import java.util.*;

/**
 * Created by ZingBug on 2018/9/8.
 */
public class test1 {

    private static class T{
        double s;
        double f;
    }
    public static void solution2()
    {
        Scanner in=new Scanner(System.in);
        ArrayList<Double> s=new ArrayList<>();
        ArrayList<Double> f=new ArrayList<>();
        int start=8;
        int end=21;
        while (in.hasNext())
        {
            String line=in.next();
            String[] array=line.split(",");
            double temp1=Double.valueOf(array[0]);
            double temp2=Double.valueOf(array[1]);
            if(temp1==0&&temp2==0)
            {
                break;
            }
            if(temp1<start||temp2>end)
            {
                continue;
            }
            s.add(temp1);
            f.add(temp2);
        }
        int n=s.size();
        T[] ts=new T[n];

        for(int i=0;i<n;i++)
        {
            ts[i]=new T();
            ts[i].s=s.get(i);
            ts[i].f=f.get(i);
        }

        Arrays.sort(ts, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return (int)(o1.s-o2.s);
            }
        });

        boolean[] arrange=new boolean[n];
        arrange[0]=true;
        for(int i=1,j=0;i<n;i++)
        {
            //如果前一个的结束时间比后一个的开始时间小。则安排下去
            if(ts[j].f<ts[i].s)//符合要求
            {
                arrange[i]=true;
                j=i;//如果成立，则j就是下一个作为参考的时间
            }
            else
            {
                arrange[i]=false;
            }
        }

        for(int i=0;i<n;i++)
        {
            if(arrange[i])
            {
                System.out.println(ts[i].s+","+ts[i].f);
            }
        }

    }
    public static void main(String[] args)
    {
        solution2();
    }
}
