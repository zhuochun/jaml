package com.bicrement.jaml;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.bicrement.jaml.Attributes.*;
import static com.bicrement.jaml.Html.*;
import com.bicrement.jaml.tag.Tag;

public class HtmlTest {

	@Test
	public void testForm() {
		Tag form = form(
				div(text("Username:"), input("text", name("username"))),
				div(text("Password:"), input("password", name("password")))
			);

		String expected = "<form>" +
				"<div>Username:<input type=\"text\" name=\"username\"/></div>" +
				"<div>Password:<input type=\"password\" name=\"password\"/></div>" +
				"</form>";
		assertEquals(expected, form.getContent());
	}

}
