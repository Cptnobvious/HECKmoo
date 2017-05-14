package verb.globalverbs;

import verb.VerbList;

//Verbs you can call anywhere

public class ListGlobalVerbs extends VerbList{

	public ListGlobalVerbs(){
		init();
	}
	
	public boolean init(){
		addVerb(new VerbLook());
		addVerb(new VerbGo());
		addVerb(new VerbMap());
		addVerb(new VerbSay());
		return true;
	}
	
}
