package com.bicrement.jaml.cache;

import static com.google.common.base.Preconditions.checkState;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.bicrement.jaml.tag.Attribute;
import com.bicrement.jaml.tag.BaseText;
import com.bicrement.jaml.tag.BindMarker;
import com.bicrement.jaml.tag.Tag;

/**
 * An intermediate tag that contains BindMarkers.
 * 
 * @author zhuochun
 *
 */
public class PreparedTag implements Tag {

	// the original tag
	private final Tag rawTag;
	// partials can only be String or BindMarker
	private final List<Object> partials;
	// a copy of BindMarkers for faster access
	private final List<BindMarker> markers;

	PreparedTag(Tag tag, List<Object> partials, List<BindMarker> markers) {
		this.rawTag = tag;
		this.partials = partials;
		this.markers = markers;
	}

	public BoundedTag bind(Object... values) {
		checkState(markers.size() == values.length);
		return new BoundedTag(this, Arrays.asList(values));
	}

	public BoundedTag bind(Map<String, Object> valueMap) {
		checkState(markers.size() == valueMap.size());

		List<Object> values = new LinkedList<>();
		for (BindMarker marker : markers) {
			values.add(valueMap.get(marker.getName()));
		}

		return new BoundedTag(this, values);
	}

	public Tag getRawTag() {
		return rawTag;
	}

	List<Object> getPreparedPartials() {
		return partials;
	}

	List<BindMarker> getBindMarkers() {
		return markers;
	}

	@Override
	public String getName() {
		return rawTag.getName();
	}

	@Override
	public List<Attribute> getAttributes() {
		return rawTag.getAttributes();
	}

	@Override
	public List<Tag> getChildElements() {
		return rawTag.getChildElements();
	}

	@Override
	public BaseText getHtml() {
		return bind().getHtml();
	}

	@Override
	public PreparedTag prepare() {
		return this;
	}

	@Override
	public Builder prepare(Builder builder) {
		return builder.add(this);
	}

	@Override
	public String toString() {
		return bind(markers.toArray()).toString();
	}

	@Override
	public StringBuilder toString(StringBuilder sb) {
		return bind(markers.toArray()).toString(sb);
	}

	public static class Builder {
		private StringBuilder stringBuilder;

		private final Tag rawTag;
		private final List<Object> partials;
		private final List<BindMarker> markers;

		public Builder(Tag tag) {
			this.stringBuilder = new StringBuilder();
			this.rawTag = tag;
			this.partials = new LinkedList<>();
			this.markers = new LinkedList<>();
		}

		public Builder add(String... partials) {
			for (String partial : partials) {
				stringBuilder.append(partial);
			}

			return this;
		}

		public Builder add(BindMarker marker) {
			flushStringBuilder();

			partials.add(marker);
			markers.add(marker);

			return this;
		}

		/**
		 * Concatenate the preparedDom to this builder
		 * 
		 * @param preparedTag
		 * @return this {@link PreparedTag.Builder}
		 */
		public Builder add(PreparedTag preparedTag) {
			return add(preparedTag.getPreparedPartials().toArray());
		}

		/**
		 * Partials can be type of {@link String} or {@link BindMarker}
		 * 
		 * @param partials
		 * @return this {@link PreparedTag.Builder}
		 */
		public Builder add(Object... partials) {
			for (Object partial : partials) {
				if (partial instanceof BindMarker) {
					add((BindMarker) partial);
				} else {
					add(partial.toString());
				}
			}

			return this;
		}

		/**
		 * Flush the text in StringBuilder to partial
		 */
		private void flushStringBuilder() {
			if (stringBuilder.length() > 0) {
				partials.add(stringBuilder.toString());
				stringBuilder = new StringBuilder();
			}
		}

		/**
		 * Get the prepared tag.
		 * 
		 * @return {@link PreparedTag}
		 */
		public PreparedTag build() {
			flushStringBuilder();
			return new PreparedTag(rawTag, partials, markers);
		}
	}

}
