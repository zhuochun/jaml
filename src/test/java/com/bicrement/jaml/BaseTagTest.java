package com.bicrement.jaml;

import static org.junit.Assert.*;

import static java.util.Arrays.*;
import static java.util.Collections.*;

import org.junit.Test;

import com.bicrement.jaml.tag.Attribute;
import com.bicrement.jaml.tag.BaseTag;
import com.bicrement.jaml.tag.Tag;

public class BaseTagTest {

	@Test
	public void testEmptyTag() {
		Tag tag = new BaseTag("test", emptyList(), emptyList());
		assertEquals("<test/>", tag.toString());
	}

	@Test
	public void testBlockTag() {
		Attribute cssClass = new Attribute("class", "dialog");

		Tag p = new BaseTag("p", emptyList(), emptyList());
		Tag div = new BaseTag("div", asList(cssClass), asList(p, p));

		assertEquals("<div class=\"dialog\"><p/><p/></div>", div.toString());
	}

	@Test
	public void testEmptySelfClosedTag() {
		Tag tag = new BaseTag("test", emptyList());
		assertEquals("<test/>", tag.toString());
	}

	@Test
	public void testAttributeTag() {
		Attribute attr = new Attribute("href", "http://www.bicrement.com");
		Tag link = new BaseTag("a", asList(attr));
		assertEquals("<a href=\"http://www.bicrement.com\"/>", link.toString());
	}

}
