package java_IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;
/*
 * 实现获取文件名称列表
 */
class DirFilter implements FilenameFilter{

	private Pattern pattern;
	public DirFilter(String regex){
		pattern=Pattern.compile(regex);
	}
	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();//对整个字符串完全匹配时才会返回真值
	}
	
}

public class DirList {

	public static void main(String[] args) {
		File path=new File(".");//当前目录所在
		String[] list;
		if(args.length==0){
			list=path.list();
		}
		else{
			list=path.list(new DirFilter(args[0]));//过滤器类调用accept()方法，过滤掉false的文件
			/*
			 * 内部匿名类
			 * 
			list=path.list(new FilenameFilter() {
				private Pattern pattern=Pattern.compile(args[0]);
				@Override
				public boolean accept(File dir, String name) {
					// TODO Auto-generated method stub
					return pattern.matcher(name).matches();
				}
			});*/
		}
		Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);//忽略大小写排序
		for(String dirItem : list){
			System.out.println(dirItem);
		}
		
		//String regex = "\\?|\\*";
		//Pattern pattern = Pattern.compile(regex);
		//String patternStr = pattern.pattern();//返回\?\*
		//System.out.println(patternStr);
	}
	/*
	 * 内部匿名类
	 */
	public static FilenameFilter filter(final String regex){
		return new FilenameFilter() {
			private Pattern pattern=Pattern.compile(regex);
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return pattern.matcher(name).matches();
			}
		};
	}
}
