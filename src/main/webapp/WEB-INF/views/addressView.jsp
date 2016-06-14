<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LeatherSwan Address Form</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<div id=header>
	<%@ include file="bannerHome.jsp"%>
</div>
<body>
	<p>Address Information</p>
	<form:form method="post" modelAttribute="address">
		<div style="width: 300px; text-align: right">

			<div class="formfield">
				<form:label path="addressName">Name:</form:label>
				<form:input path="addressName" />
			</div>

			<div class="formfield">
				<form:label path="addressStreet">Street:</form:label>
				<form:input path="addressStreet" />
			</div>

			<div class="formfield">
				<form:label path="addressCity">City:</form:label>
				<form:input path="addressCity" />
			</div>

			<div class="formfield">
				<form:label path="addressState">State:</form:label>
				<form:input path="addressState" />
			</div>

			<div class="formfield">
				<form:label path="addressZipcode">Zip Code:</form:label>
				<form:input path="addressZipcode" />
			</div>
			<div class="errors">
				<form:errors path="addressZipcode" />
			</div>

			<div class="formfield">
				<input type="submit" value="Submit Address" />
			</div>

		</div>
	</form:form>

	<%@ include file="footer.jsp"%>
</body>
</html>