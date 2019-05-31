package com.atguigu.two;

/**
 * @program: JUC
 * 线程间的通信 需求: 两个线程 默认0 一个线程负责加1 一个线程负责减1 循环10轮
 *
 * @author Administrator
 *
 *         线程编码口诀 1,线程 操作 资源类 2,判断 操作 通知
 */
public class WaitNotifyDemo {
    public static void main(String[] args)  {
        Resource02 r = new Resource02();


        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    r.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "加1操作-一号车间").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    r.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "减1操作-一号车间").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    r.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "加1操作-二号车间").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    r.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "减1操作-二号车间").start();

    }
}