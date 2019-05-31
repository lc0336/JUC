package com.atguigu.two;

import com.sun.org.glassfish.gmbal.Description;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JUC
 * @description:
 * @author: li chao
 * @create: 2019-05-30 18:52
 * @Version 1.0
 */
public class Resource02 {
    private int num = 0;

    private Lock lock =new ReentrantLock();

    final Condition in_c = lock.newCondition();
    final Condition de_c = lock.newCondition();

    /**
    *@Description:
    *@Author: li chao
    *@date:
    *@Param:
    *@return: 加1
    */

    public void increment() throws Exception{

        lock.lock();
        try{
            //判断
            while (num!=0)
                in_c.await();
            //操作
            ++num;
            System.out.println(Thread.currentThread().getName()+"\t"+num);

            //通知
            de_c.signal();
        }finally {

            lock.unlock();
        }
    }

    public void decrement() throws Exception {

        lock.lock();
        try {
            //判断
            while (num != 1)
                de_c.await();
            //操作
            --num;
            System.out.println(Thread.currentThread().getName() + "\t" + num);

            //通知
            in_c.signal();
        } finally {
            lock.unlock();
        }

    }

}