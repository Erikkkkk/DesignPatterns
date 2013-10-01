package nl.hanze.designpatterns.db;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

//http://alvinalexander.com/java/jwarehouse/commons-dbutils-1.0/src/java/org/apache/commons/dbutils/BasicRowProcessor.java.shtml
public class RowIterator<T> implements Iterator<T> {
	Class<T> c;
	Method[] classMethods;

	ResultSet res;
	
	 private static final int PROPERTY_NOT_FOUND = -1;
	
	/**
     * Set a bean's primitive properties to these defaults when SQL NULL 
     * is returned.  These are the same as the defaults that ResultSet get* 
     * methods return in the event of a NULL column.
     */
    private static final Map primitiveDefaults = new HashMap();

    static {
        primitiveDefaults.put(Integer.TYPE, new Integer(0));
        primitiveDefaults.put(Short.TYPE, new Short((short) 0));
        primitiveDefaults.put(Byte.TYPE, new Byte((byte) 0));
        primitiveDefaults.put(Float.TYPE, new Float(0));
        primitiveDefaults.put(Double.TYPE, new Double(0));
        primitiveDefaults.put(Long.TYPE, new Long(0));
        primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
        primitiveDefaults.put(Character.TYPE, new Character('\u0000'));
    }
	
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
	
	/**
     * Convert a ResultSet row into an Object[].
     * This implementation copies column values into the array in the same 
     * order they're returned from the ResultSet.  Array elements
     * will be set to null if the column was SQL NULL.
     *
     * @see org.apache.commons.dbutils.RowProcessor#toArray(java.sql.ResultSet)
     */
    public Object[] toArray(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();
        Object[] result = new Object[cols];

        for (int i = 0; i < cols; i++) {
            result[i] = rs.getObject(i + 1);
        }

        return result;
    }

    /**
     * Convert a ResultSet row into a JavaBean.  This 
     * implementation uses reflection and BeanInfo classes to 
     * match column names to bean property names.  Properties are matched to 
     * columns based on several factors:
     * 

     * 

    *
    * The class has a writable property with the same name as a column. * The name comparison is case insensitive. *
    * *
    * The property's set method parameter type matches the column * type. If the data types do not match, the setter will not be called. *
    * 


     * 
     * 

* Primitive bean properties are set to their defaults when SQL NULL is * returned from the ResultSet. Numeric fields are set to 0 * and booleans are set to false. Object bean properties are set to * null when SQL NULL is returned. This is the same behavior * as the ResultSet get* methods. *

     * 
     * @see org.apache.commons.dbutils.RowProcessor#toBean(java.sql.ResultSet, java.lang.Class)
     */
    public Object toBean(ResultSet rs, Class type) throws SQLException {

        PropertyDescriptor[] props = this.propertyDescriptors(type);

        ResultSetMetaData rsmd = rs.getMetaData();

        int[] columnToProperty = this.mapColumnsToProperties(rsmd, props);

        int cols = rsmd.getColumnCount();

        return this.createBean(rs, type, props, columnToProperty, cols);
    }

    /**
     * Convert a ResultSet into a List of JavaBeans.  
     * This implementation uses reflection and BeanInfo classes to 
     * match column names to bean property names. Properties are matched to 
     * columns based on several factors:
     * 

     * 

    *
    * The class has a writable property with the same name as a column. * The name comparison is case insensitive. *
    * *
    * The property's set method parameter type matches the column * type. If the data types do not match, the setter will not be called. *
    * 


     * 
     * 

* Primitive bean properties are set to their defaults when SQL NULL is * returned from the ResultSet. Numeric fields are set to 0 * and booleans are set to false. Object bean properties are set to * null when SQL NULL is returned. This is the same behavior * as the ResultSet get* methods. *

     * 
     * @see org.apache.commons.dbutils.RowProcessor#toBeanList(java.sql.ResultSet, java.lang.Class)
     */
    public List toBeanList(ResultSet rs, Class type) throws SQLException {
        List results = new ArrayList();

        if (!rs.next()) {
            return results;
        }

        PropertyDescriptor[] props = this.propertyDescriptors(type);
        ResultSetMetaData rsmd = rs.getMetaData();
        int[] columnToProperty = this.mapColumnsToProperties(rsmd, props);
        int cols = rsmd.getColumnCount();

        do {
            results.add(this.createBean(rs, type, props, columnToProperty, cols));

        } while (rs.next());

        return results;
    }

