package com.bicrement.jaml.fixture;

import java.util.ArrayList;
import java.util.List;

import static com.bicrement.jaml.Html.*;

import com.bicrement.jaml.cache.PreparedTag;
import com.bicrement.jaml.tag.Tag;

public class UserTag {
	
	public static String createTable(List<User> users) {
		List<Tag> rows = new ArrayList<>(users.size());
		for (User user : users) {
			Tag row = tr(td(text(user.id)), td(text(user.name)), td(text(user.age)));
			rows.add(row);
		}
		
		Tag thead = thead(tr(th(text("Id")), th(text("Name")), th(text("Age"))));
		Tag table = table(thead, tbody(rows.toArray(new Tag[rows.size()])));

		return table.toString();
	}

}
