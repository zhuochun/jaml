package com.bicrement.jaml.fixture;

import static com.bicrement.jaml.Html.table;
import static com.bicrement.jaml.Html.tbody;
import static com.bicrement.jaml.Html.td;
import static com.bicrement.jaml.Html.text;
import static com.bicrement.jaml.Html.th;
import static com.bicrement.jaml.Html.thead;
import static com.bicrement.jaml.Html.tr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.bicrement.jaml.tag.Tag;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class UserTagCache {

	static Cache<String, Tag> tagCache = CacheBuilder.newBuilder()
			.maximumSize(10000).build();

	public static String createTable(List<User> users)
			throws ExecutionException {
		List<Tag> rows = new ArrayList<>(users.size());
		for (User user : users) {
			Tag row = tagCache.get(
					Integer.toString(user.id),
					() -> tr(td(text(user.id)), td(text(user.name)),
							td(text(user.age))));
			rows.add(row);
		}

		Tag thead = thead(tr(th(text("Id")), th(text("Name")), th(text("Age"))));
		Tag table = table(thead, tbody(rows.toArray(new Tag[rows.size()])));

		return table.toString();
	}

}
