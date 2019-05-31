package com.atguigu.eight;

import java.util.concurrent.CountDownLatch;

/**
 * 减少计数器
 * @author Administrator
 * 
 * 54人      
 *	运用 CountDownLatch 判断走后一个循环内容走完，才去执行其他线程
 */
public class CountDownDemo {

	public static void main(String[] args) throws Exception {
		
		
		CountDownLatch c = new CountDownLatch(54);
		
		for (int i = 1; i <= 54; i++) {
			
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+"\t 回家了");
				c.countDown();
			}, String.valueOf(i)).start();
			
		}
		c.await();
		System.out.println(Thread.currentThread().getName());

	}

}
