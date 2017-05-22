package script.interpreter.functions;

import script.attributes.Attribute;
import script.interpreter.HeckFunction;
import script.script.ScriptArguments;

public class FuncAnnouncePlayerRoom extends HeckFunction{

	@Override
	public boolean setFunctionName() {
		exactName = "AnnouncePlayerRoom";
		return true;
	}

	@Override
	public Attribute run(String[] args, ScriptArguments sa){
		
		
		return null;
	}

}
