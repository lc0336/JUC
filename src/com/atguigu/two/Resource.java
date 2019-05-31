package com.atguigu.two;

/**
 * @program: JUC
 * @description:
 * @author: li chao
 * @create: 2019-05-30 18:42
 * @Version 1.0
 */
public class Resource {
    private int num =0;

    /*/**

    *@Description:
    *@Author: li chao
    *@return: 加1
    */

    public synchronized void increment() throws Exception{
        //判断
        if (num!=0) this.wait();
        //操作
        ++num;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        //通知
        this.notifyAll();
    }

    /*
     *@Description:
     *@Author: li chao
     *@return: 减一
     */

    public synchronized void decrement() throws Exception{
        //判断
        if (num!=1) this.wait();
        //操作
        --num;
        System.out.println(Thread.currentThread().getName()+"\t"+num);
        //通知
        this.notifyAll();
    }
}
