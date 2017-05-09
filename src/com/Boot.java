package com;

import playermanager.PlayerController;
import world.World;
import network.Relay;

public class Boot {

	
	//TODO: Port defined by config
	private static final int PORT = 7777;
	private static boolean isCloseRequested = false;
	
	
	public static void main(String[] args) {
		
		World.init();
		
		Relay.StartNetwork(PORT);
		
		while (!isCloseRequested){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Relay.think();
			PlayerController.think();
		}
		
		Relay.CloseNetwork();
		
		System.out.println("Server Closed");
	}

	
	public static void requestShutdown(){
		isCloseRequested = true;
	}
}
