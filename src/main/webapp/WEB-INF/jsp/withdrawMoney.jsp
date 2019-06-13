<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Withdraw money</title>
</head>
<h3>Withdraw money</h3>
<body>
<form:form method="POST"
           action="/accounts/withdraw" modelAttribute="transfer">
    <table>
        <tr>
            <td><form:label path="senderId">Account Id</form:label></td>
            <td><form:input path="senderId"/></td>
        </tr>
        <tr>
            <td><form:label path="amount">Amount</form:label></td>
            <td><form:input path="amount"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Save"/></td>
        </tr>
        <tr>
            <td><a href="/">Main Page</a><br></td>
        </tr>
    </table>
</form:form>
</body>
</html>