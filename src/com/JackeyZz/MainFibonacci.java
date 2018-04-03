package com.JackeyZz;

/*
 * 线程实现斐波那契数列
 */

public class MainFibonacci {
	public static void main(String[] args) {
		for(int i=0;i<5;i++){
			new Thread(new Thread_Fibonacci(8)).start();
		}
		System.out.println("***begin");
	}
}
