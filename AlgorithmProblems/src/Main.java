import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        //while (in.hasNextLine())
        {
            int payload_len=in.nextInt();
            String[] str1=in.next().split(",");
            int delete_offset=Integer.valueOf(str1[0]);
            int delete_len=Integer.valueOf(str1[1]);

            List<Integer> lens=new ArrayList<>();
            List<Integer> offsets=new ArrayList<>();
            while (in.hasNextLine())
            {
                String s=in.next();
                if(s.equals("end"))
                {
                    break;
                }
                String[] str=s.split(",");
                int offset=Integer.valueOf(str[0]);
                int len=Integer.valueOf(str[1]);

                offsets.add(offset);
                lens.add(len);
            }

            int num=lens.size();
            int sum=0;
            for(int i=0;i<num;i++)
            {
                int len=lens.get(i);
                sum+=len;
                if(sum>=delete_offset)
                {
                    int offset=offsets.get(i);
                    if(delete_offset+delete_len<=sum)
                    {
                        int a1=delete_offset+len-sum;
                        int a2=sum-(delete_offset+delete_len);
                        if(a1>=a2)
                        {
                            len-=delete_len;
                            lens.set(i,len);
                            offsets.set(i,offset);
                        }
                        else
                        {
                            offset+=delete_len;
                            len-=delete_len;
                            lens.set(i,len);
                            offsets.set(i,offset);
                        }
                    }
                    else
                    {
                        len-=(sum-delete_offset);
                        lens.set(i,len);
                        offsets.set(i,offset);
                        int s=sum-delete_offset;//已经删除的
                        int delete_len_s=delete_len-(sum-delete_offset);//还未删除的
                        i++;
                        len=lens.get(i);
                        offset=offsets.get(i);
                        s+=len;
                        while (s<=delete_len)
                        {
                            delete_len_s-=len;
                            len=0;
                            offset=0;
                            lens.set(i,len);
                            offsets.set(i,offset);
                            i++;
                            if(i>=num)
                            {
                                break;
                            }
                            len=lens.get(i);
                            offset=offsets.get(i);
                            s+=len;
                        }
                        if(i>=num)
                        {
                            break;
                        }
                        offset+=delete_len_s;
                        len-=delete_len_s;
                        lens.set(i,len);
                        offsets.set(i,offset);
                    }
                    break;
                }
            }
            for(int i=0;i<num;i++)
            {
                System.out.println(offsets.get(i)+","+lens.get(i));
            }
        }
    }
}
