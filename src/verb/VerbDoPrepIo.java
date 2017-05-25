package verb;

import utility.StringUtility;

public class VerbDoPrepIo {
	
	private static final String[] PREPOSITIONS = 
		{"for", "from", "at", "with", "to", "as"};
	
	private String verb = "none";
	private String directObject = "none";
	private String preposition = null;
	private String indirectObject = null;
	private boolean malformed = false;
	
	
	
	public VerbDoPrepIo(String str){
		//Get the verb
		this.verb = StringUtility.getFirstWord(str);
		
		String[] args = StringUtility.getWordListWithoutQuotes(str);
		
		//Count prepositions, if more than 1 then error out
		int cPreps = 0;
		for (int i = 1; i < args.length; i++){
			if (isPreposition(args[i])){
				cPreps++;
			}
		}
		if (cPreps > 1){
			malformed = true;
			return;
		}
		
		//Break it up
		if (args.length == 4) {
			this.verb = args[0];
			this.directObject = args[1];
			this.preposition = args[2];
			this.indirectObject = args[3];
		} 
		if (args.length == 2) {
			this.verb = args[0];
			this.directObject = args[1];
		}
		
	}
	
	public boolean isPreposition(String str){
		for (int i = 0; i < PREPOSITIONS.length; i++){
			if (PREPOSITIONS[i].equals(str)) {
				return true;
			}
		}
		return false;
	}

	public String getVerb() {
		return verb;
	}

	public String getDirectObject() {
		return directObject;
	}

	public String getPreposition() {
		if (preposition == null){
			return "false";
		} else {
			return preposition;
		}
	}

	public String getIndirectObject() {
		if (indirectObject == null){
			return "false";
		} else {
			return indirectObject;
		}
	}

	public boolean isMalformed() {
		return malformed;
	}
	
	

}
