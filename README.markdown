# Jaml (Java Markup Language)

Write HTML in Java.

## Example

### Pure view

``` java
Tag loginDiv =
    div($$(id("login"), klass("login-frame")),
        div($$(klass("login-header")),
            div(text("Login"))
        ),
        div($$(klass("login-body")),
            form($$(id("login-form")),
				div(text("Name:"), input("text", attr("name", "username"))),
				div(text("Password:"), input("password", attr("name", "password")))
			)
		)
    );
```

### Bind data to view

``` java
List<User> users;

PreparedDom preparedRow = tr(td(text(__)), td(text(__))).prepare();
List<Tag> rows = users.stream()
					  .map(u -> preparedRow.bind(u.getName(), u.getAge()).getContent())
					  .map(c -> raw(c))
					  .collect(Collectors.toList());

Tag usersTable = table(thead(th(text("Name")), th(text("Age"))), tbody(rows));
```