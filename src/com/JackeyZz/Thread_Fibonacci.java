package com.JackeyZz;

public class Thread_Fibonacci implements Runnable{

	protected int count;
	public Thread_Fibonacci() {
	}
	public Thread_Fibonacci(int count){
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
	public void run() {
		for(int j=0;j<count;j++){
			System.out.print(getFibonacci()[j]+" ");
			Thread.yield();
		}
		System.out.println();
	}
	
}
