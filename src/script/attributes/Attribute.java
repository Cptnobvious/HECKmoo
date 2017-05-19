package script.attributes;

import java.util.ArrayList;

public class Attribute {

	private String attName = null;
	private int i = 0;
	private double d = 0;
	private String str = "";
	private Boolean b = false;
	private ArrayList<Attribute> list = new ArrayList<Attribute>();
	
	public Attribute(String name){
		this.attName = name;
	}
	
	public Attribute(String name, int i){
		this.attName = name;
		iSetValue(i);
	}
	
	public Attribute(String name, double d){
		this.attName = name;
		dSetValue(d);
	}
	
	public Attribute(String name, String s){
		this.attName = name;
		sSetValue(s);
	}
	
	public Attribute(String name, boolean b){
		this.attName = name;
		bSetValue(b);
	}
	
	public Attribute(String name, ArrayList<Attribute> atts){
		this.attName = name;
		lSetValue(atts);
	}
	
	public boolean match(String name){
		return attName.equals(name);
	}
	
	public int iGetValue(){
		return this.i;
	}
	
	public double dGetValue(){
		return this.d;
	}
	
	public String sGetValue(){
		return this.str;
	}
	
	public Boolean bGetValue(){
		return this.b;
	}
	
	public ArrayList<Attribute> lgetValue(){
		return this.list;
	}
	
	public boolean iSetValue(int i){
		this.i = i;
		this.d = (double)i;
		this.str = String.valueOf(i);
		this.b = (i != 0);
		return true;
	}
	
	public boolean dSetValue(double d){
		this.d = d;
		this.i = Math.round((float)d);
		this.str = String.valueOf(d);
		this.b = (d != 0);
		return true;
	}
	
	public boolean sSetValue(String str){
		this.str = str;
		try {
			this.i = Integer.parseInt(str);
		} catch (NumberFormatException e){
			this.i = 0;
		}
		try {
			this.d = Double.parseDouble(str);
		} catch (NumberFormatException e){
			this.d = 0;
		}
		this.b = (!str.equals(""));
		return true;
	}
	
	public boolean bSetValue(boolean b){
		this.b = b;
		if (b){
			this.i = 1;
			this.d = 1;
			this.str = "true";
		} else {
			this.i = 0;
			this.d = 0;
			this.str = "false";
		}
		return true;
	}
	
	public boolean lSetValue(ArrayList<Attribute> list){
		this.list = list;
		return true;
	}
	
}
