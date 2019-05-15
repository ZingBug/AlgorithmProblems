package Others;

/**
 * 幂
 * Created by ZingBug on 2018/12/24.
 */
public class Power {

    private int integerPower(int x,int p)//求整数的N次方
    {
        int res=1;
        int tmp=x;
        for(;p!=0;p>>=1)
        {
            if((p&1)!=0)
            {
                res*=tmp;
            }
            tmp=tmp*tmp;
        }
        return res;
    }

    public static void main(String[] args)
    {
        Power p=new Power();
        System.out.println(p.integerPower(3,3));
    }
}
