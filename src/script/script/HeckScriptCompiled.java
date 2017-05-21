package script.script;

import java.util.ArrayList;

public class HeckScriptCompiled {

	private ArrayList<LogicTree> tree = new ArrayList<LogicTree>();
	private ArrayList<ExecutionBlock> blocks = new ArrayList<ExecutionBlock>();
	private HeckScript toCompile = null;
	
	//Start by getting exec block 1, go to the tree statement it asks next, follow tree from there
	
	public HeckScriptCompiled(HeckScript script){
		this.toCompile = script;
	}
	
	public ArrayList<String> getBlock(int i){
		return blocks.get(i).getBlock();
	}
	
	public boolean compile(){
		ArrayList<String> sc = toCompile.getScript();
		
		ExecutionBlock iBlock = new ExecutionBlock();
		for (int i = 0; i < sc.size(); i++){
			//For now just stuff it all into a single exec block
			iBlock.addLine(sc.get(i));
		}
		blocks.add(iBlock);
		
		//Remove the tocompile again to clear memory
		toCompile = null;
		return true;
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
