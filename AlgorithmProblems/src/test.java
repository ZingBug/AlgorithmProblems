import java.awt.Canvas;

public class test {
    public static void main(String[] args)
    {
        int[] nums={6,5,3,1,8,7,3,4};
        int len=nums.length;
        for(int i=0;i<len;i++)
        {
            while (nums[i]!=i+1)
            {
                if(nums[i]==nums[nums[i]-1])
                {
                    break;
                }
                int temp=nums[i];
                nums[i]=nums[temp-1];
                nums[temp-1]=temp;
            }
        }
        for(int num:nums)
        {
            System.out.print(num+" ");
        }
        System.out.println();

        String str="iloveyou";
        char[] c=str.toCharArray();
        int i=1;
        while (i<str.length()-1)
        {
            c[i]=c[i+1];
            i++;
        }

        System.out.println(String.valueOf(c));
    }
}
