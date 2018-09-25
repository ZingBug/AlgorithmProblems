package Algorithms_4th.Base;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * 读取比特
 * Created by ZingBug on 2017/10/26.
 */
public class BinaryStdIn {
    private static BufferedInputStream in=new BufferedInputStream(System.in);
    private static final int EOF=-1;//文件结尾

    private static int buffer;//一个字符缓冲区
    private static int n;//字节缓冲区中的比特位数

    //静态初始化
    static {
        fillBuffer();
    }

    //无法构造对象
    private BinaryStdIn()
    {}

    private static void fillBuffer()
    {
        try
        {
            buffer=in.read();
            n=8;
        }
        catch (IOException e)
        {
            System.out.println("EOF");
            buffer=EOF;
            n=-1;
        }
    }

    //关闭输入流并释放所有资源
    public static void close()
    {
        try
        {
            in.close();
        }
        catch (IOException ioe)
        {
            throw new IllegalStateException("Could not close BinaryStdIn",ioe);
        }
    }

    //判断是否输入为空
    public static boolean isEmpty()
    {
        return buffer==EOF;
    }

    public static boolean readBoolean()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("Reading from empty input stream");
        }
        n--;
        boolean bit=((buffer>>n)&1)==1;
        if(n==0) fillBuffer();//再次读取
        return bit;
    }
    //读取8位字节
    public static char readChar()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("Reading from empty input stream");
        }

        if(n==8)
        {
            //如果buffer内正好是一个字节
            int x=buffer;
            fillBuffer();
            return (char)(x&0xff);
        }
        //如果不是
        int x=buffer;
        x<<=(8-n);
        int oldN=n;
        fillBuffer();
        if(isEmpty())
        {
            throw new NoSuchElementException("Reading from empty input stream");
        }
        n=oldN;
        x|=(buffer>>>n);
        return (char)(x&0xff);
    }
    //读取指定位数的字节
    public static char readChar(int r)
    {
        if (r < 1 || r > 16) throw new IllegalArgumentException("Illegal value of r = " + r);

        if(r==8)
        {
            return readChar();
        }

        char x=0;
        for(int i=0;i<r;i++)
        {
            x<<=1;
            boolean bit=readBoolean();
            if(bit) x|=1;
        }
        return x;
    }
    //读取字符串形式
    public static String readString() {
        if (isEmpty()) throw new NoSuchElementException("Reading from empty input stream");

        StringBuilder sb = new StringBuilder();
        while (!isEmpty()) {
            char c = readChar();
            sb.append(c);
        }
        return sb.toString();
    }
    //读取短整数
    public static short readShort() {
        short x = 0;
        for (int i = 0; i < 2; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
    }
    //读取整数
    public static int readInt() {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
    }
    //读取指定位数整数
    public static int readInt(int r) {
        if (r < 1 || r > 32) throw new IllegalArgumentException("Illegal value of r = " + r);

        // optimize r = 32 case
        if (r == 32) return readInt();

        int x = 0;
        for (int i = 0; i < r; i++) {
            x <<= 1;
            boolean bit = readBoolean();
            if (bit) x |= 1;
        }
        return x;
    }
    //读取长整数
    public static long readLong() {
        long x = 0;
        for (int i = 0; i < 8; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
    }

    public static double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public static float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public static byte readByte() {
        char c = readChar();
        return (byte) (c & 0xff);
    }

}
