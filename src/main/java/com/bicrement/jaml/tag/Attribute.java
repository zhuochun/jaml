package com.bicrement.jaml.tag;

import com.bicrement.jaml.cache.PreparedTag;

/**
 * Represent an attribute
 * 
 * @author zhuochun
 *
 */
public class Attribute {

	private final String name;
	private final Text value;

	public Attribute(String name, String value) {
		this.name = name;
		this.value = new BaseText(value);
	}

	public Attribute(String name, Text value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public Attribute htmlSafe() {
		value.htmlSafe();
		return this;
	}

	public boolean isHtmlSafe() {
		return value.isHtmlSafe();
	}

	public String getContent() {
		return getContent(new StringBuilder()).toString();
	}

	public StringBuilder getContent(StringBuilder sb) {
		return sb.append(name).append("=\"").append(value).append("\"");
	}

	@Override
	public String toString() {
		return getContent();
	}

	/**
	 * For internal usages
	 * 
	 * @param builder
	 * @return
	 */
	public PreparedTag.Builder prepare(PreparedTag.Builder builder) {
		return builder.add(name, "=\"", value, "\"");
	}

}
