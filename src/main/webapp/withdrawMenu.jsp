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
      <div class="box-center">
          <h3 class="form-heading">WITHDRAW</h3>
          <h3><a href="/withdrawSummary?amount=${10}">1. $10</a></h3>
          <h3><a href="/withdrawSummary?amount=${50}">2. $50</a></h3>
          <h3><a href="/withdrawSummary?amount=${100}">3. $100</a></h3>
          <h3><a href="/withdrawCustom">4. Other</a></h3>
          <h3><a href="/mainMenu">5. Back</a></h3>
      </div>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
