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
				<li>${error }</li>
			</c:forEach>
		</ul>
	</div>
</c:if> <c:choose>
	<c:when test="${user!=null}">
		<p id="test">Welcome ${user.getFirstName()}!</p>

		<form >
			<h2>Change your status</h2>
			<input type="radio" id="online" name="status" value="online">
			<label for="online">online</label><br>
			<input type="radio" id="offline" name="status" value="offline">
			<label for="offline">offline</label><br>
			<input type="radio" id="away" name="status" value="away">
			<label for="away">away</label><br>
			<input type="radio" id="busy" name="status" value="busy">
			<label for="busy">busy</label><br>
			<input type="radio" id="in les" name="status" value="in les">
			<label for="in les">in les</label><br>

			<input id="setStatus" type="button" value="set status">

			<p id="showStatus"></p>
		</form>

		<form>
			<h2>Add a person to your friend list</h2>
			<label for="naam"> naam van persoon</label>
			<input type="text" id="naam" placeholder="naam"><br>
			<input type="button" id="addFriend" value="add friend to friendlist">
		</form>

		<table id="allFriends">
		</table>
		<input type="button" id="showFriendlist" value="change visibility friendlist">
		<div class="chatbox">
			<div class="outputMessage" ></div>
			<input type="text" class="inputMessage">
			<button class="sendMessage">send message</button>
		</div>



		<form method="post" action="Controller?action=LogOut">
			<p>
				<input type="submit" id="logoutbutton" value="Log Out">
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
		</form>
	</c:otherwise>
</c:choose> </main>

	<jsp:include page="footer.jsp">
		<jsp:param name="title" value="Home" />
	</jsp:include>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript" src="js/index_t1.js"></script>
</body>
</html>