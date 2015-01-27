package com.jjit.pdfgenerator;


class Num{
	int[] n=new int[100];
	
}
public class GcDemo { 
	public static void main(String [] args) {
		Runtime rt = Runtime.getRuntime();
		System.out.println("Total JVM memory: " + rt.totalMemory());
		Num n1 = new Num();
		Num n2 = new Num();
		System.out.println("Before Memory =   " + rt.freeMemory());
		n1 = null;
		n2 = null;
		System.gc();
		
		System.out.println("After GC Memory = " + rt.freeMemory());
	}
}
