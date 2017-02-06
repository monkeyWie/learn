package com.example.thread;

public class ThreadTest {
	
	public static void main(String[] args) {
		StackBasket stack = new StackBasket();
		new Producer(stack).start();
		//new Consumer(stack).start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(1111);
	}
}

//生产者
class Producer extends Thread{
	
	private StackBasket stackBasket;
	
	public Producer(StackBasket stackBasket) {
		this.stackBasket=stackBasket;
	}
	
	//生产
	public void run(){
		int i = 1;
		while(true){
			//stackBasket.push(new Thing(i++));
			System.out.println(2222);
			try {
				Thread.sleep(1000);  
				//Thread.sleep((int)(Math.random()*10000));  
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

//消费者
class Consumer extends Thread{
	
	private StackBasket stackBasket;
	
	public Consumer(StackBasket stackBasket) {
		this.stackBasket=stackBasket;
	}
	
	//消费
	public void run(){
		for(int i = 1;i<=20;i++){
			//stackBasket.pop();
			try {
				Thread.sleep(2000);  
				//Thread.sleep((int)(Math.random()*10000));  
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

class Thing{
	private int id;
	
	public Thing(int id){
		this.id = id;
	}
	
	public String toString(){
		return "包子ID："+id;
	}
}

class StackBasket{
	private int index=0;

	Thing[] things = new Thing[5];
	
	public void push(Thing thing){
		//生产满了
		if(index==things.length){
			try {
				System.out.println("【生产】包子满了等人消费");
				for (Thing t : things) {
					System.out.print(t.toString()+"\t");
				}
				System.out.print("未消费");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("【生产】"+thing.toString());
		things[index++]=thing;
		try {
			this.notify();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public Thing pop(){
		//消费完了
		if(index==0){
			try {
				System.out.println("【消费】包子没了等人生产");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		index--;
		System.out.println("【消费】"+things[index].toString());
		try {
			this.notify();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return things[index];
	}
	
	public synchronized void test1(){
		for(int i = 0;i<10;i++){
			System.out.println("test1:"+i);
		}
	}
	
	public synchronized void test2(){
		for(int i = 0;i<10;i++){
			System.out.println("test2:"+i);
		}
	}
}