    /**
     * Creates a new object and initializes its fields from the ResultSet.
     *
     * @param rs The result set
     * @param type The bean type (the return type of the object)
     * @param props The property descriptors
     * @param columnToProperty The column indices in the result set
     * @param cols The number of columns
     * @return An initialized object.
     * @throws SQLException If a database error occurs
     */
    private Object createBean(
        ResultSet rs,
        Class type,
        PropertyDescriptor[] props,
        int[] columnToProperty,
        int cols)
        throws SQLException {

        Object bean = this.newInstance(type);

        for (int i = 1; i <= cols; i++) {

            if (columnToProperty[i] == PROPERTY_NOT_FOUND) {
                continue;
            }
            
            Object value = rs.getObject(i);

            PropertyDescriptor prop = props[columnToProperty[i]];
            Class propType = prop.getPropertyType();

            if (propType != null && value == null && propType.isPrimitive()) {
                value = primitiveDefaults.get(propType);
            }

            this.callSetter(bean, prop, value);
        }

        return bean;
    }

    /**
     * The positions in the returned array represent column numbers.  The values
     * stored at each position represent the index in the PropertyDescriptor[] 
     * for the bean property that matches the column name.  If no bean property
     * was found for a column, the position is set to PROPERTY_NOT_FOUND.
     * 
     * @param rsmd The result set meta data containing column information
     * @param props The bean property descriptors
     * @return An int[] with column index to property index mappings.  The 0th 
     * element is meaningless as column indexing starts at 1.
     * 
     * @throws SQLException If a database error occurs
     */
    private int[] mapColumnsToProperties(
        ResultSetMetaData rsmd,
        PropertyDescriptor[] props)
        throws SQLException {

        int cols = rsmd.getColumnCount();
        int columnToProperty[] = new int[cols + 1];

        for (int col = 1; col <= cols; col++) {
            String columnName = rsmd.getColumnName(col);
            for (int i = 0; i < props.length; i++) {

                if (columnName.equalsIgnoreCase(props[i].getName())) {
                    columnToProperty[col] = i;
                    break;

                } else {
                    columnToProperty[col] = PROPERTY_NOT_FOUND;
                }
            }
        }

        return columnToProperty;
    }

    /**
     * Convert a ResultSet row into a Map.  This 
     * implementation returns a Map with case insensitive column
     * names as keys.  Calls to map.get("COL") and 
     * map.get("col") return the same value.
     * @see org.apache.commons.dbutils.RowProcessor#toMap(java.sql.ResultSet)
     */
    public Map toMap(ResultSet rs) throws SQLException {
        Map result = new CaseInsensitiveHashMap();
        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();

        for (int i = 1; i <= cols; i++) {
            result.put(rsmd.getColumnName(i), rs.getObject(i));
        }

        return result;
    }

    /**
     * Calls the setter method on the target object for the given property.
     * If no setter method exists for the property, this method does nothing.
     * @param target The object to set the property on.
     * @param prop The property to set.
     * @param value The value to pass into the setter.
     * @throws SQLException if an error occurs setting the property.
     */
    private void callSetter(
        Object target,
        PropertyDescriptor prop,
        Object value)
        throws SQLException {

        Method setter = prop.getWriteMethod();

        if (setter == null) {
            return;
        }

        Class[] params = setter.getParameterTypes();
        try {
            // Don't call setter if the value object isn't the right type 
            if (this.isCompatibleType(value, params[0])) {
                setter.invoke(target, new Object[] { value });
            }

        } catch (IllegalArgumentException e) {
            throw new SQLException(
                "Cannot set " + prop.getName() + ": " + e.getMessage());

        } catch (IllegalAccessException e) {
            throw new SQLException(
                "Cannot set " + prop.getName() + ": " + e.getMessage());

        } catch (InvocationTargetException e) {
            throw new SQLException(
                "Cannot set " + prop.getName() + ": " + e.getMessage());
        }
    }

