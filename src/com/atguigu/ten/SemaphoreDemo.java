package com.atguigu.ten;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * �źŵ�
 * @author Administrator
 *
 */
public class SemaphoreDemo {

	public static void main(String[] args) {


		//������λ
		Semaphore s = new Semaphore(3);
		//������
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				try {
					//����,ռ��
					s.acquire();
					System.out.println(Thread.currentThread().getName()+"\t �����˳�λ");
					TimeUnit.SECONDS.sleep(4);
					System.out.println(Thread.currentThread().getName()+"\t-----�뿪�˳�λ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					//�ͷ�
					s.release();
				}
			}, String.valueOf(i)).start();
		}

	}

}
