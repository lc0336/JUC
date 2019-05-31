package com.atguigu.six;

import java.util.concurrent.FutureTask;

public class CallableTest {

	public static void main(String[] args) throws Exception {
		
		CallableDemo callable = new CallableDemo();
		FutureTask<Integer> futureTask = new FutureTask<>(callable);
		
		new Thread(futureTask,"Íú²Æ...").start();
		new Thread(futureTask,"À´¸£...").start();
		new Thread(futureTask,"³£Íþ...").start();
		
		System.err.println(futureTask.get());
		
	}

}
