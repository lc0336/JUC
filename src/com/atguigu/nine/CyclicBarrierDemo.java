package com.atguigu.nine;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 循环栅栏
 * @author Administrator
 * 54  人齐了.我们开始开会
 * 
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier c = new CyclicBarrier(54,()->{
			System.out.println("各位,人到齐了,我们开始开会");
		});
		
		for (int i = 1; i <= 54; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+"\t 到");
				try {
					c.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}

	}

}
