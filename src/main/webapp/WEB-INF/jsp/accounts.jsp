<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
<h3>Accounts</h3>
<table border="1px">
    <tr>
        <td>Id</td>
        <td>Client Id</td>
        <td>Amount</td>
    </tr>
    <c:forEach items="${accounts}" var="account">
        <tr>
            <td>${account.id}</td>
            <td>${account.clientId}</td>
            <td>${account.amount}</td>
        </tr>
    </c:forEach>
</table>

<a href="/clients">Back</a>
<a href="/accounts/addAccount">Add new account</a><br>

</body>
</html>