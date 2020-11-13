<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Main Menu</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>
  <div class="container">
      <div class="panel panel-default">
          <div class="panel-heading">
              <h3>List of transactions</h3>
          </div>
          <div class="panel-body">
              <table class="table table-striped">
                  <thead>
                  <tr>
                      <th width="30%">Account</th>
                      <th width="40%">Type</th>
                      <th width="30%">Amount</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${transactions}" var="t">
                      <tr>
                          <td>${t.accountNumber}</td>
                          <td>${t.transactionType}</td>
                          <td>${t.amount}</td>
                      </tr>
                  </c:forEach>
                  </tbody>
              </table>
          </div>
          <div class="panel-footer">
              <h3><a href="/mainMenu">Back</a></h3>
          </div>
      </div>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
