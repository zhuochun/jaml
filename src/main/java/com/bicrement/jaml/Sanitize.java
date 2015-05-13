package com.bicrement.jaml;

import com.google.common.html.HtmlEscapers;

public class Sanitize {
	
	/**
	 * A simple one
	 * 
	 * @param text
	 * @return
	 */
	public static String sanitizeText(String text) {
		return HtmlEscapers.htmlEscaper().escape(text);
	}

}
