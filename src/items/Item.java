package items;

import verb.Verb;
import verb.VerbList;

public class Item {
	
	protected String name = "Item";
	protected String plural = "Items";
	protected String description = "Description";
	protected double weight = 1.0;
	protected VerbList verbs = new VerbList();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPlural() {
		return plural;
	}
	
	public void setPlural(String plural) {
		this.plural = plural;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public boolean addVerb(Verb verb){
		verbs.addVerb(verb);
		return true;
	}

	public boolean removeVerb(){
		//TODO: remove verbs from items
		return false;
	}
}
