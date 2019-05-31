package com.atguigu.six;

/**
 * 线程创建的第三种方式
 */
import java.util.concurrent.Callable;

public class CallableDemo implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		
		//困难线程的业务逻辑
		
		System.out.println(Thread.currentThread().getName()+"callable...");
		return 200;
	}

}
