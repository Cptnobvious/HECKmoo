package network;

class Network {

	private static ClientWatcher clientWatcher = null;
	private static ClientsList clientsList = null;
	
	//Starts client watcher
	public static boolean StartNetwork(int port){
		clientWatcher = new ClientWatcher(port);
		clientWatcher.start();
		clientsList = new ClientsList();
		clientsList.start();
		return true;
	}
	
	//Closes client watcher
	public static boolean CloseNetwork(){
		clientWatcher.stopClientWatcher();
		ClientsList.shutdown();
		return true;
	}
	
	public static boolean think(){
		ClientsList.think();
		return true;
	}
	
}
