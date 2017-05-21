package script.script;

import java.util.ArrayList;

public class HeckScriptCompiled {

	ArrayList<LogicTree> tree = new ArrayList<LogicTree>();
	ArrayList<ExecutionBlock> blocks = new ArrayList<ExecutionBlock>();
	
	//Start by getting exec block 1, go to the tree statement it asks next, follow tree from there
	
	public HeckScriptCompiled(HeckScript script){
		ArrayList<String> sc = script.getScript();
		
		ExecutionBlock iBlock = new ExecutionBlock();
		for (int i = 0; i < sc.size(); i++){
			//For now just stuff it all into a single exec block
		}
	}
	
	private class LogicTree{
		
	}
	
	private class ExecutionBlock{
		
		private ArrayList<String> block = new ArrayList<String>();
		
		public boolean addLine(String str){
			block.add(str);
			return true;
		}
		
		public ArrayList<String> getBlock(){
			return block;
		}
		
	}
	
}
