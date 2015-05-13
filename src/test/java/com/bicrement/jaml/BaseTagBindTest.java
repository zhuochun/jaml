package com.bicrement.jaml;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import com.bicrement.jaml.content.BindMarker;
import com.bicrement.jaml.tag.Attribute;
import com.bicrement.jaml.tag.BaseTag;
import com.bicrement.jaml.tag.Tag;

public class BaseTagBindTest {
	
	@Test
	public void testBlockTag() {
		Attribute cssClass = new Attribute("class", "dialog");

		Tag p = new BaseTag("p", Collections.emptyList(), Arrays.asList(new BindMarker()));
		Tag div = new BaseTag("div", Arrays.asList(cssClass), Arrays.asList(p, p));
		
		String html = div.prepare().bind("paragraph 1", "paragraph 2").getContent();
		assertEquals("<div class=\"dialog\"><p>paragraph 1</p><p>paragraph 2</p></div>", html);
	}
	
	@Test
	public void testLinkTag() {
		Attribute attr = new Attribute("href", new BindMarker());
		Tag link = new BaseTag("a", Arrays.asList(attr));
		
		String html = link.prepare().bind("http://www.bicrement.com").getContent();
		assertEquals("<a href=\"http://www.bicrement.com\"/>", html);
	}

}
