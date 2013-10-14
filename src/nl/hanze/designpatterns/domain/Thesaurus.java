package nl.hanze.designpatterns.domain;

import java.util.ArrayList;

public interface Thesaurus {
	public String getDescription();
	public ArrayList<Thesaurus> getChilds();
	public boolean hasChilds();
	public void add(Thesaurus thesaurus);
}
