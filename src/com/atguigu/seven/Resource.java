package com.atguigu.seven;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * һ���߳�д��,100���̶߳�ȡ
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
			System.out.println(Thread.currentThread().getName()+"д���ֵΪ"+obj);
		}finally {
			rwl.writeLock().unlock();
		}
	}
	
	
	
	public void read() {
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t ��ȡ����ֵΪ"+obj);
		}finally {
			rwl.readLock().unlock();
		}
	}

}
