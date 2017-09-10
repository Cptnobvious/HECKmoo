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
		addVerb(new VerbDrop());
		addVerb(new VerbGet());
		addVerb(new VerbEmote());
		addVerb(new VerbWho());
		addVerb(new VerbLocalOOC());
		addVerb(new VerbExAlias());
		addVerb(new VerbExDescribe());
		addVerb(new VerbExRoll());
		addVerb(new VerbExChat());
		return true;
	}
	
}
