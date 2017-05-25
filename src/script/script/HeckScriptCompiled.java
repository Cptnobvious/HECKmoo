package script.script;

import java.util.ArrayList;

import script.interpreter.HeckFunctionList;
import utility.StringUtility;

public class HeckScriptCompiled {

	private ArrayList<ExecutionBlock> blocks = new ArrayList<ExecutionBlock>();
	private ArrayList<String> toCompile = null;
	private ArrayList<String> compiled = null;
	private String compilerErrorMessage = "None";
	
	public static final String[] conditionals = {"if", "elseif", "else", "endif", "BLOCKINDEX"};
	public static final String[] tags = {"func", "mov"};
	//Start by getting exec block 1, go to the tree statement it asks next, follow tree from there
	
	public HeckScriptCompiled(ArrayList<String> script){
		this.toCompile = script;
	}
	
	public ArrayList<String> getBlock(int i){
		return blocks.get(i).getBlock();
	}
	
	public String getErrorMessage(){
		return this.compilerErrorMessage;
	}
	
	public ArrayList<String> getCompiledScript(){
		return compiled;
	}
	
	public boolean compile(){
		ArrayList<String> sc = toCompile;
	
		//Check for errors
		if (checkProblems(sc)){
			toCompile = null;
			return false;
		}
		
		//Strip the blocks out of the script
		stripBlocks(sc);
		
		//Adding dummy blocks in between conditionals
		padConditionals(sc);
		
		//Set the final script
		this.compiled = sc;
		
		//Remove the tocompile again to clear memory
		toCompile = null;
		return true;
	}
	
	private boolean checkProblems(ArrayList<String> sc){
		if (onlyKnownTags(sc)){
			if (onlyKnownFunctions(sc)){
				return false;
			}
		}
		return true;
	}
	
	private boolean onlyKnownFunctions(ArrayList<String> sc){
		for (int i = 0; i < sc.size(); i++){
			if (sc.get(i).startsWith("func")){
				String[] funcName = StringUtility.getWordList(sc.get(i));
				if (HeckFunctionList.getFunction(funcName[1]) == null){
					this.compilerErrorMessage = "Error: No function of name " + funcName[1];
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean onlyKnownTags(ArrayList<String> sc){
		for (int i = 0; i < sc.size(); i++){
			if (!knownTag(sc.get(i))){
				compilerErrorMessage = "Unknown tag at line " + i;
				return false;
			}
		}
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
			boolean blockAdded = false;
			boolean stuffToAdd = false;
			ExecutionBlock blockToAdd = new ExecutionBlock();
			
			if (blockLeft){
				for (int i = line; i < sc.size(); i++){
					if (!lineHasConditional(sc.get(i))){
						blockToAdd.addLine(sc.get(i));
						stuffToAdd = true;
					} else {
						blocks.add(blockToAdd);
						blockAdded = true;
						break;
					}
				}
				
				if (!blockAdded && stuffToAdd){
					blocks.add(blockToAdd);
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
	
	private boolean padConditionals(ArrayList<String> sc){
		
		boolean finished = true;
		
		//If there's only one block skip this
		if (sc.size() == 1){
			return true;
		}
		
		do {
			finished = true;
			
			for (int i = 0; i < sc.size(); i++){
				if (finished == false){
					break;
				}
				
				//is this line a conditional?
				if (lineHasConditional(sc.get(i))){
					
					//is there a next line even?
					if (i + 1 < sc.size()){
						
						//Is that line another conditional?
						if (lineHasConditional(sc.get(i + 1))){
							//Split them with a dummy block
							blocks.add(new ExecutionBlock());
							//Add a line there 
							sc.add(i, "BLOCKINDEX " + (sc.size() - 1));
							//we are not finished
							finished = false;
							break;
						}
						
					} else {
						//No room for a next line so pad the end
						blocks.add(new ExecutionBlock());
						sc.add("BLOCKINDEX " + (sc.size() - 1));
						break;
					}
				}
				
			}
			
			
		} while (!finished);
		
		
		
		return true;
	}
	
	public static boolean knownTag(String line){
		
		if (lineHasTag(line)){
			return true;
		}
		
		if (lineHasConditional(line)){
			return true;
		} 
		
		return false;
		
	}
	
	public static boolean lineHasTag(String line){
		for (int i = 0; i < tags.length; i++){
			if (line.startsWith(tags[i])){
				return true;
			}
		}
		return false;
	}
	
	public static boolean lineHasConditional(String line){
		for (int i = 0; i < conditionals.length; i++){
			if (line.startsWith(conditionals[i])){
				return true;
			}
		}
		return false;
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
