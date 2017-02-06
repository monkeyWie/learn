package ob;

import java.util.Observable;
import java.util.Observer;

public class Person implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(1);
	}
	
	public static void main(String[] args) {
		Person p = new Person();
		Child c1 = new Child();
		c1.addObserver(p);
		c1.cry();
	}

}
