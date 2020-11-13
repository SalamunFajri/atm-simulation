<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Other Withdraw</title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
  </head>

  <body>

    <div class="container">
      <form method="POST" action="${contextPath}/fundTransfer1" class="form-signin">
            <h2 class="form-heading">Fund Transfer</h2>
            <span>${message}</span>
            <input name="destAccount" type="text" class="form-control" placeholder="Destination Account"
                   autofocus="true"/>
            <input type="hidden" name="${_csrf.parameterName}"
                 value="${_csrf.token}"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Continue</button>
     </form>
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  </body>
</html>
