package nl.hanze.designpatterns.domain;

import java.util.ArrayList;

public class Question implements Thesaurus, java.io.Serializable {
	private String title;
	private String description;
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Thesaurus> childs;
	
	public Question() {

	}
	
	public Question(String title, String description) {
		this.title = title;
		this.description = description;
		childs = new ArrayList<Thesaurus>();
	} 
	
	public Question(ArrayList<Thesaurus> childs) {
		this.childs = childs;
	}
	
	public void add(Thesaurus thesaurus) {
		childs.add(thesaurus);
	}
	
	public void remove(Thesaurus thesaurus) {
		childs.remove(thesaurus);
	}
	
	public ArrayList<Thesaurus> getChilds() {
		return childs;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean hasChilds() {
		return childs.isEmpty();
	}
}
