package com.bicrement.jaml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.bicrement.jaml.tag.Attribute;
import com.bicrement.jaml.tag.Text;

/**
 * Shortcuts to create {@link Attribute}s.
 * 
 * @author zhuochun
 *
 */
public final class Attributes {

	/**
	 * Create a list of attributes
	 *
	 * @param attributes
	 * @return a list of {@link Attribute}
	 */
	public static List<Attribute> $$(Attribute... attributes) {
		if (attributes.length == 0) {
			return Collections.emptyList();
		}

		return Arrays.asList(attributes);
	}

	/**
	 * Create an attribute that take in a {@code name="value"}
	 * 
	 * @param name
	 * @param value
	 * @return {@link Attribute}
	 */
	public static Attribute attr(String name, Object value) {
		if (value instanceof Text) {
			return new Attribute(name, (Text) value);
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

	public static Attribute src(Object value) {
		return attr("src", value);
	}

	public static Attribute name(Object value) {
		return attr("name", value);
	}

}
