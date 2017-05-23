package script.script;

import java.util.ArrayList;

public class HeckScriptCompiled {

	private ArrayList<LogicTree> tree = new ArrayList<LogicTree>();
	private ArrayList<ExecutionBlock> blocks = new ArrayList<ExecutionBlock>();
	private HeckScript toCompile = null;
	
	private static final String[] conditionals = {"if", "elseif", "else", "endif", "BLOCKINDEX"};
	//Start by getting exec block 1, go to the tree statement it asks next, follow tree from there
	
	public HeckScriptCompiled(HeckScript script){
		this.toCompile = script;
	}
	
	public ArrayList<String> getBlock(int i){
		return blocks.get(i).getBlock();
	}
	
	public boolean compile(){
		ArrayList<String> sc = toCompile.getScript();
	
		stripBlocks(sc);
		
		
		//Remove the tocompile again to clear memory
		toCompile = null;
		return true;
	}
	
	private boolean stripBlocks(ArrayList<String> sc){

		//Pull out all the execution blocks and replace them with BLOCKINDEX #
		boolean blocksDone = false;
		int line = 0;
		do {
			//Find a block to strip out by incrementing line until we hit on something
			//Is there a block left to strip?
			boolean blockLeft = false; 
			for (int i = 0; i < sc.size(); i++){
				if (!lineHasConditional(sc.get(i))){
					line = i;
					blockLeft = true;
					break;
				}
			}


			//If we found something, strip it out
			if (blockLeft){
				ExecutionBlock blockToAdd = new ExecutionBlock();
				for (int i = line; i < sc.size(); i++){
					if (!lineHasConditional(sc.get(i))){
						blockToAdd.addLine(sc.get(i));
					} else {
						blocks.add(blockToAdd);
						break;
					}
				}

				//Remove those lines from the arraylist and replace them with a BLOCKINDEX #
				String blockI = "BLOCKINDEX " + String.valueOf(blocks.size() - 1);
				sc.set(line, blockI);
				boolean linesToStrip = true;
				while (linesToStrip){
					if ((line + 1) < sc.size() && !lineHasConditional(sc.get(line+1))){
						sc.remove(line + 1);
					} else {
						linesToStrip = false;
					}
				}
			}

			//Reset line in case we have to go again
			line = 0;

			//Assume we're done
			blocksDone = true;

			//If any line doesn't start with a conditional OR BLOCKINDEX we aren't done
			for (int i = 0; i < sc.size(); i++){
				if (!lineHasConditional(sc.get(i))){
					blocksDone = false;
					break;
				}
			}
		} while (!blocksDone);
		
		return true;
	}
	
	private boolean lineHasConditional(String line){
		for (int i = 0; i < conditionals.length; i++){
			if (line.startsWith(conditionals[i])){
				return true;
			}
		}
		return false;
	}
	
	private class LogicTree{
		
		String conditional = null;
		int trueblock = -1;
		int falseblock = -1;
		
		public LogicTree(String con, int trueblock, int falseblock){
			this.conditional = con;
			this.trueblock = trueblock;
			this.falseblock = falseblock;
		}
		
		public String getStatement(){
			return conditional;
		}
		
		public int getTrueblock(){
			return trueblock;
		}
		
		public int getFalseblock(){
			return falseblock;
		}
		
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
