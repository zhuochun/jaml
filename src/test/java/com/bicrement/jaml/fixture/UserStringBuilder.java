package com.bicrement.jaml.fixture;

import java.util.List;

public class UserStringBuilder {
	
	public static String createTable(List<User> users) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<table>");
		sb.append("<thead>").append("<tr><th>Id</th><th>Name</th><th>Age</th></tr>").append("</thead>");
		sb.append("<tbody>");
		for (User user : users) {
			sb.append("<tr><td>").append(user.id).append("</td><td>");
			sb.append(user.name).append("</td><td>").append(user.age).append("</td></tr>");
		}
		sb.append("</tbody>");
		sb.append("</table>");
		
		return sb.toString();
	}

}
