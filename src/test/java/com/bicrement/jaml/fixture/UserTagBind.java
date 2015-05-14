package com.bicrement.jaml.fixture;

import static com.bicrement.jaml.Html.__;
import static com.bicrement.jaml.Html.table;
import static com.bicrement.jaml.Html.tbody;
import static com.bicrement.jaml.Html.td;
import static com.bicrement.jaml.Html.text;
import static com.bicrement.jaml.Html.th;
import static com.bicrement.jaml.Html.thead;
import static com.bicrement.jaml.Html.tr;

import java.util.List;

import com.bicrement.jaml.cache.PreparedTag;
import com.bicrement.jaml.tag.Tag;

public class UserTagBind {

	static PreparedTag userRow = tr(td(__), td(__), td(__)).prepare();
	static Tag thead = thead(
			tr(th(text("Id")), th(text("Name")), th(text("Age")))).getHtml();

	public static String createTable(List<User> users) {
		Tag[] rows = new Tag[users.size()];
		for (int i = 0; i < users.size(); i++) {
			rows[i] = userRow.bind(users.get(i).id, users.get(i).name,
					users.get(i).age);
		}

		Tag table = table(thead, tbody(rows));

		return table.toString();
	}

	public static String createTable2(List<User> users) {
		Tag[] rows = new Tag[users.size()];
		for (int i = 0; i < users.size(); i++) {
			rows[i] = userRow.bind(users.get(i).id, users.get(i).name,
					users.get(i).age).getHtml();
		}

		Tag table = table(thead, tbody(rows));

		return table.toString();
	}
}
