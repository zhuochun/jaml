package com.bicrement.jaml.tag;

import com.bicrement.jaml.content.BindMarker;
import com.bicrement.jaml.content.PreparedDom;
import com.bicrement.jaml.content.PreparedDom.Builder;

public class Attribute {

	private final String name;
	private final Object value;

	public Attribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public Attribute(String name, BindMarker marker) {
		this.name = name;
		this.value = marker;
	}

	public String name() {
		return name;
	}

	public Object value() {
		return value;
	}
	
	public String getContent() {
		return getContent(new StringBuilder()).toString();
	}
	
	public StringBuilder getContent(StringBuilder sb) {
		return sb.append(name).append("=\"").append(value).append("\"");
	}
	
	PreparedDom prepare() {
		PreparedDom.Builder builder = new Builder();
		builder.add(name, "=\"", value, "\"");
		return builder.build();
	}

	@Override
	public String toString() {
		return getContent();
	}

}
