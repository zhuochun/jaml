package com.bicrement.jaml;

import java.util.Arrays;
import java.util.List;

import com.bicrement.jaml.content.BindMarker;
import com.bicrement.jaml.tag.Attribute;

public final class Attributes {
	
    /**
     * Create a list of attributes
     *
     * @param attributes
     * @return
     */
    public static List<Attribute> $$(Attribute... attributes) {
        return Arrays.asList(attributes);
    }

    /**
     * General attribute that take in a {@code name="value"}
     * 
     * @param name
     * @param value
     * @return {@link Attribute}
     */
    public static Attribute attr(String name, Object value) {
    	if (value instanceof BindMarker) {
    		return new Attribute(name, (BindMarker) value);
    	}

        return new Attribute(name, value.toString());
    }

    public static Attribute id(Object value) {
    	return attr("id", value);
    }

    public static Attribute klass(Object value) {
    	return attr("class", value);
    }
    
    public static Attribute style(Object value) {
    	return attr("style", value);
    }

    public static Attribute href(Object value) {
    	return attr("href", value);
    }

    public static Attribute name(Object value) {
    	return attr("name", value);
    }

}
