package com.bicrement.jaml.tag;

import java.util.Collections;
import java.util.List;

import com.bicrement.jaml.content.PreparedDom;
import com.bicrement.jaml.content.PreparedDom.Builder;

public class BaseTag implements Tag {

	private final String name;
	private final List<Attribute> attributes;
	private final List<Tag> childElems;

	public BaseTag(String name, List<Attribute> attrs, List<Tag> childElems) {
		this.name = name;
		this.attributes = attrs;
		this.childElems = childElems;
	}

	public BaseTag(String name, List<Attribute> attrs) {
		this.name = name;
		this.attributes = attrs;
		this.childElems = Collections.emptyList();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Attribute> getAttributes() {
		return attributes;
	}

	@Override
	public List<Tag> getChildElements() {
		return childElems;
	}
	
	private boolean isSelfClosed() {
		return childElems.isEmpty();
	}

	@Override
	public String getContent() {
		return getContent(new StringBuilder()).toString();
	}

	@Override
	public String toString() {
		return getContent();
	}

	@Override
	public StringBuilder getContent(StringBuilder sb) {
		sb.append("<").append(name);

		attributes.forEach(attr -> sb.append(" ").append(attr));

		if (isSelfClosed()) {
			return sb.append("/>");
		}
		sb.append(">");

		childElems.forEach(e -> e.getContent(sb));

		return sb.append("</").append(name).append(">");
	}

	@Override
	public PreparedDom prepare() {
		PreparedDom.Builder builder = new Builder();
		
		builder.add("<", name);

		attributes.forEach(attr -> builder.add(" ").add(attr.prepare()));

		if (isSelfClosed()) {
			return builder.add("/>").build();
		}
		builder.add(">");
		
		childElems.forEach(e -> builder.add(e.prepare()));

		return builder.add("</", name, ">").build();
	}
	
	@Override
	public Text persist() {
		return prepare().bind().toHtml();
	}

}
