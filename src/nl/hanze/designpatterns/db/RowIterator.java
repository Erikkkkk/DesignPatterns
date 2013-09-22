package nl.hanze.designpatterns.db;

import java.util.*;

public class RowIterator<T> implements Iterator<T> {
	
	protected RowIterator() {
		
	}
	
	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public T next() {
		//sorteer type
		return null;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
