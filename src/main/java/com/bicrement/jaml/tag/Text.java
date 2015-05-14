package com.bicrement.jaml.tag;

/**
 * Classes that accept texts directly should implement this interface.
 * 
 * @author zhuochun
 *
 */
public interface Text {

	/**
	 * Mark this text as HTML safe to skip sanitize.
	 * 
	 * @return this {@link Text}
	 */
	public Text htmlSafe();

	/**
	 * Check whether this text is HTML safe.
	 * 
	 * @return boolean
	 */
	public boolean isHtmlSafe();

	/**
	 * Returns the String representation of the text.
	 * 
	 * @return String
	 */
	@Override
	public String toString();

}
