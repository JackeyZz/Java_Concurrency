package com.JackeyZz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class PriorityTask implements Runnable{

	private int count=5;
	private volatile double d;
	private int priority;
	public PriorityTask() {
	}
	public PriorityTask(int priority){
		this.priority=priority;
	}
	public String toString(){
		return Thread.currentThread()+":"+count;
	}
	
	@Override
	public void run() {
		Thread.currentThread().setPriority(priority);
		while(true){
			for(int i=1;i<10000;i++){
				d+=(Math.PI+Math.E)/(double)i;
				/*
				if(i%1000==0){
					Thread.yield();
				}*/
			}
			System.out.println(this);
			if(--count==0)
				return;
		}
	}
	
}

public class Runnable_Priority {

	public static void main(String[] args) {
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			exec.execute(new PriorityTask(Thread.MIN_PRIORITY));
		}
		exec.execute(new PriorityTask(Thread.MAX_PRIORITY));
		exec.shutdown();
	}
}
