package com.bicrement.jaml.performance;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bicrement.jaml.fixture.User;
import com.bicrement.jaml.fixture.UserStringBuilder;
import com.bicrement.jaml.fixture.UserTag;

public class TagBindPerformance {
	
	static int[] userSizes = { 5000, 10000, 15000, 100000 };
	static Map<Integer, List<User>> users = new HashMap<>();
	
	static Function<Integer, User> createUser = (i) -> {
		User user = new User();
		user.id = i;
		user.name = "user-" + i;
		user.age = i;
		return user;
	};
	
	@BeforeClass
	public static void beforeSetUp() {
		for (int size : userSizes) {
			List<User> u = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				u.add(createUser.apply(i));
			}
			users.put(size, u);
		}
	}
	
	@After
	public void tearDown() {
		System.gc();
	}
	
	@Test
	public void compare() {
		String html_sb = UserStringBuilder.createTable(Arrays.asList(createUser.apply(1)));
		String html_tag = UserTag.createTable(Arrays.asList(createUser.apply(1)));
		String html_tag_prepare = UserTag.createTableUsingPrepare(Arrays.asList(createUser.apply(1)));

		assertEquals(html_sb, html_tag);
		assertEquals(html_sb, html_tag_prepare);
	}
	
	@Test
	public void onStringBuilderCreate() {
		for (int size : userSizes) {
			long startMs = System.currentTimeMillis();
			String html = UserStringBuilder.createTable(users.get(size));
			long durationMs = System.currentTimeMillis() - startMs;
			System.out.println(String.format("Test [%s], Size [%d], %d ms", "StringBuilder", size, durationMs));
			html = html.trim();
		}
	}
	
	@Test
	public void onTagCreate() {
		for (int size : userSizes) {
			long startMs = System.currentTimeMillis();
			String html = UserTag.createTable(users.get(size));
			long durationMs = System.currentTimeMillis() - startMs;
			System.out.println(String.format("Test [%s], Size [%d], %d ms", "Tag", size, durationMs));
			html = html.trim();
		}
	}
	
	@Test
	public void onTagBindCreate() {
		for (int size : userSizes) {
			long startMs = System.currentTimeMillis();
			String html = UserTag.createTableUsingPrepare(users.get(size));
			long durationMs = System.currentTimeMillis() - startMs;
			System.out.println(String.format("Test [%s], Size [%d], %d ms", "TabBind", size, durationMs));
			html = html.trim();
		}
	}

}
