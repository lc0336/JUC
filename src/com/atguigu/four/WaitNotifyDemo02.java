package com.atguigu.four;

/**
 * 
 * ����:����߳�֮�䰴˳����� ʵ�� A->B->C
    	����߳�֮�䰴˳����� ʵ�� A->B->C
    	�����߳�����,Ҫ������:
    	AA��ӡ5��,BB��ӡ10��,CC��ӡ15��
    	����
    	AA��ӡ5��,BB��ӡ10��,CC��ӡ15��
    	.....ѭ��10��
 * 
 * �ھ�:
 * 	��:�߳� ���� ��Դ��
 * 	��:�ж� ���� ֪ͨ
 * 
 * 
 * 
 * �̼߳�ĵ���  �������߳�ͨ��
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
