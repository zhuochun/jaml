package com.bicrement.jaml.cache;

import java.util.List;

import com.bicrement.jaml.cache.PreparedTag.Builder;
import com.bicrement.jaml.tag.Attribute;
import com.bicrement.jaml.tag.BindMarker;
import com.bicrement.jaml.tag.BaseText;
import com.bicrement.jaml.tag.Tag;
import com.bicrement.jaml.tool.Sanitize;

/**
 * A complete tag that has all its values binded.
 * 
 * @author zhuochun
 *
 */
public class BoundedTag implements Tag {

	private final PreparedTag preparedTag;
	private final List<Object> values;

	BoundedTag(PreparedTag preparedTag, List<Object> values) {
		this.preparedTag = preparedTag;
		this.values = values;
	}

	@Override
	public String getName() {
		return preparedTag.getName();
	}

	@Override
	public List<Attribute> getAttributes() {
		return preparedTag.getAttributes();
	}

	@Override
	public List<Tag> getChildElements() {
		return preparedTag.getChildElements();
	}

	@Override
	public PreparedTag prepare() {
		return prepare(new Builder(preparedTag.getRawTag())).build();
	}

	@Override
	public Builder prepare(Builder builder) {
		return builder.add(toString());
	}

	@Override
	public BaseText getHtml() {
		return new BaseText(toString()).htmlSafe();
	}

	@Override
	public String toString() {
		return toString(new StringBuilder()).toString();
	}
	
	@Override
	public StringBuilder toString(StringBuilder sb) {
		int valueIdx = 0;
		
		for (Object partial : preparedTag.getPreparedPartials()) {
			if (partial instanceof BindMarker) {
				final BindMarker marker = (BindMarker) partial;
				final Object value = values.get(valueIdx);
				
				if (value == null) {
					sb.append("");
				} else if (marker.isHtmlSafe()) {
					sb.append(value);
				} else {
					sb.append(Sanitize.sanitizeText(value));
				}
				
				valueIdx += 1;
			} else {
				sb.append(partial.toString());
			}
		}

		return sb;
	}


}
