package utility;

public class NetworkUtility {
	
	public static boolean compareIPs(String a, String b){
		return NetworkUtility.removeIPPort(a).equals(NetworkUtility.removeIPPort(b));
	}
	
	public static String removeIPPort(String str){
		return str.split(":")[0];
	}

}
