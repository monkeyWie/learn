package jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量池溢出 jdk1.6生效
 * -XX:PermSize=10M-XX:MaxPermSize=10M
 * @author Administrator
 *
 */
public class RuntimeOutTest {
//	public static void main(String[] args) {
//		List<String> temp = new ArrayList<String>();
//		int i = 0;
//		while(true){
//			temp.add(String.valueOf("a"+(i++)).intern());
//		}
//	}
	
	public static void main(String[]args){
		String str1=new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern()==str1);
		String str2=new StringBuilder("a").toString();
		System.out.println(str2.intern()==str2);
	}
}
