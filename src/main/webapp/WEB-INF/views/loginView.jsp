<%@ include file="include.jsp"%>
<html>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/craftsmain.css" />" />
<head>
<title>About You - Login Form</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<nav onload="incart();">
	<div id="wrapper">
		<%@ include file="bannerHome.jsp"%>
		<hr>
		<small><i> <font color="black"><fmt:message
						key="loginview" /> </font></i></small>
		<hr>
	</div>
</nav>
<body>

	<p>
		Please enter your username and password,<br> email and phone are
		required at checkout.
	</p>
	<form:form method="post" modelAttribute="user">
		<div style="width: 300px; text-align: right">

			<div class="formfield">
				<form:label path="username">Name:</form:label>
				<form:input path="username" value="${user.username}" />
			</div>
			<div class="errors">
				<form:errors path="username" />
			</div>

			<div class="formfield">
				<form:label path="password">Password:</form:label>
				<form:password path="password" value="${user.password}" />
			</div>
			<div class="errors">
				<form:errors path="password" />
			</div>

			<input type="submit" value="Submit Login" />
		</div>
	</form:form>

	<%@ include file="footer.jsp"%>
</body>
</html>