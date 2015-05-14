package com.bicrement.jaml.tag;

import java.util.Collections;
import java.util.List;

import com.bicrement.jaml.cache.PreparedTag;
import com.bicrement.jaml.cache.PreparedTag.Builder;

public class BindMarker implements Tag, Text {

	private final String name;
	private boolean htmlSafe;

	public BindMarker() {
		this("_");
	}

	public BindMarker(String name) {
		this.name = name;
		this.htmlSafe = false;
	}

	public String getName() {
		return name;
	}

	public BindMarker htmlSafe() {
		this.htmlSafe = true;
		return this;
	}

	public boolean isHtmlSafe() {
		return htmlSafe;
	}

	@Override
	public List<Attribute> getAttributes() {
		return Collections.emptyList();
	}

	@Override
	public List<Tag> getChildElements() {
		return Collections.emptyList();
	}

	/**
	 * BindMarker cannot be converted to HTML.
	 */
	@Override
	public BaseText getHtml() {
		throw new IllegalStateException("BindMarker cannot convert to HTML.");
	}

	@Override
	public PreparedTag prepare() {
		return prepare(new Builder(this)).build();
	}

	@Override
	public Builder prepare(Builder builder) {
		return builder.add(this);
	}

	@Override
	public String toString() {
		return toString(new StringBuilder()).toString();
	}

	@Override
	public StringBuilder toString(StringBuilder sb) {
		String c = htmlSafe ? "_" : "$";
		return sb.append(c).append(name).append(c);
	}

}
