<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>

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
    <div class = "hidden" style="display:none" >
        <table>
            <tr>
                <th>name</th>
                <th>size</th>
                <th>groupschat</th>
            </tr>
            <c:forEach var="group" items="${groups}">
                <tr>
                    <td>${group.groupname}</td>
                    <td>${group.size}</td>
                    <td><input id="groupchat" name="GroupChat" type="button" onclick="send('${group.groupname}')"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</main>

<script type="text/javascript" src="../js/group.js"></script>
<script src="js/jquery-3.6.0.js"></script>
<script src="js/effects.js"></script>
</body>
</html>