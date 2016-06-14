<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ye Olde Bookseller Address Form</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<div id=header>
	<%@ include file="bannerHome.jsp"%>
</div>
<body>
	<p>
		Welcome to
		<fmt:message key="title" />
		, please enter your address
	</p>
	<form:form method="post" modelAttribute="creditCard">
		<div style="width: 500px; text-align: right">
			<div class="formfield">
				<form:label path="cardName">Name on Card:</form:label>
				<form:input path="cardName" />
			</div>
			<div class="formfield">
				<form:label path="cardType">Card Type:</form:label>
				<form:password path="cardType" />
			</div>
			<div class="formfield">
				<form:label path="cardNumber">Card Number:</form:label>
				<form:input path="cardNumber" />
			</div>
			<div class="formfield">
				<form:label path="cardSecurityNum">Security Number:</form:label>
				<form:password path="cardSecurityNum" />
			</div>
			<div class="formfield">
				<form:label path="cardExpDate">Exp Date:</form:label>
				<form:input path="cardExpDate" />
			</div>
			<div class="formfield">

				<input type="submit" value="Submit Credit Card" />
			</div>
		</div>
	</form:form>

	<%@ include file="footer.jsp"%>
</body>
</html>