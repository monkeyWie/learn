package netty.serializable;

import java.io.Serializable;

public class OrderResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int code;
	private String msg;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "id:"+getId()+"\t code:"+code+"\t msg:"+msg;
	}
	
	public void loop(){
		while(true){
			//System.out.println();
		}
	}
}
