<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Result</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="wrapper">
    <div id="main_content">

        <header>
            <h1>Guest book</h1>
            <h3>List of members:</h3>
        </header>

        <div class="results_container">

            <c:forEach items="${persons}" var="person">
                <div class="person_container">
                    <b>Name:</b> ${person.lastName}, ${person.firstName}<br>
                    <b>Birth date:</b> ${person.bithdate}<br>
                    <b>Phone:</b> ${person.phone}<br>
                    <b>Email:</b> ${person.email}<br>
                    <b>Country:</b> ${person.country}<br>
                    <b>City/district:</b> ${person.city}
                </div>
            </c:forEach>
        </div>
    </div> <!-- #main_content> -->

    <footer>
        <b>Footer</b>
    </footer>
</div><!-- #wrapper -->
</body>
</html>