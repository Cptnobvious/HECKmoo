package com;

import playermanager.PlayerController;
import saving.SaveManager;
import world.World;
import network.Relay;

public class Boot {

	
	//TODO: Port defined by config
	private static final int PORT = 7777;
	private static boolean isCloseRequested = false;
	private static final int savetime = 20 * 300;
	
	public static void main(String[] args) {
		
		SaveManager.loadAll();
		World.init();
		
		Relay.StartNetwork(PORT);
		
		int shouldsave = 0;
		while (!isCloseRequested){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Relay.think();
			PlayerController.think();
			shouldsave++;
			if (shouldsave > savetime){
				SaveManager.saveAll();
				System.out.println("Saved world");
				shouldsave = 0;
			}
		}
		
		Relay.CloseNetwork();
		SaveManager.saveAll();
		
		System.out.println("Server Closed");
	}

	
	public static void requestShutdown(){
		isCloseRequested = true;
	}
}
