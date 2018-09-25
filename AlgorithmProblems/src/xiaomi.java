/**
 * Created by ZingBug on 2018/9/10.
 */
import java.util.*;
public class xiaomi {

    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        while (in.hasNextInt())
        {
            int year=2018;
            int month=in.nextInt();
            int day=in.nextInt();

            int a=7;
            if((month==1)||(month==2))
            {
                month+=12;
                year--;
            }
            a=(day+2*month+3*(month+1)/5+year+year/4-year/100+year/400)%7;
            System.out.println(a+1);
        }
    }
}
