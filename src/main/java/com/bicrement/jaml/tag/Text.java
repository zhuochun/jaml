package com.bicrement.jaml.tag;

import java.util.Collections;
import java.util.List;

import com.bicrement.jaml.Sanitize;
import com.bicrement.jaml.content.PreparedDom;
import com.bicrement.jaml.content.PreparedDom.Builder;

public class Text implements Tag {

	private final String content;
	private boolean isSafe;

	public Text(String content) {
		this.content = content;
		this.isSafe = false;
	}
	
	public Text htmlSafe() {
		this.isSafe = true;
		return this;
	}
	
	public boolean isHtmlSafe() {
		return isSafe;
	}

	@Override
	public String getName() {
		return "";
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
		if (isSafe) {
			return content;
		}

		return Sanitize.sanitizeText(content);
	}

	@Override
	public StringBuilder getContent(StringBuilder sb) {
		return sb.append(getContent());
	}

	@Override
	public PreparedDom prepare() {
		PreparedDom.Builder builder = new Builder();
		builder.add(getContent());
		return builder.build();
	}

	@Override
	public Text persist() {
		return this;
	}

}
