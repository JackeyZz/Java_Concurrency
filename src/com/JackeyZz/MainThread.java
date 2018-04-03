package com.JackeyZz;

public class MainThread {

	public static void main(String[] args) {
		LiftOff lf=new LiftOff();
		Thread t=new Thread(lf);
		t.start();
		System.out.println("Waiting for LiftOff");
	}
}
