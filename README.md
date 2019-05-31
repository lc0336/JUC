juc多线程
java.util.concurrent(并发)
概念复习
 进程:正在进行中的程序
 线程:进程当中的一个执行单元
 关系: 进程包含线程
 面试问题:
	日常使用进程/线程的案例或者case
线程的各种状态:
	Thread.state --枚举
	(需手写记忆)
	进入源码:NEW --新建
			RUNNABLE --运行
			BLOCKED  --阻塞
			WAITING  --等待         --不见不散
			TIMED_WAITING--等待     --过时不候
			TERMINATED-- 终结
案例1--买票
	需求:三个窗口  卖出  30张票
	提纲:-->上篇
		1,线程		操作(资源类中的实例方法)		资源类
		2,高内聚	低耦合
	始终记得:多个线程操作同一个资源类
	代码编写
		资源类(实例变量+实例方法)
		    class Ticket{
    			private int number = 30;
    			// 引入java.util.concurrent.locks
    			private Lock lock = new ReentrantLock();
    			public void sale(){
    				lock.lock();
    				try{
    					if(number>0){
    						System.err.println(Thread.currentThread().getName()+"卖出第:\t"+(number--)+"\t还剩下"+number+"张票");
    					}
    				}catch(Exception e){
    					e.printStackTrace();
    				}finally{
    					lock.unlock();
    				}
    			}
    		}
    		public class SaleTicket{
				Ticket t = new Ticket();
    			public static void main(String[] args){
    				//Thread构造方法回顾
							new Thread(new Runnable() {
							@Override
							public void run() {
								for (int i = 1; i < 40; i++) {
									t.sale();
								}
							}
						},"A窗口");
    			}
    		}
Lock 接口
	实现类:重入锁 Package java.util.concurrent.locks
	
	实现类:
		ReentrantLock(可重入锁)
	如何使用(查看文档):
		 class X {
		   private final ReentrantLock lock = new ReentrantLock();
		   // ...
		
		   public void m() {
		   lock.lock();  // block until condition holds
			 try {
			   // ... method body
			 } finally {
			   lock.unlock()
			 }
			   }
			 }
	扩展:自定义 Java Code Template
		windows-->proferences-->java-->editors-->templates
		mycurr -->打印当前线程的名称
		mynewcurr01 -->匿名内部类创建线程对象
		mynewcurr02 -->lambda表达式创建线程
lamda表达式:
	就是用来解决匿名内部类代码冗余问题
传统的接口实现类实现方式
	第一种方式:需要重新编写一个类用来是新建该接口,并重写接口中的方法
	第二种方式:使用匿名内部类
接口中方法为空参空返回
	1,接口当中有且只有一个方法时,在编写实现类的时候肯定不会找错,所以方法名可以省略 也就是该接口为函数式接口
    	**拷贝中括号(方法的参数列表),写死右箭头,落地大括号(为方法体)**
    	interface Foo{
    		public void say();
    	}
    	使用lamda表达式实例化
    	new Foo()->{}
	2,@FunctionalInterface 接口中有且仅有只有一个public abstract方法,使用该注解来增强定义
接口中方法为有参又返回
	interface Foo{
		public int add(int x,int y);
	}
	使用lamda表达式实现实现类
	new Foo  = (int x,int y)->{
		return x+y;
	};
	也可以省略形参的参数类型,因为方法只有一个
	new Foo  = (x,y)->{
		return x+y;
	};
default方法实现
	@FunctionalInterface
	interface Foo{
		public int(int x,int y);
		default int (int a,int b){
			return a+b;
		}
	}
	default 方法在接口中可以定义多个,那么如何调用呢?
静态方法实现
	@FunctionalInterface
	interface Foo{
		public int(int x,int y);
		default int (int a,int b){
			return a+b;
		}
	}
	//静态方法实现也可以有多个
	public static int sub(int x,int y){
		return x-y
		}
