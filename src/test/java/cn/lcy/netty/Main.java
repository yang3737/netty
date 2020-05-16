package cn.lcy.netty;

public class Main {
         
    public static void main(String[] args) {
        String str1 = "hello world";
        String str2 = new String("hello world");
        String str3 = "hello world";
        String str4 = new String("hello world");
         
        System.out.println(str1==str2);
        System.out.println(str1==str3);
        System.out.println(str2==str4);
        System.out.println(str1==str3);
    }
}