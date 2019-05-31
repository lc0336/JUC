package com.atguigu.four;

/**
 * 
 * 需求:多个线程之间按顺序调用 实现 A->B->C
    	多个线程之间按顺序调用 实现 A->B->C
    	三个线程启动,要求如下:
    	AA打印5次,BB打印10次,CC打印15次
    	接着
    	AA打印5次,BB打印10次,CC打印15次
    	.....循环10轮
 * 
 * 口诀:
 * 	上:线程 操作 资源类
 * 	下:判断 操作 通知
 * 
 * 
 * 
 * 线程间的调度  依赖于线程通信
 * @author Administrator
 *
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
		}, "AA").start();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					r.print10(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "BB").start();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					r.print15(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "CC").start();
		
		
	}

}
