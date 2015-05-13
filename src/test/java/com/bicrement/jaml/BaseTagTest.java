package com.bicrement.jaml;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import com.bicrement.jaml.tag.Attribute;
import com.bicrement.jaml.tag.BaseTag;
import com.bicrement.jaml.tag.Tag;

public class BaseTagTest {

	@Test
	public void testEmptyTag() {
		Tag tag = new BaseTag("test", Collections.emptyList(), Collections.emptyList());
		assertEquals("<test></test>", tag.getContent());
	}
	
	@Test
	public void testBlockTag() {
		Attribute cssClass = new Attribute("class", "dialog");

		Tag p = new BaseTag("p", Collections.emptyList(), Collections.emptyList());
		Tag div = new BaseTag("div", Arrays.asList(cssClass), Arrays.asList(p, p));
		
		assertEquals("<div class=\"dialog\"><p></p><p></p></div>", div.getContent());
	}
	
	@Test
	public void testEmptySelfClosedTag() {
		Tag tag = new BaseTag("test", Collections.emptyList());
		assertEquals("<test/>", tag.getContent());
	}

	@Test
	public void testAttributeTag() {
		Attribute attr = new Attribute("href", "http://www.bicrement.com");
		Tag link = new BaseTag("a", Arrays.asList(attr));
		assertEquals("<a href=\"http://www.bicrement.com\"/>", link.toString());
	}

}
