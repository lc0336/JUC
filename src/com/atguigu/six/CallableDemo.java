package com.atguigu.six;

/**
 * �̴߳����ĵ����ַ�ʽ
 */
import java.util.concurrent.Callable;

public class CallableDemo implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		
		//�����̵߳�ҵ���߼�
		
		System.out.println(Thread.currentThread().getName()+"callable...");
		return 200;
	}

}
