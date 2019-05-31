package com.atguigu.one;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JUC
 * @description:
 * @author: li chao
 * @create: 2019-05-30 18:25
 * @Version 1.0
 */
public class Ticket {
    private int num = 30;
    //可重入锁
    Lock l = new ReentrantLock();

    public  void sale() {

        l.lock();
        try {
            if(num>0) {
                System.out.println(Thread.currentThread().getName()+"\t 当前正在卖的第"+(num--)+"张票,还剩下"+num+"张票");
            }
        } finally {
        l.unlock();
    }
    }
}