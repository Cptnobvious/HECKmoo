package items;

import java.util.ArrayList;

import script.ScriptInterface;
import script.attributes.Attribute;
import script.attributes.AttributeList;
import script.script.HeckScript;
import script.script.ScriptList;
import verb.Verb;
import verb.VerbList;

public class Item implements ScriptInterface{
	
	public static final String ITEMNAME = 		"_NAME";
	public static final String ITEMPLURAL = 	"_PLURAL";
	public static final String ITEMDESC = 		"_DESC";
	public static final String ITEMWEIGHT = 	"_WEIGHT";

	protected VerbList verbs = new VerbList();
	
	public Item(){
		setAttribute(ITEMNAME, "Item");
		setAttribute(ITEMPLURAL, "Items");
		setAttribute(ITEMDESC, "Description");
		setAttribute(ITEMWEIGHT, 1.0);
	}
	
	public ArrayList<String> sGetVerbList(){
		return verbs.getVerbs();
	}
	
	public String getName() {
		return getAttribute(ITEMNAME).sGetValue();
	}
	
	public void setName(String name) {
		setAttribute(ITEMNAME, name);
	}
	
	public String getPlural() {
		return getAttribute(ITEMPLURAL).sGetValue();
	}
	
	public void setPlural(String plural) {
		setAttribute(ITEMPLURAL, plural);
	}
	
	public String getDescription() {
		return getAttribute(ITEMDESC).sGetValue();
	}
	
	public void setDescription(String description) {
		setAttribute(ITEMDESC, description);
	}
	
	public double getWeight() {
		return getAttribute(ITEMWEIGHT).dGetValue();
	}
	
	public void setWeight(double weight) {
		setAttribute(ITEMWEIGHT, weight);
	}
	
	public boolean addVerb(Verb verb){
		verbs.addVerb(verb);
		return true;
	}
	
	public Verb getVerb(String verb){
		return verbs.getVerb(verb);
	}

	public boolean removeVerb(){
		//TODO: remove verbs from items
		return false;
	}
	
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------Scripted Stuff----------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	
	private AttributeList 	attributes 	= new AttributeList();
	private ScriptList		scripts 	= new ScriptList();

	public boolean setAttribute(String name, int i) {
		attributes.setAttribute(name, i);
		return true;
	}

	public boolean setAttribute(String name, double d) {
		attributes.setAttribute(name, d);
		return true;
	}

	public boolean setAttribute(String name, String str) {
		attributes.setAttribute(name, str);
		return true;
	}

	public boolean setAttribute(String name, boolean b) {
		attributes.setAttribute(name, b);
		return true;
	}

	public Attribute getAttribute(String name) {
		return attributes.getByName(name);
	}
	
	public ArrayList<Attribute> getAttributeList(){
		return attributes.getAttributeList();
	}

	public boolean addScript(HeckScript script) {
		return scripts.addScript(script);
	}

	public boolean removeScript(String name) {
		return scripts.removeScript(name);
	}

	public HeckScript getScript(String name) {
		return scripts.getScript(name);
	}

	public boolean scriptExists(String name) {
		return scripts.scriptExists(name);
	}

	public boolean isScriptCompiled(String name) {
		return scripts.isScriptCompiled(name);
	}
	
	public ArrayList<String> getScriptNames() {
		return scripts.getScriptNames();
	}
	
}
