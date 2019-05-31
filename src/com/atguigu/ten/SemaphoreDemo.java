package com.atguigu.ten;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 * @author Administrator
 *
 */
public class SemaphoreDemo {

	public static void main(String[] args) {


		//三个车位
		Semaphore s = new Semaphore(3);
		//六辆车
		for (int i = 1; i <= 30; i++) {
			new Thread(() -> {
				try {
					//征服,占用
					s.acquire();
					System.out.println(Thread.currentThread().getName()+"\t 抢到了车位");
					TimeUnit.SECONDS.sleep(4);
					System.out.println(Thread.currentThread().getName()+"\t-----离开了车位");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally {
					//释放
					s.release();
				}
			}, String.valueOf(i)).start();
		}

	}

}
