package com.atguigu.six;

import java.util.concurrent.FutureTask;

public class CallableTest {

	public static void main(String[] args) throws Exception {
		
		CallableDemo callable = new CallableDemo();
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		
		new Thread(futureTask,"����...").start();
		new Thread(futureTask,"����...").start();
		new Thread(futureTask,"����...").start();
		
		System.err.println(futureTask.get());
		
	}

}
