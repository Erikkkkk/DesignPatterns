package nl.hanze.designpatterns.db;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.*;

public class RowIterator<T> implements Iterator<T> {
	Class<T> c;
	Method[] classMethods;

	ResultSet res;
	
	protected RowIterator(Class<T> c, ResultSet res) {
		this.c = c;
		this.res = res;
	}
	
	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public T next() {
		//if(method.getName.startsWith("set" + ))
		return null;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
