package verb.adminverbs;

import verb.VerbList;

public class ListAdminVerbs extends VerbList{
	
	public ListAdminVerbs(){
		init();
	}
	
	public boolean init(){
		addVerb(new VerbAtDig());
		addVerb(new VerbAtHelp());
		addVerb(new VerbAtAddExit());
		addVerb(new VerbAtDescribe());
		addVerb(new VerbAtEditMinimap());
		return true;
	}
	
}
