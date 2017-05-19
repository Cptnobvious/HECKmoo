package script.attributes;

import java.util.ArrayList;

public interface AttributeInterface {
	
	public abstract boolean 	setAttribute(String name, int i);
	public abstract boolean 	setAttribute(String name, double d);
	public abstract boolean 	setAttribute(String name, String str);
	public abstract boolean 	setAttribute(String name, boolean b);
	public abstract Attribute	getAttribute(String name);
	public abstract ArrayList<Attribute> getAttributeList();

}
