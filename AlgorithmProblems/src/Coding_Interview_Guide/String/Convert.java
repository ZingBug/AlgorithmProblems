package Coding_Interview_Guide.String;

/**
 * 将整数字符串转成整数值
 * 给定一个字符串str，如果str符合日常书写的整数形式，并且属于32位整数的范围，返回str所代表的整数值，否则返回0。
 * page248
 *
 * Created by ZingBug on 2019/3/19.
 */
public class Convert {

    //判断是否符合日常书写的整数形式
    private boolean isValid(char[] chars)
    {
        if(chars[0]!='-'&&(chars[0]<'0'||chars[0]>'9'))
        {
            return false;
        }
        if(chars[0]=='-'&&(chars.length==1||chars[1]=='0'))
        {
            return false;
        }
        if(chars[0]=='0'&&chars.length>1)
        {
            return false;
        }
        for(int i=1;i<chars.length;i++)
        {
            if(chars[i]<'0'||chars[i]>'9')
            {
                return false;
            }
        }
        return true;
    }

    private int convert(String str)
    {
        if(str==null||str.equals(""))
        {
            return 0;
        }
        char[] chars=str.toCharArray();
        if(!isValid(chars))
        {
            return 0;
        }
        boolean posi=chars[0]=='-'?false:true;
        int minq=Integer.MIN_VALUE/10;//用于判断溢出
        int minr=Integer.MIN_VALUE%10;
        int res=0;
        int cur=0;
        for(int i=posi?0:1;i<chars.length;i++)
        {
            cur='0'-chars[i];//用负数的形式记录绝对值，因为负数比正数拥有更大的绝对值范围
            if((res<minq)||(res==minq&&cur<minr))
            {
                //此时溢出
                return 0;
            }
            res=res*10+cur;
        }
        if(posi&&res==Integer.MIN_VALUE)
        {
            return 0;//不能转
        }
        return posi?-res:res;
    }

    public static void main(String[] args)
    {
        Convert convert=new Convert();
        String str1="123";
        String str2="012";
        String str3="A12";
        String str4="2147483647";
        String str5="2147483648";
        System.out.println(convert.convert(str1));
        System.out.println(convert.convert(str2));
        System.out.println(convert.convert(str3));
        System.out.println(convert.convert(str4));
        System.out.println(convert.convert(str5));
    }
}
