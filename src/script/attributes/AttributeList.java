package script.attributes;

import java.util.ArrayList;

public class AttributeList {

	private static final Attribute DEFAULTATTRIBUTE = new Attribute("_DEFAULT", false);
	private ArrayList<Attribute> attributes = new ArrayList<Attribute>();
	
	public boolean setAttribute(String name, int i){
		if (checkByName(name)){
			getByName(name).iSetValue(i);
			return true;
		} else {
			Attribute att = new Attribute(name, i);
			attributes.add(att);
			return true;
		}
	}
	
	public boolean setAttribute(String name, double d){
		if (checkByName(name)){
			getByName(name).dSetValue(d);
			return true;
		} else {
			Attribute att = new Attribute(name, d);
			attributes.add(att);
			return true;
		}
	}
	
	public boolean setAttribute(String name, String str){
		if (checkByName(name)){
			getByName(name).sSetValue(str);
			return true;
		} else {
			Attribute att = new Attribute(name, str);
			attributes.add(att);
			return true;
		}
	}
	
	public boolean setAttribute(String name, boolean b){
		if (checkByName(name)){
			getByName(name).bSetValue(b);
			return true;
		} else {
			Attribute att = new Attribute(name, b);
			attributes.add(att);
			return true;
		}
	}
	
	public boolean attributeExists(String name){
		for (int i = 0; i < attributes.size(); i++){
			if (attributes.get(i).match(name)){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkByName(String name){
		for (int i = 0; i < attributes.size(); i++){
			if (attributes.get(i).match(name)){
				return true;
			}
		}
		return false;
	}
	
	public Attribute getByName(String name){
		for (int i = 0; i < attributes.size(); i++){
			if (attributes.get(i).match(name)){
				return attributes.get(i);
			}
		}
		return DEFAULTATTRIBUTE;
	}
	
	public ArrayList<Attribute> getAttributeList(){
		return attributes;
	}
}
