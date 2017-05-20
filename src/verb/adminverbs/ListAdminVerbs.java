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
		addVerb(new VerbAtName());
		addVerb(new VerbAtPalette());
		addVerb(new VerbAtExamine());
		addVerb(new VerbAtShutdown());
		addVerb(new VerbAtTeleport());
		addVerb(new VerbAtAddZone());
		addVerb(new VerbAtAuditWorld());
		addVerb(new VerbAtAuditZone());
		addVerb(new VerbAtRecycle());
		addVerb(new VerbAtItemTest());
		addVerb(new VerbAtCreate());
		addVerb(new VerbAtAuditItem());
		addVerb(new VerbAtEdit());
		addVerb(new VerbAtScript());
		addVerb(new VerbAtVerb());
		return true;
	}
	
}
