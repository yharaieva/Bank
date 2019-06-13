<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Make transfer</title>
</head>
<h3>Make transfer</h3>
<body>
<form:form method="POST"
           action="/accounts/transfer" modelAttribute="transfer">
    <table>
        <tr>
            <td><form:label path="recipientId">Recipient Id</form:label></td>
            <td><form:input path="recipientId"/></td>
        </tr>
        <tr>
            <td><form:label path="senderId">Sender Id</form:label></td>
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