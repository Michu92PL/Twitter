<%--
  Created by IntelliJ IDEA.
  User: MichuPC
  Date: 17.10.2018
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logowanie</title>
</head>
<body>

<p>Podaj dane logowania</p>
<form action="/login" method="POST">
    <input type="text" name="user"/>
    <input type="password" name="pass"/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>
