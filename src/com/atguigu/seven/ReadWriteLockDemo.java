package com.atguigu.seven;

/**
 * 读写锁
 * @author Administrator
 * 一个线程写入,100个线程读取
 * 
 * 
 * 口诀:
 * 	上:线程 操作 资源类
 * 	下:判断 操作 通知
 */
public class ReadWriteLockDemo {

	public static void main(String[] args) {
		
		Resource r = new Resource();
		
		new Thread(() -> {
			r.write("0225大数据");
		}, "来福").start();
		
		
		for (int i = 1; i <= 100; i++) {
			new Thread(() -> {
				r.read();
			},String.valueOf(i)).start();
		}
		
	}

}
