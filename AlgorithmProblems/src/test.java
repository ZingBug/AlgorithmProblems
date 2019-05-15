/**
 * Created by ZingBug on 2018/12/25.
 */
public class Test {
    public static void main(String[] args)
    {
        A a=new B();
    }
}
class A{
    static {
        System.out.println("A基类静态域");
    }
    {
        System.out.println("A基类对象成员构造函数");
    }
    Object o=new Object(){
        @Override
        public String toString() {
            return "A基类对象成员匿名类构造函数";
        }
    };

    public A(){
        System.out.println("A基类本身构造函数");
    }
}

class B extends A{
    static {
        System.out.println("B基类静态域");
    }
    String a="2";
    {
        System.out.println("B基类对象成员构造函数");
    }

    public B(){
        System.out.println("B基类本身构造函数");
    }
}
