package java_IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;
/*
 * ʵ�ֻ�ȡ�ļ������б�
 */
class DirFilter implements FilenameFilter{

	private Pattern pattern;
	public DirFilter(String regex){
		pattern=Pattern.compile(regex);
	}
	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();//�������ַ�����ȫƥ��ʱ�Ż᷵����ֵ
	}
	
}

public class DirList {

	public static void main(String[] args) {
		File path=new File(".");//��ǰĿ¼����
		String[] list;
		if(args.length==0){
			list=path.list();
		}
		else{
			list=path.list(new DirFilter(args[0]));//�����������accept()���������˵�false���ļ�
			/*
			 * �ڲ�������
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
		Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);//���Դ�Сд����
		for(String dirItem : list){
			System.out.println(dirItem);
		}
		
		//String regex = "\\?|\\*";
		//Pattern pattern = Pattern.compile(regex);
		//String patternStr = pattern.pattern();//����\?\*
		//System.out.println(patternStr);
	}
	/*
	 * �ڲ�������
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
