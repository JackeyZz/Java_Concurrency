package com.JackeyZz;

import java.util.ArrayList;

/*
 * 线程1，破解线程，穷举法匹配密码
 */
class ThreadTask1 extends Thread{

	private String s;
	private ArrayList<String> pws;
	public ThreadTask1(){}
	public ThreadTask1(String s,ArrayList<String> pws){
		this.s=s;
		this.pws=pws;
	}
	@Override
	public void run() {
		while(true){
			getPW(s, pws);
	
			//Thread.yield();
		}
	}
	public static void getPW(String s,ArrayList<String> pws){
		char[] result=new char[3];
		loop:
		for(short i='0';i<='z';i++){
			for(short j='0';j<='z';j++){
				for(short k='0';k<='z';k++){
					if(!isLetterOrDigital(i, j, k))
						continue;
					result[0]=(char) i;
					result[1]=(char)j;
					result[2]=(char)k;
					String temp=new String(result);
					pws.add(temp);
					if(s.equals(temp)){
						System.out.println("破解密码成功,密码是："+temp);
						break loop;
					}
				}
			}
		}
		return;
	}
	public static boolean isLetterOrDigital(short i,short j,short k){
		return Character.isLetterOrDigit(i) && Character.isLetterOrDigit(j)
				&& Character.isLetterOrDigit(k);
	}
}
/*
 * 线程2，守护日志线程，打印字符串匹配记录
 */
class ThreadTask2 extends Thread{
	
	private ArrayList<String> pws;
	public ThreadTask2(){} 
	public ThreadTask2(ArrayList<String> pws){
		this.pws=pws;
		this.setDaemon(true);
	}
	@Override
	public void run(){
        while(true){
        	while(pws.isEmpty()){
        	try {
                		Thread.sleep(50);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
         
        	}
            String password = pws.remove(0);
            System.out.println("穷举法本次生成的密码是：" +password);
        }
    }
}

public class Thread_getPW {

	public static void main(String[] args) {
		String password=randomString(3);
		System.out.println("密码是："+password);
		ArrayList<String> pws=new ArrayList<String>();
		new ThreadTask1(password, pws).start();
		new ThreadTask2(pws).start();
	}
	
	public static String randomString(int length)
	{
		String pool="";
		for(short i='0';i<='9';i++){
			pool+=(char)i;
		}
		for(short i='a';i<='z';i++){
			pool+=(char)i;
		}
		for(short i='A';i<='Z';i++){
			pool+=(char)i;
		}
		char[] result=new char[length];
		for(int i=0;i<result.length;i++){
			int index=(int) (Math.random()*pool.length());
			result[i]=pool.charAt(index);
		}
		return new String(result);
	}
}
