package net.mindview.util;

import java.io.File;
import java.io.IOException;

public class ProcessFiles {

	/*
	 * 接口
	 */
	public interface Strategy{
		void process(File file);
	}
	private Strategy strategy;
	private String ext;
	/*
	 * 构造器
	 */
	public ProcessFiles(Strategy strategy,String ext){
		this.strategy=strategy;
		this.ext=ext;
	}
	public void processDirectoryTree(File root) throws IOException{
		for(File file : Directory.walk(root.getAbsolutePath(), ".*\\."+ext)){
			strategy.process(file.getCanonicalFile());
		}
	}
	/*
	 * getPath()返回的是构造方法里的路径，不做任何处理
	 *  getAbsolutePath()返回的是 user.dir+getPath()，也就是执行路径加上构造方法中的路径	 
	 *  getCanonicalPath()返回的是将符号完全解析的路径，也就是全路径
	 */
	public void start(String[] args){
		try {
			if(args.length==0){
				processDirectoryTree(new File("."));
			}else{
				for(String arg : args){
					File fileArg=new File(arg);
					if(fileArg.isDirectory()){
						processDirectoryTree(fileArg);
					}else{
						if(!arg.endsWith("."+ext)){
							arg+="."+ext;
						}
						strategy.process(new File(arg).getCanonicalFile());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ProcessFiles(new ProcessFiles.Strategy() {   //调用接口的方法要重写方法
			@Override
			public void process(File file) {
				System.out.println(file);
			}
		}, "java").start(args);
	}
}
