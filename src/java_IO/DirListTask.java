package java_IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

import net.mindview.util.TextFile;

/*
 * 基于TextFile工具类实现接受文件路径并打开文件，判断命令行尾随的参数是否存在于那个文件中，以此检查是否接受这个文件
 * 'java java_IO.DirListTask jackeyzz' 
 */
public class DirListTask {
	public static void main(String[] args) {
		File path=new File("C:\\Users\\acer\\Desktop\\javaio");
		String[] list;
		if(args.length==0){
			list=path.list();
		}else{
			list=path.list(new FilenameFilter() {
				Pattern pattern=Pattern.compile(args[0]);
				@Override
				public boolean accept(File dir, String name) {
					TextFile text=new TextFile("C:\\Users\\acer\\Desktop\\javaio\\"+name);
					for(String item : text){
						if(pattern.matcher(item).matches()){
							return true;
						}
					}
					return false;
				}
			});
		}
		for(String item1 : list){
			System.out.println(item1);
		}
	}
}
