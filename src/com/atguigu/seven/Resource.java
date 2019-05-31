package com.atguigu.seven;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 一个线程写入,100个线程读取
 * @author Administrator
 *
 */
public class Resource {
	
	private Object obj;
	
	ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	
	
	public void write(Object obj) {
		this.obj = obj;
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"写入的值为"+obj);
		}finally {
			rwl.writeLock().unlock();
		}
	}
	
	
	
	public void read() {
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t 读取到的值为"+obj);
		}finally {
			rwl.readLock().unlock();
		}
	}

}
