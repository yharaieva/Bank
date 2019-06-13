<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Clients</title>
</head>
<body>
<h3>Clients</h3>
<table border="1px">
    <tr>
        <td>Id</td>
        <td>Name</td>
        <td>Address</td>
        <td>Age</td>
    </tr>
    <c:forEach items="${clients}" var="client">
        <tr>
            <td>${client.id}</td>
            <td><a href="/accounts?clientId=${client.id}">${client.name}</a></td>
            <td>${client.address}</td>
            <td>${client.age}</td>
        </tr>
    </c:forEach>
</table>

<a href="/clients/addClient">Add new client</a>
<a href="/">Main page</a>

</body>
</html>