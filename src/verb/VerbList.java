package verb;

import java.util.ArrayList;

//Smarter list of verbs

public class VerbList {

	protected ArrayList<Verb> verbList = new ArrayList<Verb>();
	
	public boolean addVerb(Verb verb){
		//Check the list to see if it's there already
		for (int i = 0; i < verb.getAliases().length; i++){
			if (isOnList(verb.getAliases()[i])){
				return false;
			}
		}
		
		verbList.add(verb);
		return true;
	}
	
	public Verb getVerb(String str){
		for (int i = 0; i < verbList.size(); i++){
			if (verbList.get(i).matches(str)){
				return verbList.get(i);
			}
		}
		return null;
	}
	
	public boolean isOnList(String str){
		for (int i = 0; i < verbList.size(); i++){
			if (verbList.get(i).matches(str)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> getVerbs(){
		ArrayList<String> verbs = new ArrayList<String>();
		for (int i = 0; i < verbList.size(); i++){
			verbs.add(verbList.get(i).getAliases()[0]);
		}
		return verbs;
	}
	
}
