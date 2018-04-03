package com.JackeyZz;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Callable实现斐波那契数列之和
 */
class CallableTask1 implements Callable<Integer>{

	private int count;
	int sum=0;
	public CallableTask1(int count){
		this.count=count;
	}
	public int[] getFibonacci(){
		int[] result=new int[count];
		result[0]=1;
		result[1]=1;
		for(int i=2;i<count;i++){
			result[i]=result[i-1]+result[i-2];
		}
		return result;
	}
	
	@Override
	public Integer call() throws Exception {
		
		for(int j=0;j<count;j++){
			sum=sum+getFibonacci()[j];
		}
		return sum;
	}
	
}

public class Callable_Fibonacci {
	public static void main(String[] args) {
		ExecutorService exec=Executors.newCachedThreadPool();
		ArrayList<Future<Integer>> results=new ArrayList<Future<Integer>>();
		for(int i=3;i<7;i++){
			results.add(exec.submit(new CallableTask1(i)));
		}
		for(Future<Integer> fi : results){
			try {
				System.out.println(fi.get());                        
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
