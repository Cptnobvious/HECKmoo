package script.attributes;

public interface AttributeInterface {
	
	public abstract boolean 	setAttribute(String name, int i);
	public abstract boolean 	setAttribute(String name, double d);
	public abstract boolean 	setAttribute(String name, String str);
	public abstract boolean 	setAttribute(String name, boolean b);
	public abstract Attribute	getAttribute(String name);

}
