package com.example.test;

public class MemoryTest {
	public static void main(String[] args) {
		int a = 128;
		int b = 128;
		Integer a2 = 127; 
		Integer b2 = 127; 
		Integer c2 = new Integer(127); 
		Integer d2 = Integer.valueOf("127"); 
		Long l1 = new Long(256);
		Long l2 = new Long(256);
		String a1 = "ab";
		String b1 = "ab";
		String c1 = new String("ab");
		String d1 = "a"+"b";
		System.out.println(a==b);
		System.out.println(a1==b1);
		System.out.println(a1==c1);
		System.out.println(a1==d1);
		System.out.println(a2==c2);
		System.out.println(a2==d2);
		System.out.println(l1==l2);
	}
}
