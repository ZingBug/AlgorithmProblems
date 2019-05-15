import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    public static void main(String[] args)
    {
        int x=5;
        int y=50;
        for (;x<=15;x++)
        {
            for(y=50;y<75;y++)
            {
                if(6*x+7*y==500)
                {
                    System.out.println(x);
                    System.out.println(y);
                    break;
                }
            }
        }
        Queue<Integer> integers;
        HashMap<String,String> map;
        Hashtable<String,String> table;
        List<Integer> list;
        AbstractCollection<Integer> abstractCollection;
        AbstractSet<Integer> abstractSet;
        ListIterator<Integer> listIterator;
        ArrayList<Integer> arrayList;
        Collections.emptySet();
        LinkedList<Integer> linkedList;

        FileInputStream fileInputStream;
        FilterInputStream filterInputStream;
        BufferedInputStream bufferedInputStream;
        SimpleDateFormat simpleDateFormat;
        ConcurrentHashMap<Integer,Integer> concurrentHashMap;
        Thread thread;
        Main main=new Main();
        Class c=main.getClass();

        Math.round(2.3f);

        ThreadPoolExecutor threadPoolExecutor;
    }
}
