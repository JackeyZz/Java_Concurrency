package com.JackeyZz;

/*
 * �߳�ʵ��쳲���������
 */

public class MainFibonacci {
	public static void main(String[] args) {
		for(int i=0;i<5;i++){
			new Thread(new Thread_Fibonacci(8)).start();
		}
		System.out.println("***begin");
	}
}
