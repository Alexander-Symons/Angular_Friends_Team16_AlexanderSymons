<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Overview" />
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Overview" />
</jsp:include>
<main>
<c:if test="${groups.size()>0 }">
    <div>
        <table>
            <tr>
                <th>name</th>
                <th>size</th>
            </tr>
            <c:forEach var="group" items="${groups}">
                <tr>
                    <td>${group.groupname}</td>
                    <td>${group.size}</td>
                    <form method="post" action="">
                        <label>Enter City :</label>
                        <input id="cityName" name="cityName" size="30" type="text" />
                        <input id="getWeatherReport" name="getWeatherReport" type="button" value="Get Weather" />
                    </form>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</main>
</body>
</html>