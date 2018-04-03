package com.JackeyZz;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Callable实现线程返回值
 */
class CallableTask implements Callable<String>{
	private int id;
	public CallableTask(int id) {
		this.id=id;
	}
	
	@Override
	public String call() throws Exception {
		return "result of Callable_test"+id;
	}
}

public class Callable_test{

	public static void main(String[] args) {
		ExecutorService exec=Executors.newCachedThreadPool();
		ArrayList<Future<String>> results=new ArrayList<Future<String>>();
		for(int i=0;i<10;i++){
			results.add(exec.submit(new CallableTask(i)));
		}
		for(Future<String> fs : results){
			try {
				System.out.println(fs.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}finally {
				exec.shutdown();
			}
		}
	}
}