    /**
     * ResultSet.getObject() returns an Integer object for an INT column.  The
     * setter method for the property might take an Integer or a primitive int.
     * This method returns true if the value can be successfully passed into
     * the setter method.  Remember, Method.invoke() handles the unwrapping
     * of Integer into an int.
     * 
     * @param value The value to be passed into the setter method.
     * @param type The setter's parameter type.
     * @return boolean True if the value is compatible.
     */
    private boolean isCompatibleType(Object value, Class type) {
        // Do object check first, then primitives
        if (value == null || type.isInstance(value)) {
            return true;

        } else if (
            type.equals(Integer.TYPE) && Integer.class.isInstance(value)) {
            return true;

        } else if (type.equals(Long.TYPE) && Long.class.isInstance(value)) {
            return true;

        } else if (
            type.equals(Double.TYPE) && Double.class.isInstance(value)) {
            return true;

        } else if (type.equals(Float.TYPE) && Float.class.isInstance(value)) {
            return true;

        } else if (type.equals(Short.TYPE) && Short.class.isInstance(value)) {
            return true;

        } else if (type.equals(Byte.TYPE) && Byte.class.isInstance(value)) {
            return true;

        } else if (
            type.equals(Character.TYPE) && Character.class.isInstance(value)) {
            return true;

        } else if (
            type.equals(Boolean.TYPE) && Boolean.class.isInstance(value)) {
            return true;

        } else {
            return false;
        }

    }

    /**
     * Returns a new instance of the given Class.
     *
     * @param c The Class to create an object from.
     * @return A newly created object of the Class.
     * @throws SQLException if creation failed.
     */
    private Object newInstance(Class c) throws SQLException {
        try {
            return c.newInstance();

        } catch (InstantiationException e) {
            throw new SQLException(
                "Cannot create " + c.getName() + ": " + e.getMessage());

        } catch (IllegalAccessException e) {
            throw new SQLException(
                "Cannot create " + c.getName() + ": " + e.getMessage());
        }
    }

    /**
     * Returns a PropertyDescriptor[] for the given Class.
     *
     * @param c The Class to retrieve PropertyDescriptors for.
     * @return A PropertyDescriptor[] describing the Class.
     * @throws SQLException if introspection failed.
     */
    private PropertyDescriptor[] propertyDescriptors(Class c)
        throws SQLException {
        // Introspector caches BeanInfo classes for better performance
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(c);

        } catch (IntrospectionException e) {
            throw new SQLException(
                "Bean introspection failed: " + e.getMessage());
        }

        return beanInfo.getPropertyDescriptors();
    }
    
    /**
     * A Map that converts all keys to lowercase Strings for case insensitive
     * lookups.  This is needed for the toMap() implementation because 
     * databases don't consistenly handle the casing of column names. 
     */
    private static class CaseInsensitiveHashMap extends HashMap {

        /**
         * @see java.util.Map#containsKey(java.lang.Object)
         */
        public boolean containsKey(Object key) {
            return super.containsKey(key.toString().toLowerCase());
        }

        /**
         * @see java.util.Map#get(java.lang.Object)
         */
        public Object get(Object key) {
            return super.get(key.toString().toLowerCase());
        }

        /**
         * @see java.util.Map#put(java.lang.Object, java.lang.Object)
         */
        public Object put(Object key, Object value) {
            return super.put(key.toString().toLowerCase(), value);
        }

        /**
         * @see java.util.Map#putAll(java.util.Map)
         */
        public void putAll(Map m) {
            Iterator iter = m.keySet().iterator();
            while (iter.hasNext()) {
                Object key = iter.next();
                Object value = m.get(key);
                this.put(key, value);
            }
        }

        /**
         * @see java.util.Map#remove(java.lang.ObjecT)
         */
        public Object remove(Object key) {
            return super.remove(key.toString().toLowerCase());
        }
    }
}
