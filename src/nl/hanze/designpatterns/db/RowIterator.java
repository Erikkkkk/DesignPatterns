package nl.hanze.designpatterns.db;

import java.util.*;

public class RowIterator<T> implements Iterator<T> {
	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public T next() {
		return null;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
