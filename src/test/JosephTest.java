package test;

/**
 * 已知n个人（以编号1，2，3...n分别表示）围坐在一张圆桌周围。 从编号为k的人开始报数，数到m的那个人出列；
 * 他的下一个人又从1开始报数，数到m的那个人又出列； 依此规律重复下去，直到圆桌周围的人全部出列
 * 
 * @author Administrator
 * 
 */
public class JosephTest {
	
	private final static int n = 100;
	private final static int m = 3;

	/*
	 * 复杂度O(NM)
	 */
	private static void test1() {
		boolean[] array = new boolean[n];
		int liveCount = array.length;
		int k = 0;
		int i = 0;
		while (liveCount > 1) {
			if(i==array.length){//从圈头再数过
				i=0;
			}
			for (; i < array.length;i++) {
				if (array[i]) { // 排除出局的人
					continue;
				}
				k++;
				if (k == m) {
					array[i] = true; // 出局
					liveCount--;
					k = 0;
					break;
				}
			}
		}
		for (int l = 0; l < array.length; l++) {
			if (!array[l]) {
				System.out.println("最后剩下的人编号为："+(l+1));
			}
		}
	}

	/*
	 * 复杂度O(M)
	 */
	private static void test2() {
		int s = 0;
		for (int i = 2; i <= n; i++) {
			s = (s + m) % i;
		}
		System.out.println("最后剩下的人编号为：" + (s + 1));
	}

	public static void main(String[] args) {
		//test1();
		test2();
	}
}
