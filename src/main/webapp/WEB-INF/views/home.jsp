<%@ include file="include.jsp"%>
<html>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/craftsmain.css" />" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js">
	
</script>
<%-- response based on http://www.sitepoint.com/use-jquerys-ajax-function/ --%>
<script type="text/javascript">
function infavs() {
	$.ajax({
		type : 'POST',
		url : 'api/favoritesNum',
		cache : 'false',
		success : function(response) {
			$('div.in_favs').empty();
			$('div.in_favs').append(
					"<a>Favorites (" + response
							+ ")</a>");
		},
		error : function() {
			alert('find favoritesNum failed');
		}
	});
};
<%--
$(document).ready(function($) {
	$('div.add_to_favs').click(function() {
		$.ajax({
			type : 'PUT',
			url : 'api/favs/${item.itemid}',
			cache : 'false',
			success : function(response) {
				alert('Success: Item Added To Favorites!');
				incart();
			},
			error : function() {
				alert('Error: Item Not Added To Favorites');
			}
		});
	});
});
--%>
	</script>
<head>
<title>Artistic Endeavors</title>
</head>

<nav onload="infavs();">
	<div id="wrapper">
		<%@ include file="bannerHome.jsp"%>
		<hr>
		<small><i> <font color="black"><fmt:message
						key="instruction" /> </font></i></small>
		<hr>
	</div>
</nav>
<body>
	<div id="wrapper">
		<!-- <div class="main" style="background-color: #F5F5F5"> -->
		<div class="main">
			<c:forEach items="${items}" var="item">
				<div class="frame">
					<div class="frame-back">
						<img class="frame-back" src="<c:url value='${item.image}' />"
							style="position: absolute">

					</div>
					<a href="see_details?itemid=${item.itemid}"> <img
						class="image50" src="<c:url value='${item.image}' />"
						alt="No image available">
					</a>

					<div class="desc">
						<strong> <a href="see_details?itemid=${item.itemid}">
								<c:out value="${item.title}" />
						</a></strong> <br> <strong>Artist: </strong>
						<%-- 				<c:out value="${item.artist}" /> --%>
						/> <br> <strong>Price: </strong>
						<fmt:formatNumber value="${item.price}" type="currency" />
						<br>
						<c:choose>
							<c:when test="${ item.discount > 0.0 }">
								<strong>Discount: <i><font color=red><fmt:formatNumber
												value="${item.discount * 100}" type="number" />%</font></i>
								</strong>
								<br>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${empty username}">
								<font color="gray"><i>Login to place in Favorites
										list</i></font>
								<br>
							</c:when>
							<c:otherwise>
								<c:url value="addtofavs" >
									<c:param name="itemid" value="${item.itemid}"></c:param>
								</c:url>
								<a href="<c:url value="${addtofavs}"/>"> <small><i>place
											in Favorites list</i></small></a>
								<br>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="footer">
			<hr>
			<%@ include file="footer.jsp"%>
			<hr>
		</div>
	</div>
</body>

</html>
