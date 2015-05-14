package com.bicrement.jaml.tag;

import java.util.Collections;
import java.util.List;

import com.bicrement.jaml.cache.PreparedTag;
import com.bicrement.jaml.cache.PreparedTag.Builder;

/**
 * Represents a generic tag.
 * 
 * @author zhuochun
 *
 */
public class BaseTag implements Tag {

	private final String name;
	private final List<Attribute> attributes;
	private final List<Tag> childElems;

	public BaseTag(String name, List<Attribute> attrs) {
		this(name, attrs, Collections.emptyList());
	}

	public BaseTag(String name, List<Attribute> attrs, List<Tag> childElems) {
		this.name = name;
		this.attributes = attrs;
		this.childElems = childElems;
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
	public BaseText getHtml() {
		return prepare().bind().getHtml();
	}

	@Override
	public PreparedTag prepare() {
		return prepare(new Builder(this)).build();
	}

	@Override
	public Builder prepare(Builder builder) {
		builder.add("<", name);

		attributes.forEach(attr -> attr.prepare(builder.add(" ")));

		if (isSelfClosed()) {
			return builder.add("/>");
		}
		builder.add(">");

		childElems.forEach(e -> e.prepare(builder));

		return builder.add("</", name, ">");
	}

	@Override
	public String toString() {
		return prepare().toString();
	}

	@Override
	public StringBuilder toString(StringBuilder sb) {
		return prepare().toString(sb);
	}

}
