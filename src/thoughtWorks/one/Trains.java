package thoughtWorks.one;

import java.util.ArrayList;
import java.util.List;

public class Trains {
	
	static enum Town{
		A,B,C,D,E
	}
	
	private static List<Route> routeList = null;
	
	static{
		//初始化数据
		routeList = new ArrayList<Route>();
		routeList.add(new Route(Town.A,Town.B,5));
		routeList.add(new Route(Town.B,Town.C,4));
		routeList.add(new Route(Town.C,Town.D,8));
		routeList.add(new Route(Town.D,Town.C,8));
		routeList.add(new Route(Town.D,Town.E,6));
		routeList.add(new Route(Town.A,Town.D,5));
		routeList.add(new Route(Town.C,Town.E,2));
		routeList.add(new Route(Town.E,Town.B,3));
		routeList.add(new Route(Town.A,Town.E,7));
	}
			
	
	public static void main(String[] args) {
//		System.out.println(findStops(Town.C,Town.C));
//		System.out.println(findRoute(Town.A,Town.E,Town.B,Town.C,Town.D));
		/*Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the number:");
		int num = scanner.nextInt();
		System.out.print("Output #"+num+":");
		if(num==1){	//ABC
			System.out.println(findRoute(Town.A,Town.B,Town.C));
		}*/
	}
	
	/**
	 * 寻找路径，并返回路径距离
	 * @param town
	 * @return
	 */
	public static Integer findRoute(Town... towns){
		if(towns.length>1){
			int sum = 0;
			for (int i = 0; i < towns.length-1; i++) {
				//标识是否找到可用路径
				boolean isFind = false;
				//按顺序找路径并计算距离
				for (Route route : routeList) {
					if(route.checkDirection(towns[i],towns[i+1])){
						sum+= route.getDistance();
						isFind = true;
						break;
					}
				}
				//路径没找到直接返回
				if(!isFind){
					return null;
				}
			}
			return sum;
		}
		return null;
	}
	
	/**
	 * 寻找两点之间所有的出行方式
	 * @param from
	 * @param to
	 * @return
	 */
	public static int findStops(Town from,Town to){
		Integer count = 0;
		findStops(from,to,count);
		return count;
	}
	
	/**
	 * 寻找两点之间所有的出行方式
	 * @param from
	 * @param to
	 * @return
	 */
	private static void findStops(Town from,Town to,Integer count){
		for (int i = 0; i < routeList.size(); i++) {
			Route route = routeList.get(i);
			if(route.getFrom()==from){
				System.out.println(route.toString());
				//是否结束行程
				if(route.getTo()==to){
					count++;
					break;
				}else{
					findStops(route.getTo(),to,count);
				}
			}
		}
	}
}
