package com.example.test;

public class ObjectTest {
	
	String a = "1";
	
	public static void change(ObjectTest ot){
		//ot = new ObjectTest();
		ot.a="2";
	}
	
	public static void change(String s){
		s += "2";
	}
	
	public static void change(int a){
		a=-1;
	}
	
	public static void main(String[] args) {
		ObjectTest ot = new ObjectTest();
		System.out.println(ot.a);
		change(ot);
		System.out.println(ot.a);
		
		String s = "1";
		System.out.println(s);
		change(s);
		System.out.println(s);
		
		int a = 5;
		change(a);
		System.out.println(a);
	}

}
