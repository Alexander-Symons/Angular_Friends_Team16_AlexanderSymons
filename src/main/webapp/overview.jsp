<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Overview"/>
</jsp:include>
<body onload="getEmailAjax()">
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Overview"/>
</jsp:include>
<main>
    <div id="emailajax"></div>
    <form method="post">
        <label>Show role:</label>
        <input id="getRoleAJAX" name="getRoleAJAX" type="button" value="Get Role"/>
    </form>
    <div id="roleAJAX" class="outputTextArea"></div>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript">
        //* Author Arne Artois *//
        function getEmailAjax() {
            $.ajax({
                type: "GET",
                url: "Team2Ajax1",
                dataType: "json",
                success: function (json) {
                    $('#emailajax').html(json.userId);
                },
                error: function () {
                }
            });
        }
    </script>
    <script type="text/javascript">
        //* Author Arne Artois *//
        $(document).ready(function () {
            $("#getRoleAJAX").click(function () {
                $.post("Team2Ajax2", function (data) {
                    var newParagraph = $('<p />').text(data);
                    $('#roleAJAX').empty();
                    $('#roleAJAX').append(newParagraph);
                });
            });
        });
    </script>

    <c:if test="${groups.size()>0 }">
        <div class="hidden" style="display:none">
            <table id="table">
            </table>
                <%--        <table>--%>
                <%--            <tr>--%>
                <%--                <th>name</th>--%>
                <%--                <th>size</th>--%>
                <%--                <th>groupschat</th>--%>
                <%--            </tr>--%>
                <%--            <c:forEach var="group" items="${groups}">--%>
                <%--                <tr>--%>
                <%--                    <td>${group.groupname}</td>--%>
                <%--                    <td>${group.size}</td>--%>
                <%--                    <td><input id="groupchat" name="GroupChat" type="button" onclick="send('${group.groupname}')"/></td>--%>
                <%--                </tr>--%>
                <%--            </c:forEach>--%>
                <%--        </table>--%>
        </div>
    </c:if>
</main>
<script src="js/fillgroupoverview.js "></script>
<script type="text/javascript" src="../js/group.js"></script>
<script src="js/jquery-3.6.0.js"></script>
<script src="js/effects.js"></script>
</body>
</html>
