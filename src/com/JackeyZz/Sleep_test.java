package com.JackeyZz;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class SleepTask extends Runnable_test{

	public void run(){
		while(countDown-->0){
			try {
				System.out.println(status());
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class Sleep_test {

	public static void main(String[] args) {
		ExecutorService exec=Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			exec.execute(new SleepTask());
		}
		exec.shutdown();
	}
}
