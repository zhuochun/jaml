package com.bicrement.jaml;

import static com.bicrement.jaml.Attributes.$$;
import static com.bicrement.jaml.Attributes.id;
import static com.bicrement.jaml.Attributes.klass;
import static com.bicrement.jaml.Html.__;
import static com.bicrement.jaml.Html.a;
import static com.bicrement.jaml.Html.div;
import static com.bicrement.jaml.Html.p;
import static com.bicrement.jaml.Html.span;
import static com.bicrement.jaml.Html.text;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import com.bicrement.jaml.cache.PreparedTag;
import com.google.common.collect.ImmutableMap;

public class BaseTagBindTest {

	@Test
	public void testBlockTag() {
		PreparedTag div = div($$(klass("dialog")), p(__), p(__)).prepare();
		String html = div.bind("paragraph 1", "paragraph 2").toString();
		assertEquals("<div class=\"dialog\"><p>paragraph 1</p>"
				+ "<p>paragraph 2</p></div>", html);
	}

	@Test
	public void testLinkTag() {
		PreparedTag link = a(__).prepare();
		String html = link.bind("http://www.bicrement.com").toString();
		assertEquals("<a href=\"http://www.bicrement.com\"/>", html);
	}

	@Test
	public void testEventTag() {
		PreparedTag event = 
				div($$(id(__("id")), klass("event")),
					div($$(klass("event-content")), p(__("content"))),
					div($$(klass("event-meta")),
						span($$(klass("event-author")), text("From "), __("author")),
						span($$(klass("event-date")), text("On "), __("date"))
					)
				).prepare();

		Map<String, Object> values = ImmutableMap.<String, Object> builder()
				.put("id", 123).put("content", "Good Morning!")
				.put("author", "Peter").put("date", "2015-05-19").build();

		String html = event.bind(values).toString();
		String expected = "<div id=\"123\" class=\"event\">"
				+ "<div class=\"event-content\"><p>Good Morning!</p></div>"
				+ "<div class=\"event-meta\">"
				+ "<span class=\"event-author\">From Peter</span>"
				+ "<span class=\"event-date\">On 2015-05-19</span>"
				+ "</div></div>";
		assertEquals(expected, html);
	}

}
