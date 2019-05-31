package com.atguigu.eight;

import java.util.concurrent.CountDownLatch;

/**
 * ���ټ�����
 * @author Administrator
 * 
 * 54��      
 *	���� CountDownLatch �ж��ߺ�һ��ѭ���������꣬��ȥִ�������߳�
 */
public class CountDownDemo {

	public static void main(String[] args) throws Exception {
		
		
		CountDownLatch c = new CountDownLatch(54);
		
		for (int i = 1; i <= 54; i++) {
			
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+"\t �ؼ���");
				c.countDown();
			}, String.valueOf(i)).start();
			
		}
		c.await();
		System.out.println(Thread.currentThread().getName());

	}

}
