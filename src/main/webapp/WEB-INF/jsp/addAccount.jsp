<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add new account</title>
</head>
<h3>Add new account</h3>
<body>
<form:form method="POST"
           action="/accounts/addAccount" modelAttribute="account">
    <table>
        <tr>
            <td><form:label path="clientId">Client Id</form:label></td>
            <td><form:input path="clientId"/></td>
        </tr>
        <tr>
            <td><form:label path="amount">Amount</form:label></td>
            <td><form:input path="amount"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>