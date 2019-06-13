<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>Transactions</title>
</head>
<body>
<h3>Transactions</h3>
<table border="1px">
    <tr>
        <td>Id</td>
        <td>Recipient Id</td>
        <td>Sender Id</td>
        <td>Amount</td>
        <td>Created Time</td>
    </tr>
    <c:forEach items="${transactions}" var="transaction">
        <tr>
            <td>${transaction.id}</td>
            <td>${transaction.recipientId}</td>
            <td>${transaction.senderId}</td>
            <td>${transaction.amount}</td>
            <td>${transaction.createdTime}</td>
        </tr>
    </c:forEach>
</table>

<form method="GET" action="/transactions">
    <table>
        <tr>
            <td><label id="accountId">Account Id <input type="text" name="accountId"/></label></td>
        </tr>
        <tr>
            <td><label id="from">From <input type="datetime-local" name="from"/></label></td>
        </tr>
        <tr>
            <td><label id="to">To <input type="datetime-local" name="to"/></label></td>
        </tr>
        <tr>
            <td><input type="submit" value="Search"/></td>
        </tr>
    </table>
</form>

<a href="/">Main page</a>

</body>
</html>