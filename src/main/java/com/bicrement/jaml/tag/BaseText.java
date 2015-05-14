package com.bicrement.jaml.tag;

import java.util.Collections;
import java.util.List;

import com.bicrement.jaml.cache.PreparedTag;
import com.bicrement.jaml.cache.PreparedTag.Builder;
import com.bicrement.jaml.tool.Sanitize;

public class BaseText implements Tag, Text {

	private final String content;
	private boolean htmlSafe;

	public BaseText(String content) {
		this.content = content;
		this.htmlSafe = false;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public BaseText htmlSafe() {
		this.htmlSafe = true;
		return this;
	}

	@Override
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

	@Override
	public BaseText getHtml() {
		return this;
	}

	@Override
	public PreparedTag prepare() {
		return prepare(new Builder(this)).build();
	}

	@Override
	public Builder prepare(Builder builder) {
		return builder.add(toString());
	}

	@Override
	public String toString() {
		if (htmlSafe) {
			return content;
		}

		return Sanitize.sanitizeText(content);
	}

	@Override
	public StringBuilder toString(StringBuilder sb) {
		return sb.append(toString());
	}

}
