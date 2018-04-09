package com.JackeyZz;

import java.util.concurrent.TimeUnit;

/*
 * 1、设置后台线程必须在线程启动前设置
 * 2、后台线程衍生的子线程依旧是后台线程
 * 3、main主线程结束后，又没有其他普通线程，那么后台线程也会结束
 */
class DaemonTask1 implements Runnable{

	private Thread[] t=new Thread[10];
	
	@Override
	public void run() {
		for(int i=0;i<t.length;i++){
			t[i]=new Thread(new DaemonTask2());
			t[i].start();
			System.out.println("DaemonTask2 "+i+" started, ");
		}
		for(int i=0;i<t.length;i++){
			System.out.println("t["+i+"].isDaemon()= "+t[i].isDaemon()+", ");
		}
		while(true)
			Thread.yield();
	}
	
}

class DaemonTask2 implements Runnable{

	@Override
	public void run() {
		while(true)
			Thread.yield();
	}
	
}

public class Runnable_Daemons {
	
	public static void main(String[] args) throws Exception {
		Thread t=new Thread(new DaemonTask1());
		t.setDaemon(true);
		t.start();
		System.out.println("t.isDaemon()="+t.isDaemon()+",");
		TimeUnit.SECONDS.sleep(1);  //设置主线程睡眠，以便可以查看子线程的输出情况
	}
}
