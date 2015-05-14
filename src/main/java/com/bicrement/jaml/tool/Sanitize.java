package com.bicrement.jaml.tool;

import com.google.common.html.HtmlEscapers;

/**
 * Sanitize
 * 
 * @author zhuochun
 *
 */
public class Sanitize {

	/**
	 * Sanitize the text by escapes HTML characters
	 * 
	 * @param text
	 * @return sanitized text
	 */
	public static String sanitizeText(Object text) {
		return HtmlEscapers.htmlEscaper().escape(text.toString());
	}

}
