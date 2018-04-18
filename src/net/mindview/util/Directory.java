package net.mindview.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class Directory {

	/*
	 * ʹ��File.list()�ı���listFiles����File���飬����Ŀ¼�µ��ļ���
	 */
	public static File[] local(File dir,final String regex){
		return dir.listFiles(new FilenameFilter() {
			private Pattern pattern=Pattern.compile(regex);
			@Override
			public boolean accept(File dir, String name) {   //name����pattern�е�regex
				return pattern.matcher(new File(name).getName()).matches();
			}
		});
	}
	public static File[] local(String path,final String regex){
		return local(new File(path),regex);
	}
	
	/*
	 * Iterable������
	 */
	public static class TreeInfo implements Iterable<File>{

		public List<File> files=new ArrayList<File>();
		public List<File> dirs=new ArrayList<File>();
		@Override
		public Iterator<File> iterator() {
			return files.iterator();
		}
		void addAll(TreeInfo other){
			files.addAll(other.files);//list.add()ֻ�����һ��ʵ������������Ӷ��ʵ����ɵ�������list.addAll()������Ӷ��ʵ��
			dirs.addAll(other.dirs);
		}
		public String toString(){
			return "dirs:"+PPrint.pformat(dirs)+"\n\nfiles:"+PPrint.pformat(files);
		}
	}
	
	/*
	 * �ݹ����Ŀ¼���������ļ�
	 */
	static TreeInfo recurseDirs(File startDir,String regex){
		TreeInfo result=new TreeInfo();
		for(File item : startDir.listFiles()){
			if(item.isDirectory()){   //s�Ƿ���Ŀ¼
				result.dirs.add(item);
				result.addAll(recurseDirs(item, regex));
			}else{
				if(item.getName().matches(regex))   //����Ŀ¼��Ϊ�ļ����ж��ļ����Ƿ�����������ʽ
					result.files.add(item);
			}
		}
		return result;
	}
	
	/*
	 * ��������Ŀ¼�µ�������Ŀ¼���������ļ����ɵ�List<File>
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
