package com.example.testproject;

import net.minidev.json.JSONUtil;

/**
 * @Author: 张昕
 * @Date： 2021/7/23
 * @Time: 16:27
 **/

/*
1.使用Lambda时，要记住的就两点：

        Lambda返回的是接口的实例对象
        有没有参数、参数有多少个、需不需要有返回值、返回值的类型是什么---->选择自己合适的函数式接口
   2.如果函数式接口的实现恰好可以通过调用一个方法来实现，那么我们可以使用方法引用
*/
interface Eat {
    void func();
}
//测试lambda表达式
public class MyTest implements Eat {
    public MyTest() {

    }

    public MyTest(String s) {
        System.out.println(s);
    }

    public MyTest(Integer a) {
        System.out.println(a);
    }

    public MyTest(Eat eat){

    }

    @Override
    public void func() {
        System.out.println("aaa");
    }

    public void func1() {
        System.out.println("h");
    }


    public static void main(String[] args) {
        System.out.println("start");
        MyTest apple = new MyTest(() -> System.out.println("1"));
        MyTest apple1 = new MyTest(System.out::println);
        System.out.println("end");
    }

}
