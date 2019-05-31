package com.atguigu.five;

/**
 * 锁的资源是什么?  Phone.java
 */
import java.util.concurrent.TimeUnit;

public class Phone {
	

	

	/**
	 * 短信
	 */
	public static synchronized void sendMSG() {
	
		/*
		 * try { Thread.sleep(4000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */
		System.out.println("发短信...");
	}
	
	/**
	 * 邮件
	 */
	public static synchronized void sendEmail() {
		System.out.println("发邮件...");
	}
	
	
	
	 public void sayHello() { System.out.println("hello world"); }
	 
}
