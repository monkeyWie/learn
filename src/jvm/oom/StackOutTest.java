package jvm.oom;

/**
 * 栈溢出
 * -Xss128k
 * @author Administrator
 *
 */
public class StackOutTest {
	private static int i = 0;
	public static void test(){
		i++;
		test();
	}
	
	public static void main(String[] args) {
		try {
			test();
		} catch (Throwable e) {
			System.out.println(i);
			e.printStackTrace();
		}
	}
}
