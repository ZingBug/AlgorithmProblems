package Coding_Interview_Guide.String;

/**
 * 在有序但有空的数组中查找字符串
 *
 * 给定一个字符串数组strs[]，在strs中有些位置为null，但在不为null的位置上，其字符串是按照字典顺序由小到大依次出现的。
 * 再给定一个字符串str，请返回str在strs中出现的最左的位置。
 * Page258
 *
 * Created by ZingBug on 2019/4/1.
 */
public class GetIndex {

    //用二分法查询，注意null
    private int getIndex(String[] strs,String str)
    {
        if(strs==null||strs.length==0||str==null)
        {
            return -1;
        }
        int res=-1;
        int left=0;
        int right=strs.length-1;
        int mid=0;
        int i=0;
        while (left<=right)
        {
            mid=(left+right)/2;
            if(strs[mid]!=null&&strs[mid].equals(str))
            {
                res=mid;
                right=mid-1;
            }
            else if(strs[mid]!=null)
            {
                if(strs[mid].compareTo(str)<0)
                {
                    //中间值比匹配值小
                    left=mid+1;
                }
                else
                {
                    right=mid-1;
                }
            }
            else
            {
                i=mid;
                while (strs[i]==null&&--i>=left)
                    ;
                if(i<left||strs[i].compareTo(str)<0)
                {
                    left=mid+1;
                }
                else
                {
                    res=strs[i].equals(str)?i:res;
                    right=i-1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args)
    {
        GetIndex getIndex=new GetIndex();
        String[] strs={null,"a",null,"a",null,"b",null,"c"};
        String str="b";
        System.out.println(getIndex.getIndex(strs,str));
    }
}
