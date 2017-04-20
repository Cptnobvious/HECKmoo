package network;

import java.net.Socket;
import java.util.ArrayList;

//This holds a list of active clients

class ClientsList extends Thread{
	
	//List of all the clients
	private static ArrayList<Client> clients = new ArrayList<Client>();
	//Should I keep thinking
	private static boolean keepAlive = true;
	
	
	public static boolean addClient(Socket sock){
		Client cli = new Client(sock, getUniqueID());
		cli.init();
		clients.add(cli);
		System.out.println("Added a new client");
		//TODO: Check for success
		return false;
	}

	
	public static boolean killClient(int uID){
		//TODO: check for success
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getUniqueID() == uID){
				clients.get(i).killClient();
				clients.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	
	private static int getUniqueID(){
		int uID = 0;
		boolean unique = true;
		
		do {
			unique = true;
			
			for (int i = 0; i < clients.size(); i++){
				if (clients.get(i).getUniqueID() == uID){
					uID++;
					unique = false;
					break;
				}
			}
			
		} while (!unique);
		
		return uID;
	}
	
	public static boolean shutdown(){
		for (int i = 0; i < clients.size(); i++){
			clients.get(i).killClient();
		}
		clients.clear();
		keepAlive = false;
		return true;
	}
	
	public static boolean sendMessageToClient(TaggedClientString tcs){
		for (int i = 0; i < clients.size(); i++){
			if (clients.get(i).getUniqueID() == tcs.getUID()){
				clients.get(i).sendToClient(tcs.getString());
				return true;
			}
		}
		return false;
	}
	
	public void run(){
		while (keepAlive){
			try {
				think();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static boolean think(){
		for (int i = 0; i < clients.size(); i++){
			clients.get(i).think();
		}
		return true;
	}
}
