package com.atguigu.one;

/**
 * @program: JUC
 * @description: 买票线程
 * @author: li chao
 * @create: 2019-05-30 18:12
 * @Version 1.0
 */
public class TicketDemo {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        // 创建线程对象 Thread

        new Thread(() -> {
            for (int i = 1; i <= 40; i++) ticket.sale();
        }, "窗口A").start();
        new Thread(() -> {
            for (int i = 1; i <= 40; i++) ticket.sale();
        }, "窗口B").start();
        new Thread(() -> {
            for (int i = 1; i <= 40; i++) ticket.sale();
        }, "窗口C").start();


        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }

            }
        }, "窗口A").start();



      /*  new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "窗口B").start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 1; i <= 40; i++) {
                    ticket.sale();
                }
            }
        }, "窗口C").start();
    }*/
    }
}