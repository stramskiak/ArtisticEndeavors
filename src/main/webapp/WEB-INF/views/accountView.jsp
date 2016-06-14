<%@ include file="include.jsp"%>
<html>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/craftsmain.css" />" />
<head>
<title>About You - Account Info</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<nav onload="incart();">
	<div id="wrapper">
		<%@ include file="bannerHome.jsp"%>
		<hr>
		<small><i> <font color="black"><fmt:message
						key="infoview" /> </font></i></small>
		<hr>
	</div>
</nav>
<body>

	<p>Please note that email and phone are required at checkout.</p>
	<form:form method="GET" modelAttribute="user">
		<div style="width: 500px; text-align: right">

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

			<div class="formfield">
				<form:label path="email">eMail:</form:label>
				<form:input path="email" value="${user.email}" />
				<small> *required at checkout</small>
			</div>
			<div class="errors">
				<form:errors path="email" />
			</div>
			<%-- 
			<div class="formfield">
				<form:label path="phone">Phone:</form:label>
				<form:input path="phone" value="${user.phone}" /><small> *required at checkout</small>
			</div>

			<input type="submit" value="Update User Information" />
		</div>
		<br>
		<div style="text-align: left">
			<c:choose>
				<c:when test="${empty addressList}">
					<div>
						<a href="<c:url value="address" />">New Address</a>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<a href="<c:url value="address" />">New Address</a>
					</div>
					<div class="view_addresses">
						<input type="button" value="Select from saved addresses">
					</div>
					<br>
					<div class="show_addresses"></div>
					<br>
				</c:otherwise>
			</c:choose>
		</div>
		<div style="text-align: left">
			<c:choose>
				<c:when test="${empty creditCardList}">
					<div>
						<a href="<c:url value="creditCard"/>">New Credit Card</a>
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<a href="<c:url value="creditCard"/>">New Credit Card</a>
					</div>
					<div class="view_credit">
						<input type="button" value="Select from saved credit card">
					</div>
					<br>
					<div class="show_credit"></div>
					<br>
				</c:otherwise>
			</c:choose>
			--%>
		</div>
	</form:form>

	<%@ include file="footer.jsp"%>
</body>
<script type="text/javascript">
	$(document).ready(function($) {
		$('div.view_addresses').click(function() {
			$.ajax({
				type : 'POST',
				url : 'viewAddresses',
				cache : 'false',
				success : function(response) {
	               	  $.each(response, function(idx) {
	                      $('div.show_addresses').append("<div id=textblock>")
	                      .append(response[idx].text).append("<br>");
	               		  });
				},
				error : function() {
					alert('Cannot view address');
				}
			});
		});
		$('div.view_credit').click(function() {
			$.ajax({
				type : 'POST',
				url : 'viewCredit',
				data : {
					'username' : '${ username }'
				},
				cache : 'false',
				success : function(response) {
					$('div.show_credit').append(response.text);
				},
				error : function() {
					alert('Cannot view credit');
				}
			});
		});
	});
</script>
</html>