<%@ include file="include.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/bookstore.css" />
<title><fmt:message key="title" /></title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js">
	
</script>
<%-- <%@ include file="jScript.jsp"%>  --%>
<script type="text/javascript">
	$(document).ready(function($) {
		$('div.add_to_cart').click(function() {
			$.ajax({
				type : 'PUT',
				url : 'api/cart/${book.isbn}',
				cache : 'false',
				success : function(response) {
					alert('Item Added To Cart!');
					incart();
				},
				error : function() {
					alert('Item Not Added To Cart');
				}
			});
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(
			function($) {
				$('div.see_reviews').click(
						function() {
							$.ajax({
								type : 'POST',
								url : 'reviews',
								data : {'isbn' : '${book.isbn}'},
								dataType : 'json',
								cache : 'false',
								success : function(response) {
									$('div.show_reviews').empty();
									$.each(response, function(idx) {
										$('div.show_reviews').append(
												"<div id=textblock>").append(
												response[idx].date).append(
												"<br>");
										$('div.show_reviews').append(
												response[idx].username).append(
												"<br>");
										$('div.show_reviews').append(
												response[idx].text).append(
												"</div>");
									});
								},
								error : function() {
									alert('No Reviews To Show');
								}
							});
						});
			});
</script>
<script type="text/javascript">
<%-- response based on http://www.sitepoint.com/use-jquerys-ajax-function/ --%>
	function incart() {
		$.ajax({
			type : 'POST',
			url : 'api/cartNum',
			cache : 'false',
			success : function(response) {
				$('div.in_cart').empty();
				$('div.in_cart').append(
						"<a href=&#8220#cart&#8221>Shopping Cart (" + response
								+ ")</a>");
			},
			error : function() {
				alert('cartNum failed');
			}
		});
	};
</script>
<script type="text/javascript">
	$(document).ready(
		function($) {
			$('div.view_addresses').click(function() {
				$.ajax({
					type : 'GET',
					url : 'viewAddresses',
					cache : 'false',
					success : function(response) {
						$('div.show_addresses').empty();
						$.each(response,function(idx) {
							$('div.show_addresses')
								.append("<div>")
								.append("Entry: ")
								.append(response[idx].addressEntry)
								.append("<br>")
								.append("Name: ")
								.append(response[idx].addressName)
								.append("<br>")
								.append("Street: ")
								.append(response[idx].addressStreet)
								.append("<br>")
								.append("City: ")
								.append(response[idx].addressCity)
								.append("<br>")
								.append("State: ")
								.append(response[idx].addressState)
								.append("<br>")
								.append("Zip: ")
								.append(response[idx].addressZipcode)
								.append("<br>")
								.append("Is Billing Address: ")
								.append(response[idx].isBillingAddress)
								.append("<br>")
								.append("</div>");
							});
						},
						error : function() {
							alert('Cannot view address');
							}
				});
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function($) {
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
<script type="text/javascript">
	$(document).ready(function($) {
		$('div.addone').click(function add(isbn) {
			$.ajax({
				type : 'POST',
				url : 'incrementQuantity',
				data : {
					'isbn=' : isbn
				},
				cache : 'false',
				success : function(response) {
					$('div.count').refresh(response.number);
				},
				error : function() {
					alert('Cannot Add One');
				}
			});
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function($) {
		$('div.subone').click(function sub(itemid) {
			$.ajax({
				type : 'POST',
				url : 'decrementQuantity',
				data : {
					'itemid' : '${ item.itemid }'
				},
				cache : 'false',
				success : function(response) {
					$('div.subone').Number(response.quantity);
				},
				error : function() {
					alert('Whoops');
				}
			});
		});
	});
</script>
<body onload="infavs()">
	<%@ include file="bannerHome.jsp"%>
	<hr>
	<small><i><fmt:message key="instruction" /></i></small>
	<hr>
	<c:forEach items="${items}" var="item">
		<div class="img">
			<a href="see_details?isbn=${item.itemid}"> <img height="250px"
				src="<c:url value='${item.image}' />" alt="No image available">
			</a>

			<div class="desc">
				<strong> <a href="see_details?itemid=${item.itemid}"> <c:out
							value="${item.title}" /></a></strong> <br> <strong>Artist: </strong>
				<c:out value="${item.artist}" />
				<br> <strong>Price: </strong>
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
						<font color="gray"><i>Login to place item in a favorites list</i></font>
						<br>
					</c:when>
					<c:otherwise>
						<div class="add_to_favs">
							<input type="button" value="Place in favorites list">
						</div>
						<br>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</c:forEach>
</body>
<footer><%@ include file="footer.jsp"%></footer>

</html>
