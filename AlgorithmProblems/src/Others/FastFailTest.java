package Others;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Fast-Fail错误机制测试
 * 当多个线程对同一个集合的内容进行操作时，就可能会产生fail-fast事件。
 * Created by ZingBug on 2018/12/18.
 */
public class FastFailTest {

    //private static List<String> list=new ArrayList<>();
    private static List<String> list=new CopyOnWriteArrayList<>();//解决了这个问题
    public static void main(String[] args)
    {
        new ThreadOne().start();
        new ThreadTwo().start();
    }

    private static void printAll()
    {
        String value=null;
        Iterator iter=list.iterator();
        while (iter.hasNext())
        {
            value=(String) iter.next();
            System.out.print(value+",");
        }
        System.out.println();
    }

    private static class ThreadOne extends Thread
    {
        @Override
        public void run() {
            int i=0;
            while (i<6)
            {
                list.add(String.valueOf(i++));
                printAll();
            }
        }
    }

    private static class ThreadTwo extends Thread
    {
        @Override
        public void run()
        {
            int i=0;
            while (i<6)
            {
                list.add(String.valueOf(i++));
                printAll();
            }
        }
    }
}
