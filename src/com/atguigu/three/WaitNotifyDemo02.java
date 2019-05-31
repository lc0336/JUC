package com.atguigu.three;

/**
 * @program: JUC
 * @description:
 * @author: li chao
 * @create: 2019-05-30 19:07
 * @Version 1.0
 */
public class WaitNotifyDemo02 {

    public static void main(String[] args) {
        Resource r = new Resource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    r.print5(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();


        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    r.print10(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();


        new Thread(() ->  {
            for (int i = 1; i <= 10; i++) {
                try {
                    r.print15(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "cc").  start();
    }
}