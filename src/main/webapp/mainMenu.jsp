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
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>Welcome ${name}</h2>
    </c:if>
      <div class="box-center">
          <h3 class="form-heading">ATM SIMULATION</h3>
          <h3><a href="/withdrawMenu">1. Withdraw</a></h3>
          <h3><a href="/fundTransfer1">2. Fund Transfer</a></h3>
          <h3><a href="/printTransaction">3. Print Transaction</a></h3>
          <h3><a onclick="document.forms['logoutForm'].submit()">4. Exit</a></h3>
      </div>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
