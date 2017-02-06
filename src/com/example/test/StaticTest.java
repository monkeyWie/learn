package com.example.test;

public class StaticTest {
	
	static{
		System.out.println(111);
	}
	
	public static void main(String[] args) {
		TestB t1 = new TestB();
		TestB t2 = new TestB();
		t1=t2;
		t2.name="test";
		System.out.println(t1.name);
	}
	
}

class TestB{
	
	String name;
	
	static{
		System.out.println(333);
	}
	
	public TestB(){
		//System.out.println("init");
	}
	
	static void test(){
		
	}
	
}