--------------------------------------------------------------------------------
线程间通信
	概要:
		1,生产者+消费者
		2,通知等待唤醒机制
		3,线程编码  下 ----->判断  干活   通知
	案例:现有两个线程,可以操作初始值为零的一个变量.实现一个线程对变量加1,一个线程对该变量减1,交替10轮(老版本+新版本)
		分析: 线程   操作    资源类  ----上
			 判断   干活     通知   -----下
			  第一步:要清楚,一共有两个线程对象,一个线程+1,一个线程-1 10轮
			  第二步:资源列中,变量,初始值为0 ; private int number = 0;
					两个方法,一个负责+  一个负责-	 
					另外,加1之后紧接着执行减1操作     --->判断 干活  通知
					-----加--public synchronized increment()-------------
						判断 if(number!=0){this.wait()}
						干活 ++number; 为了效果,可输出一句话,打印当前线程名和当前数字
						通知 this.notifyAll();
					-----减--public synchronized decrement()-------------
						判断 if(number==0){this.wait()}
						干活 --number;
						通知 this.notifyAll()
			  第三步:编写测试类
					1,main()方法
					2,实例化资源类
					3,使用lambda表达式创建两个线程对象,分别调用increment和decrement
		案例扩展:由两个线程变为四个线程 两个生产者两个消费者
			问题分析:1,每个线程休眠一定时间  --涛声依旧
			考察核心:禁止出现线程的虚假唤醒(spurious wakeup)
					举例:飞机重新安检   只要重新登记,就需要重新接受安检,只要wait了,就必须notify之后再判断
			结果:在java多线程判断的时候,是不能用if判断
			验证:查看API文档,wait和notify是object类的方法
				查看Object类中wait方法的官方说明
				As in the one argument(情况) version, interrupts and spurious wakeups are possible, and this method should always be used in a loop
			解决:使用while循环来判断
修改为java8新版
	1,引入condition接口 -->查看API文档-->从Lock的API中可以看到condition的影子-->跳转到condition的文档
		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		condition.await();
		condition.signalAll();
----------------------------------------------------------------------------------------
	    	    synchronized                                    Lock



	obj.wait                   obj.notify       condition.await      condition.signalAll 
------------------------------------------------------------------------------------------
案例3 Lock+多condition(一锁多把备用钥匙)
	考察核心:永远保证线程按照指定顺序执行   使用synchronized很复杂,且它为重锁,一旦释放,必导致多个线程对资源执行权的争抢
    	多个线程之间按顺序调用 实现 A->B->C
    	三个线程启动,要求如下:
    	AA打印5次,BB打印10次,CC打印15次
    	接着
    	AA打印5次,BB打印10次,CC打印15次
    	.....循环10轮
	<--项目中案例-->
	
总结:
	资源的保护需要加锁
	生产者消费者模式--线程的通信
	加锁+加通信+线程之间的调度访问
	
多线程锁(8锁)
	1 标准访问，先打印短信还是邮件(欲扬先抑)
		同一时间段,被锁的是整个资源类
		短信
		邮件
	2 停4秒在资源类的短信方法内，先打印短信还是邮件(TimeUnit.SECONDS.sleep(4))
		同1,因为锁的是资源
	3 普通的hello方法，是先打短信还是hello
		不会发生资源上的竞争
	4 现在有两部手机，先打印短信还是邮件
		各锁各的   不会发生资源上的竞争
	5 两个静态同步方法，1部手机，先打印短信还是邮件
		static,锁的资源不再是资源类,而是整个.class文件
	eg:class文件相当于生成iphone某一具体系列手机的总工厂,(前面几个案例的锁是this,可以理解为锁的对象是具体的某一部手机)
	6 两个静态同步方法，2部手机，先打印短信还是邮件    jvm  元数据
		static,锁的资源不在是资源,而是整个.class文件,虽然实例变了,但是实际使用的仍然是同一把锁
	7 1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
		前者锁工厂,后者锁具体事例,两把不同锁
	8 1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
		两把不同的锁
callable接口获取多线程
	问题:
		1,创建线程有几种方式?
		2,试着去写出Runnable和callable接口创建线程的代码...
		3,实现Runnable接口与实现Callable接口有什么区别?
			 答 （1）是否有返回值
       			（2）是否抛异常
       			（3）落地方法不一样，一个是run，一个是call
