<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="title" value="Home" />
</jsp:include>
<body>
	<jsp:include page="header.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
	<main>
<c:if test="${errors.size()>0 }">
	<div class="danger">
		<ul>
			<c:forEach var="error" items="${errors }">
				<li>${error}</li>
			</c:forEach>
		</ul>
	</div>
</c:if> <c:choose>
	<c:when test="${user!=null}">
		<form method="post" action="Controller?action=LogOut">
			<p>
				<input type="submit" id="logoutbutton" value="Log Out">
			</p>
		</form>
		<form method="post" action="Controller?action=NewGroup">
			<p>
				<label for="groupname">Create group </label>
				<input type="text" id="groupname" name="groupname" value="bestegroup">
			</p>
			<p>
				<label for="member1">member1 </label>
				<input type="text" id="member1" name="member1" value="Jan">
			</p>
			<p>
				<label for="member2">member2 </label>
				<input type="text" id="member2" name="member2" value="An">
			</p>
			<p>
				<label for="member3">member3 </label>
				<input type="text" id="member3" name="member3" value="Ian">
			</p>
			<p>
				<label for="member4">member4 </label>
				<input type="text" id="member4" name="member4" value="Ivan">
			</p>
			<p>
				<input type="submit" id="newgroupbutton" value="Add group">
			</p>
		</form>

	</c:when>
	<c:otherwise>
		<form method="post" action="Controller?action=LogIn">
			<p>
				<label for="email">Your email </label>
				<input type="text" id="email" name="email" value="jan@ucll.be">
			</p>
			<p>
				<label for="password">Your password</label>
				<input type="password" id="password" name="password" value="t">
			</p>
			<p>
				<input type="submit" id="loginbutton" value="Log in">
			</p>
			<p>bib@ucll.be , t</p>
			<p>an@ucll.be , t</p>
			<p>jan@ucll.be , t</p>
			<p>ian@ucll.be , t</p>
			<p>ivan@ucll.be , t</p>
		</form>
	</c:otherwise>
</c:choose> </main>

	<jsp:include page="footer.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>
</body>
</html>
