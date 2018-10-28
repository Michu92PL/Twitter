<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: goobar
  Date: 22.10.18
  Time: 12:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>twitter</title>
</head>
<body>

<form action="/twitter" method="post">
    Message: <input type="text" name="message"/>
    <input type="submit" value="Post Tweet"/>
</form>

<h3>All Tweets:</h3>
<table>
    <tr>
        <td>Author</td>
        <td>Message</td>
    </tr>
    <c:forEach var="tweet" items="${tweets}">
        <tr>

            <td>
                <c:out value="${tweet.author}"/>
            </td>

            <td>
                <c:out value="${tweet.message}"/>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>