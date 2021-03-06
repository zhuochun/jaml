package com.bicrement.jaml;

import static com.bicrement.jaml.Attributes.*;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bicrement.jaml.tag.Attribute;
import com.bicrement.jaml.tag.BaseTag;
import com.bicrement.jaml.tag.BaseText;
import com.bicrement.jaml.tag.BindMarker;
import com.bicrement.jaml.tag.Tag;
import com.bicrement.jaml.tag.Text;

/**
 * Shortcuts to create HTML {@link Tag}s.
 * 
 * @author zhuochun
 *
 */
public final class Html {

	/**
	 * A {@link BindMarker} tag.
	 */
	public static final BindMarker __ = new BindMarker();

	/**
	 * Create a named {@link BindMarker} tag.
	 * 
	 * The name can be used retrieve value when binding with a {@link Map}.
	 * 
	 * @param name
	 * @return a {@link BindMarker}
	 */
	public static BindMarker __(String name) {
		return new BindMarker(name);
	}

	/**
	 * Create a plain text that will be sanitized by default.
	 * 
	 * Use {@link BaseText#htmlSafe()} to skip sanitize.
	 *
	 * @param content
	 * @return
	 */
	public static BaseText text(Object content) {
		return new BaseText(content.toString());
	}

	public static BaseTag html(Tag... childElems) {
		return new BaseTag("html", emptyList(), asList(childElems));
	}

	public static BaseTag body(Tag... childElems) {
		return new BaseTag("body", emptyList(), asList(childElems));
	}

	public static BaseTag body(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("body", attrs, asList(childElems));
	}

	public static BaseTag div(Tag... childElems) {
		return new BaseTag("div", emptyList(), asList(childElems));
	}

	public static BaseTag div(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("div", attrs, asList(childElems));
	}

	public static BaseTag h1(Tag... childElems) {
		return new BaseTag("h1", emptyList(), asList(childElems));
	}

	public static BaseTag h2(Tag... childElems) {
		return new BaseTag("h2", emptyList(), asList(childElems));
	}

	public static BaseTag h3(Tag... childElems) {
		return new BaseTag("h3", emptyList(), asList(childElems));
	}

	public static BaseTag h4(Tag... childElems) {
		return new BaseTag("h4", emptyList(), asList(childElems));
	}

	public static BaseTag h5(Tag... childElems) {
		return new BaseTag("h5", emptyList(), asList(childElems));
	}

	public static BaseTag h6(Tag... childElems) {
		return new BaseTag("h6", emptyList(), asList(childElems));
	}

	public static BaseTag p(Tag... childElems) {
		return new BaseTag("p", emptyList(), asList(childElems));
	}

	public static BaseTag p(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("p", attrs, asList(childElems));
	}
	
	public static BaseTag img(Text url) {
		return new BaseTag("img", $$(src(url)));
	}
	
	public static BaseTag figure(Text url) {
		return new BaseTag("figure", emptyList(), asList(img(url)));
	}

	public static BaseTag span(Tag... childElems) {
		return new BaseTag("span", emptyList(), asList(childElems));
	}

	public static BaseTag span(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("span", attrs, asList(childElems));
	}

	public static BaseTag a(Text href, Tag... childElems) {
		return new BaseTag("a", $$(href(href)), asList(childElems));
	}

	public static BaseTag a(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("a", attrs, asList(childElems));
	}

	public static BaseTag form(Tag... childElems) {
		return new BaseTag("form", emptyList(), asList(childElems));
	}

	public static BaseTag form(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("form", attrs, asList(childElems));
	}

	public static BaseTag input(String type, Attribute... attrs) {
		List<Attribute> attributes = new ArrayList<>(attrs.length + 1);

		attributes.add(attr("type", type));
		attributes.addAll(asList(attrs));

		return new BaseTag("input", attributes, emptyList());
	}

	public static BaseTag table(Tag... childElems) {
		return new BaseTag("table", emptyList(), asList(childElems));
	}

	public static BaseTag table(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("table", attrs, asList(childElems));
	}

	public static BaseTag thead(Tag... childElems) {
		return new BaseTag("thead", emptyList(), asList(childElems));
	}

	public static BaseTag thead(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("thead", attrs, asList(childElems));
	}

	public static BaseTag tbody(Tag... childElems) {
		return new BaseTag("tbody", emptyList(), asList(childElems));
	}

	public static BaseTag tbody(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("tbody", attrs, asList(childElems));
	}

	public static BaseTag tr(Tag... childElems) {
		return new BaseTag("tr", emptyList(), asList(childElems));
	}

	public static BaseTag tr(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("tr", attrs, asList(childElems));
	}

	public static BaseTag th(Tag... childElems) {
		return new BaseTag("th", emptyList(), asList(childElems));
	}

	public static BaseTag th(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("th", attrs, asList(childElems));
	}

	public static BaseTag td(Tag... childElems) {
		return new BaseTag("td", emptyList(), asList(childElems));
	}

	public static BaseTag td(List<Attribute> attrs, Tag... childElems) {
		return new BaseTag("td", attrs, asList(childElems));
	}

}
