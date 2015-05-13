package com.bicrement.jaml.content;

import java.util.Collections;
import java.util.List;

import com.bicrement.jaml.tag.Attribute;
import com.bicrement.jaml.tag.Tag;
import com.bicrement.jaml.tag.Text;

public class BindMarker implements Tag {
	
	private final String name;
	private boolean isHtmlSafe;
	
	public BindMarker() {
		this("_");
	}
	
	public BindMarker(String name) {
		this.name = name;
		this.isHtmlSafe = false;
	}
	
	public String getName() {
		return name;
	}
	
	public BindMarker htmlSafe() {
		this.isHtmlSafe = true;
		return this;
	}
	
	public boolean isHtmlSafe() {
		return isHtmlSafe;
	}

	@Override
	public List<Attribute> getAttributes() {
		return Collections.emptyList();
	}

	@Override
	public List<Tag> getChildElements() {
		return Collections.emptyList();
	}

	@Override
	public String getContent() {
		return getContent(new StringBuilder()).toString();
	}

	@Override
	public StringBuilder getContent(StringBuilder sb) {
		String c = isHtmlSafe ? "_" : "$";
		return sb.append(c).append(name).append(c);
	}

	@Override
	public PreparedDom prepare() {
		return new PreparedDom.Builder().add(this).build();
	}

	@Override
	public String toString() {
		return getContent();
	}

	@Override
	public Text persist() {
		return new Text("");
	}

}
