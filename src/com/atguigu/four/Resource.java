package com.atguigu.four;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ����:�����߳�����,Ҫ������: AA��ӡ5��,BB��ӡ10��,CC��ӡ15�� ���� AA��ӡ5��,BB��ӡ10��,CC��ӡ15�� .....ѭ��10��
 * 
 * @author Administrator
 *
 */
public class Resource {

	private int num = 1; // 1:AA 2:BB 3:CC

	private Lock lock = new ReentrantLock();

	private Condition c_5 = lock.newCondition();
	private Condition c_10 = lock.newCondition();
	private Condition c_15 = lock.newCondition();

	public void print5(int loopNum) throws Exception {

		lock.lock(); // block until condition holds
		try {
			// �ж�
			while (num != 1) {
				c_5.await();
			}
			// ����
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t ��"+loopNum+"��,��ӡ��������"+i);
			}
			// ֪ͨ
			num = 2;
			c_10.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void print10(int loopNum) throws Exception {

		lock.lock(); // block until condition holds
		try {
			// �ж�
			while (num != 2) {
				c_10.await();
			}
			// ����
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName()+"\t ��"+loopNum+"��,��ӡ��������"+i);
			}
			// ֪ͨ
			num = 3;
			c_15.signal();
		} finally {
			lock.unlock();
		}
	}
	public void print15(int loopNum) throws Exception {

		lock.lock(); // block until condition holds
		try {
			// �ж�
			while (num != 3) {
				c_15.await();
			}
			// ����
			for (int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName()+"\t ��"+loopNum+"��,��ӡ��������"+i);
			}
			// ֪ͨ
			num=1;
			c_5.signal();
		} finally {
			lock.unlock();
		}
	}

}
