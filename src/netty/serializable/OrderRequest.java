package netty.serializable;

import java.io.Serializable;

public class OrderRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:"+getId()+"\t name:"+getName()+"\t address:"+getAddress();
	}
	
	public static void main(String[] args) {
		System.out.println(11111);
		new OrderRequest();
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new OrderResponse().loop();
	}
	
	
}
