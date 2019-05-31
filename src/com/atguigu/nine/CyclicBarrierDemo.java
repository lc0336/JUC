package com.atguigu.nine;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier ѭ��դ��
 * @author Administrator
 * 54  ������.���ǿ�ʼ����
 * 
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier c = new CyclicBarrier(54,()->{
			System.out.println("��λ,�˵�����,���ǿ�ʼ����");
		});
		
		for (int i = 1; i <= 54; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+"\t ��");
				try {
					c.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, String.valueOf(i)).start();
		}

	}

}
