<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    <div class="danger">
        <ul>
            <c:forEach var="group" items="${groups}">
                <li>${group.groupname}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>
</main>
</body>
</html>