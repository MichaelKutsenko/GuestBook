<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error page</title>
    <meta charset="utf-8">
</head>
<body>
<p>hello! This is error page!!!</p>

<p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
<p><b>Error code:</b> ${pageContext.errorData}</p>
<p><b>Request URI:</b> ${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}</p>
</body>
</html>