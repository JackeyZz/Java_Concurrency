package java_IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

import net.mindview.util.TextFile;

/*
 * ����TextFile������ʵ�ֽ����ļ�·�������ļ����ж�������β��Ĳ����Ƿ�������Ǹ��ļ��У��Դ˼���Ƿ��������ļ�
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
