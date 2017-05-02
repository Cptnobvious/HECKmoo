package verb.adminverbs;

import verb.VerbList;

public class ListAdminVerbs extends VerbList{
	
	public ListAdminVerbs(){
		init();
	}
	
	public boolean init(){
		addVerb(new VerbAtDig());
		return true;
	}
	
}
