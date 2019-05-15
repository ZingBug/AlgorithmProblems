package Others;

/**
 * Created by ZingBug on 2018/12/19.
 */

interface TheGreatestSage{
    public void move();
}
//具体构件角色-猴子
class Monkey implements TheGreatestSage{
    @Override
    public void move() {
        System.out.println("Monkey Move");
    }
}
//抽象装饰角色-七十二变
class Change implements TheGreatestSage{
    private TheGreatestSage sage;
    public Change(TheGreatestSage sage)
    {
        this.sage=sage;
    }
    @Override
    public void move() {
        sage.move();
    }
}
//具体装饰角色-鱼
class Fish extends Change{
    public Fish(TheGreatestSage sage)
    {
        super(sage);
    }
    @Override
    public void move() {
        System.out.println("Fish Move");
    }
}
//具体装饰角色-鸟
class Bird extends Change{
    public Bird(TheGreatestSage sage)
    {
        super(sage);
    }
    @Override
    public void move() {
        System.out.println("Bird Move");
    }
}

public class Decorator {

    public static void main(String[] args)
    {

        TheGreatestSage sage=new Monkey();
        //第一种写法  单层装饰
        TheGreatestSage bird=new Bird(sage);
        bird.move();
        TheGreatestSage fish=new Fish(bird);
        //第二种写法  双层装饰
        //TheGreatestSage fish=new Fish(new Bird(sage));
        fish.move();
    }
}
