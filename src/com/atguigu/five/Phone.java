package com.atguigu.five;

/**
 * ������Դ��ʲô?  Phone.java
 */
import java.util.concurrent.TimeUnit;

public class Phone {
	

	

	/**
	 * ����
	 */
	public static synchronized void sendMSG() {
	
		/*
		 * try { Thread.sleep(4000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */
		System.out.println("������...");
	}
	
	/**
	 * �ʼ�
	 */
	public static synchronized void sendEmail() {
		System.out.println("���ʼ�...");
	}
	
	
	
	 public void sayHello() { System.out.println("hello world"); }
	 
}
