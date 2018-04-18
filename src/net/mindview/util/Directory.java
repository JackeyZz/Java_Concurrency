package net.mindview.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class Directory {

	/*
	 * 使用File.list()的变体listFiles产生File数组，本地目录下的文件集
	 */
	public static File[] local(File dir,final String regex){
		return dir.listFiles(new FilenameFilter() {
			private Pattern pattern=Pattern.compile(regex);
			@Override
			public boolean accept(File dir, String name) {   //name即是pattern中的regex
				return pattern.matcher(new File(name).getName()).matches();
			}
		});
	}
	public static File[] local(String path,final String regex){
		return local(new File(path),regex);
	}
	
	/*
	 * Iterable迭代器
	 */
	public static class TreeInfo implements Iterable<File>{

		public List<File> files=new ArrayList<File>();
		public List<File> dirs=new ArrayList<File>();
		@Override
		public Iterator<File> iterator() {
			return files.iterator();
		}
		void addAll(TreeInfo other){
			files.addAll(other.files);//list.add()只会添加一个实例，而不会添加多个实例组成的容器。list.addAll()可以添加多个实例
			dirs.addAll(other.dirs);
		}
		public String toString(){
			return "dirs:"+PPrint.pformat(dirs)+"\n\nfiles:"+PPrint.pformat(files);
		}
	}
	
	/*
	 * 递归查找目录树的所有文件
	 */
	static TreeInfo recurseDirs(File startDir,String regex){
		TreeInfo result=new TreeInfo();
		for(File item : startDir.listFiles()){
			if(item.isDirectory()){   //s是否是目录
				result.dirs.add(item);
				result.addAll(recurseDirs(item, regex));
			}else{
				if(item.getName().matches(regex))   //不是目录即为文件，判断文件名是否满足正则表达式
					result.files.add(item);
			}
		}
		return result;
	}
	
	/*
	 * 产生给定目录下的由整个目录树中所有文件构成的List<File>
	 */
	public static TreeInfo walk(String start,String regex){
		return recurseDirs(new File(start), regex);
	}
	public static TreeInfo walk(File start,String regex){
		return recurseDirs(start, regex);
	}
	public static TreeInfo walk(File start){
		return recurseDirs(start, ".*");
	}
	public static TreeInfo walk(String start){
		return recurseDirs(new File(start), ".*");
	}
	
	/*
	 * 
	 */
	public static void main(String[] args) {
		if(args.length==0){
			System.out.println(walk("."));
		}else{
			for(String arg : args){
				System.out.println(walk(arg));
			}
		}
	}
}
