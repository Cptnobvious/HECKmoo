package script.interpreter;

import java.util.ArrayList;

import script.interpreter.functions.FuncPlayerAnnounceRoom;
import script.interpreter.functions.FuncPlayerRoomTell;

public class HeckFunctionList {
	
	private static ArrayList<HeckFunction> functions = new ArrayList<HeckFunction>();
	
	
	public static boolean init(){
		addFunction(new FuncPlayerAnnounceRoom());
		addFunction(new FuncPlayerRoomTell());
		return true;
	}
	
	public static HeckFunction getFunction(String name){
		for (int i = 0; i < functions.size(); i++){
			if (functions.get(i).checkName(name)){
				return functions.get(i);
			}
		}
		return null;
	}
	
	public static boolean addFunction(HeckFunction func){
		if (checkExclusive(func.getName())){
			functions.add(func);
		} else {
			System.out.println("Tried to register function " + func.getName() + " twice!");
		}
		return true;
	}
	
	public static boolean checkExclusive(String str){
		for (int i = 0; i < functions.size(); i++){
			if (functions.get(i).checkName(str)){
				return false;
			}
		}
		return true;
	}

}
