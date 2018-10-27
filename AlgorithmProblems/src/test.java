import java.awt.Canvas;
import java.util.LinkedList;

public class test {
    public static void main(String[] args)
    {
        LinkedList<String> list=new LinkedList<>();
        list.addFirst("1");
        list.addFirst("2");
        if(list.contains("3"))
        {
            list.remove("3");
        }

        System.out.println();
    }
}
