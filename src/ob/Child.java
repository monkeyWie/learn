package ob;

import java.util.Observable;

public class Child extends Observable{

	public void cry(){
		this.setChanged();
		this.notifyObservers();
	}
	
	public static void main(String[] args) {
		Observable o = new Observable();
		Child c = new Child();
		c = (Child) o;
		
		/*System.out.println(c.getClass().getSuperclass().getSuperclass().getName());
		if(c.getClass().getName().equals(Child.class.getName())){
			System.out.println(111);
		}*/
	}
	
}
