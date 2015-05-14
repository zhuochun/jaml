# Jaml (Java as a Markup Language)

A basic HTML template engine in Java.

## Example

### Pure HTML View

``` java
div($$(id("login"), klass("login-dialog")), // $$ take attributes
    div($$(klass("login-header")),
        h1(text("Login"))
    ),
    div($$(klass("login-body")),
        form($$(id("login-form")),
          div(text("Name:"), input("text", name("username"))),
          div(text("Password:"), input("password", name("password")))
        )
    )
);

/* generates:
<div id="login" class="login-dialog">
  <div class="login-header">
    <h1>Login</h1>
  </div>
  <div class="login-body">
    <form id="login-form">
      <div>Name: <input type="text" name="username"/></div>
      <div>Password: <input type="password" name="password"/></div>
    </form>
  </div>
</div>
 */
```

### Bindable HTML View

``` java
PreparedDom userRow = tr(td(__), td(__), td(__)).prepare(); // __ is a bindMarker

List<Tag> rows = new ArrayList<>(users.size());
for (User user : users) {
  rows.add(userRow.bind(user.id, user.name, user.age).toHtml());
}

Tag thead = thead(tr(th(text("Id")), th(text("Name")), th(text("Age"))));
Tag table = table(thead, tbody(rows.toArray(new Tag[rows.size()])));

/* generates:
<table>
  <thead>
    <tr> <th>Id</th> <th>Name</th> <th>Age</th> </tr>
  </thead>
  <tbody>
    <tr> <td>1</td> <td>User A</td> <td>23</td> </tr>
    <tr> <td>2</td> <td>User B</td> <td>24</td> </tr>
    <tr> <td>3</td> <td>User C</td> <td>25</td> </tr>
  </tbody>
</table>
 */
```

## Credits

[Wang Zhuochun](https://github.com/zhuochun) @ 2015