第三种获取java多线程的方法
	1,自定义类实现callable接口
    	public class MyThread implements Callable<Integer> {
    	@Override
    	public Integer call() throws Exception {
    		return 200;
    		}
    	}
	2,查看源码发现是一个函数是接口(@FunctionalInterface)
	3,如何测试,查看Thread的构造方法
	2,通过FutureTask<V>   Runnable接口的实现类FutureTask
    	public static void main(String[] args) throws Exception {
    		FutureTask<Integer> task = new FutureTask<>(new MyThread());	
    		new Thread(task,"AA").start();	
			//放到最后 用来获取返回值
    		Integer integer = task.get();    		
    		System.out.println(integer); 		
    	}
为什么(*************************************************)
	一共三个方面,见 1,2,3三点总结
	Future(未来)Task:用来实现   异步调用
	1,  正常的代码流程:自顶向下,逐步求精
		对于线程来说:使用该接口可以将业务逻辑复杂的线程单独去执行,最后进行汇总,所以get方法只有等到所有线程执行完毕后才能够调用(类似于高考做题)

	2,			public static void main(String[] args) throws Exception, Exception {
    			FutureTask<Integer> ft = new FutureTask<Integer>(new CallableDemo());
    			new Thread(ft,"aa").start();
    			new Thread(ft,"bb").start();
    			new Thread(ft,"cc").start();
    			
    			Integer integer = ft.get();
    			System.out.println(integer);
    		}
			  打印结果:只会打印一次计算结果可以复用,这样可以提高效率在测试类中同时创建多个线程,只会调用一次线程中的方法,也就是说一次FutuTask只计算一次困难任务
	3,ft.get()这个方法的调用只能放到最后调用,只要困难线程没有执行完毕,就无法调用get方法
		代码操作:第一步:	在CallableTest中的call方法中加入Thread.sleep(4000),并打印当前线程的名称
						在测试类中FutureTask.get()执行之前打印当前线程的名称(也就是主线程的名称)
						观察打印效果     主线程并不需要等待,直接打印
				第二部:	在第一步的基础上,将FutureTask的get()方法提到打印主线程代码的前面
						打印效果:此时主线程也会等待4s钟,等到Callable线程唤醒之后再一起执行
	总结(*******):
		在主线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，可以把这些作业交给Future对象在后台完成，
		当主线程将来需要时，就可以通过Future对象获得后台作业的计算结果或者执行状态。		 
		一般FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。		 
		仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法。一旦计算完成，
		就不能再重新开始或取消计算。get方法获取结果只有在计算完成时获取，否则会一直阻塞直到任务转入完成状态，
		然后会返回结果或者抛出异常。 
		
JUC强大的辅助类介绍
	JUC下一共有三个接口 其中Lock 与 Condition 已经接触过,还剩一个 ReadWriteLock
	1,读写锁 ReadWriteLock接口(读写一起)
		举例分析:
				1,读操作与写操作的分离,写锁必排他,读锁可共享,比如张三先抢到锁,他要查询后三十条记录,而李四,是要修改第五条数据,此时就可以存在这种操作,张三读,李四写,张三读锁 时候不会对整张表加读锁,只对李四行添加行锁就可以了
				2,红蜘蛛
				3,机场,国内出发,国内到达
		代码案例:
				一个线程写入,100个线程读取
			    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		    	rwLock.readLock().lock();
		    	rwLock.writeLock().lock();
		参考代码:
				
	2,CountDownLatch  减少计数器
		举例:
			班级晚自习撤离战术(其他线程执行完毕之后,主线程才可以执行)
			先演示问题...
			以下为解决code
			<--项目中案例-->
			
	3,CyclicBarrier  循环栅栏
		概念:
			字面意思是可循环（Cyclic）使用的屏障（Barrier）。它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活
			线程进入屏障通过CyclicBarrier的await()方法
		构造方法:
			CyclicBarrier(int parties, Runnable barrierAction) 
		案例:
			开会必须等人齐了才能开会
			约定爬山,必须等人齐了才能出发
	<--项目中案例-->
	
	4,Semaphore信号灯
		概念:
			在信号灯上我们定义两种操作：
				acquire（获取）  当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），要么一直等下去，直到有线程释放信号量，或超时。
				release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
				信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
		案例:
			争车位  20部汽车争抢10个停车位
			<--项目中案例-->
	 	资源的控制和并发的调度
