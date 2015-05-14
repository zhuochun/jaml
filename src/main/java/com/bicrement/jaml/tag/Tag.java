package com.bicrement.jaml.tag;

import java.util.List;

import com.bicrement.jaml.cache.PreparedTag;

/**
 * Interface for Tag.
 * 
 * @author zhuochun
 *
 */
public interface Tag {

	/**
	 * Get the tag name.
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Get the attributes of this tag.
	 * 
	 * @return
	 */
	public List<Attribute> getAttributes();

	/**
	 * Get the child elements in this tag.
	 * 
	 * @return
	 */
	public List<Tag> getChildElements();

	/**
	 * Get the plain DOM content of this tag, including its children.
	 * 
	 * NOTE: this is the same as {@code prepare().bind().getHtml()}.
	 * 
	 * @return this {@link Tag} content in {@link BaseText}
	 */
	public BaseText getHtml();

	/**
	 * Get the prepared DOM content that is ready for binding values.
	 * 
	 * NOTE: You can only prepare logicless DOM, e.g. no @{code if, for, while}.
	 * 
	 * @return {@link PreparedTag}
	 */
	public PreparedTag prepare();

	/**
	 * Get the prepared DOM content that is ready for binding values, written
	 * into the {@link PreparedTag.Builder}.
	 * 
	 * NOTE: You can only prepare logicless DOM, e.g. no @{code if, for, while}.
	 * 
	 * @param builder
	 * @return builder
	 */
	public PreparedTag.Builder prepare(PreparedTag.Builder builder);

	/**
	 * Write the String representation of this tag, including its children, into
	 * the {@link StringBuilder}.
	 * 
	 * @param sb
	 * @return {@link StringBuilder} sb
	 */
	public StringBuilder toString(StringBuilder sb);

	/**
	 * Returns the String representation of this tag, including its children.
	 * 
	 * @return String
	 */
	@Override
	public String toString();
}
