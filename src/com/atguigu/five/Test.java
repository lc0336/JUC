package com.atguigu.five;

/**
 * 
 * @author Administrator
 *	1 标准访问，先打印短信还是邮件
 *		短信
 *		锁的资源是 Phone.java
	2 停4秒在短信方法内，先打印短信还是邮件
		短信
	3 普通的hello方法，是先打短信还是hello
		短信
		普通方法由于不涉及Phone资源的争抢
	4 现在有两部手机，先打印短信还是邮件
		短信
		不同的资源类
	5 两个静态同步方法，1部手机，先打印短信还是邮件
		短信
		资源类   Phone.class
	6 两个静态同步方法，2部手机，先打印短信还是邮件
		短信
		资源类   Phone.class
	7 1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
		短信
		静态的资源类是:.class
		普通同步资源类:.java
		
	8 1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
		短信
		
		
	总结:明确静态 非静态  普通方法  他们所操作的资源类是什么即可...
 */
public class Test {

	public static void main(String[] args) {
		
		Phone p = new Phone();
		//Phone p2 = new Phone();
		
		new Thread(() -> {
			p.sendMSG();
		}, "张三").start();
		
		new Thread(() -> {
			//p.sendEmail();
			p.sayHello();
		}, "李四").start();
	}

}
