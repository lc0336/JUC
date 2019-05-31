package com.atguigu.three;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JUC
 * @description:
 * @author: li chao
 * @create: 2019-05-30 19:07
 * @Version 1.0
 */
public class Resource {

    private int num = 1;

    private Lock lock = new ReentrantLock();

    private Condition c_5 = lock.newCondition();
    private Condition c_10 = lock.newCondition();
    private Condition c_15 = lock.newCondition();

    public void print5(int loopNum) throws Exception {
        lock.lock();
        try {
        //判断
        while (num !=1) c_5.await();

            //操作
            for (int i = 1; i <= 5 ; i++) System.out.println(Thread.currentThread().getName()+"\t"+i);
            //通知
            num=2;
            c_10.signal();
        }finally {
            lock.unlock();
        }
    }

    public void print10(int loopNum) throws Exception {

        lock.lock(); // block until condition holds
        try {
            // 判断
            while (num != 2) {
                c_10.await();
            }
            // 操作
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t 第"+loopNum+"轮,打印的数字是"+i);
            }
            // 通知
            num = 3;
            c_15.signal();
        } finally {
            lock.unlock();
        }
    }
    public void print15(int loopNum) throws Exception {

        lock.lock(); // block until condition holds
        try {
            // 判断
            while (num != 3) {
                c_15.await();
            }
            // 操作
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t 第"+loopNum+"轮,打印的数字是"+i);
            }
            // 通知
            num=1;
            c_5.signal();
        } finally {
            lock.unlock();
        }
    }

}