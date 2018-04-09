package com.JackeyZz;

public class Thread_Test extends Thread{

	private int count=5;
	private static int threadcount=0;
	public Thread_Test(){
		super(Integer.toString(++threadcount));
		start();
	}
	public String toString(){
		return "#"+getName()+"("+count+"),";
	}
	public void run(){
		while(true){
			System.out.print(this);
			if(--count==0){
				return;
			}
		}
	}
	public static void main(String[] args) {
		for(int i=0;i<5;i++){
			new Thread_Test();
		}
	}
}
