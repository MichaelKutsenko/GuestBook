<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>
<%@ page import="softGroup.testTask.objects.Country" %>
<%@ page import="java.util.List" %>
<%@ page import="softGroup.testTask.util.LocationGetter" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Form</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/checkform.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/change.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mask.js"></script>
</head>
<body>
<div id="wrapper">
    <div id="main_content">
        <header>
            <h1>Guest book</h1>
            <h3>Please fill in all fields:</h3>
        </header>

        <form onsubmit="return checkForm(this)" action="result" method="POST">
            <span class="error">${message}</span>
            <div id="fields_container">
                <div>
                    <div class="element">First name:</div>
                    <input id="name" type="text" name="firstName" placeholder="James"/>
                    <span id="err_name" class="error"></span>
                </div>

                <div>
                    <div class="element">Last name:</div>
                    <input id="surname" type="text" name="lastName" placeholder="Bond"/>
                    <span id="err_surname" class="error"></span>
                </div>

                <div>
                    <div class="element">Birth date:</div>
                    <div class="bithdate">
                        <select id="bith_year" name="year">
                            <option value="">year</option>
                            <c:set var="year" value="<%=new GregorianCalendar().get(Calendar.YEAR)%>"/>
                            <c:forEach begin="1" end="100">
                                <option>${year}</option>
                                <c:set var="year" value="${year - 1}"/>
                            </c:forEach>
                        </select>

                        <select onchange="change()" id="bith_month" name="month">
                            <option value="">month</option>
                            <option value="0">January</option>
                            <option value="1">February</option>
                            <option value="2">March</option>
                            <option value="3">April</option>
                            <option value="4">May</option>
                            <option value="5">June</option>
                            <option value="6">July</option>
                            <option value="7">August</option>
                            <option value="8">September</option>
                            <option value="9">October</option>
                            <option value="10">November</option>
                            <option value="11">December</option>
                        </select>

                        <select id="bith_day" name="day">
                            <option value="">day</option>
                        </select>
                    </div>

                    <span id="err_bithdate" class="error"></span>
                </div>

                <div>
                    <div class="element">Phone:</div>
                    <input id="phone" type="text" name="phone" placeholder="+3(333)555-5555"/>
                    <span id='err_phone' class="error"></span>
                </div>

                <div>
                    <div class="element">Email:</div>
                    <input id="email" type="text" name="email" placeholder="example@email.com"/>
                    <span id="err_email" class="error"></span>
                </div>

                <div>
                    <div class="element">Country:</div>
                    <select id="country" name="country">
                        <option value="">country</option>

                        <%
                            List<Country> countries = LocationGetter.countryList();
                            request.setAttribute("countries", countries);
                        %>
                        <c:forEach items="${countries}" var="country">
                            <option>${country.title}</option>
                        </c:forEach>
                    </select>
                    <span id="err_country" class="error"></span>
                </div>

                <div>
                    <div class="element">City/district:</div>
                    <input id="city" type="text" name="city" placeholder="London"/>
                    <span id="err_city" class="error"></span>
                </div>
            </div><!-- #fields_container> -->

            <div>
                <input type="submit" value="Submit">
            </div>
        </form>
    </div> <!-- #main_content> -->

    <footer>
        <b>Footer</b>
    </footer>
</div><!-- #wrapper -->
</body>
</html>
