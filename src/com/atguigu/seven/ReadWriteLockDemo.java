package com.atguigu.seven;

/**
 * ��д��
 * @author Administrator
 * һ���߳�д��,100���̶߳�ȡ
 * 
 * 
 * �ھ�:
 * 	��:�߳� ���� ��Դ��
 * 	��:�ж� ���� ֪ͨ
 */
public class ReadWriteLockDemo {

	public static void main(String[] args) {
		
		Resource r = new Resource();
		
		new Thread(() -> {
			r.write("0225������");
		}, "����").start();
		
		
		for (int i = 1; i <= 100; i++) {
			new Thread(() -> {
				r.read();
			},String.valueOf(i)).start();
		}
		
	}

}
