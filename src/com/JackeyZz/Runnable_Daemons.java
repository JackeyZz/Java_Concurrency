package com.JackeyZz;

import java.util.concurrent.TimeUnit;

/*
 * 1�����ú�̨�̱߳������߳�����ǰ����
 * 2����̨�߳����������߳������Ǻ�̨�߳�
 * 3��main���߳̽�������û��������ͨ�̣߳���ô��̨�߳�Ҳ�����
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
		TimeUnit.SECONDS.sleep(1);  //�������߳�˯�ߣ��Ա���Բ鿴���̵߳�������
	}
}
