package com.bicrement.jaml.fixture;

import java.util.List;

import static com.bicrement.jaml.tool.Sanitize.*;

public class UserStringBuilder {
	
	public static String createTable(List<User> users) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<table>");
		sb.append("<thead>").append("<tr><th>Id</th><th>Name</th><th>Age</th></tr>").append("</thead>");
		sb.append("<tbody>");
		for (User user : users) {
			sb.append("<tr><td>").append(sanitizeText(user.id)).append("</td><td>");
			sb.append(sanitizeText(user.name)).append("</td><td>");
			sb.append(sanitizeText(user.age)).append("</td></tr>");
		}
		sb.append("</tbody>");
		sb.append("</table>");
		
		return sb.toString();
	}

}
