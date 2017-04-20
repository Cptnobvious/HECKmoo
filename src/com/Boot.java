package com;

import network.Relay;

public class Boot {

	
	//TODO: Port defined by config
	private static final int PORT = 7777;
	private static boolean isCloseRequested = false;
	
	
	public static void main(String[] args) {
		
		Relay.StartNetwork(PORT);
		
		while (!isCloseRequested){
			Relay.think();
		}
		
		System.out.println("test");
	}

}
