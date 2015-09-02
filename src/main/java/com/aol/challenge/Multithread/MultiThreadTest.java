package com.aol.challenge.Multithread;

public class MultiThreadTest {

	public static void main(String args[]) {

		MultiThreadImpl R1 = new MultiThreadImpl("Thread 1");
		R1.start();

		MultiThreadImpl R2 = new MultiThreadImpl("Thread 2");
		R2.start();
	}   
}
