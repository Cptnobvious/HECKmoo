package com;

import playermanager.PlayerController;
import utility.StringUtility;
import world.World;
import network.Relay;

public class Boot {

	
	//TODO: Port defined by config
	private static final int PORT = 7777;
	private static boolean isCloseRequested = false;
	
	
	public static void main(String[] args) {
		
		String[] arr = StringUtility.getWordListWithoutQuotes("@add-exit \"test room\" \"assclowns\" \"\" 1");
		for (int i = 0; i < arr.length; i++){
			System.out.println(arr[i] + "\n");
		}
		
//		World.init();
//		
//		Relay.StartNetwork(PORT);
//		
//		while (!isCloseRequested){
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			Relay.think();
//			PlayerController.think();
//		}
		
		System.out.println("Server Closed");
	}

}
