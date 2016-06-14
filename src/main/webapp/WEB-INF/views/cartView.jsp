<%@ include file="include.jsp"%>
<html>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/craftsmain.css" />" />
<head>
<title><fmt:message key="cart" /></title>
</head>
<body>
	<%@ include file="bannerHome.jsp"%>
	<hr>
	<div>
		<a href="<c:url value="/"/>">Return to Item List</a>
	</div>
	<hr>

	<c:choose>
		<c:when test="${ empty favs.favItems }">
			<p>Favorites List is empty</p>
		</c:when>
		<c:otherwise>

			<form:form method="POST" modelAttribute="favs">

				<table border="1">
					<tr height="40px">
						<td align="center">Title</td>
						<td align="center">Price</td>
						<td align="center">Quantity</td>
						<td align="center">Discount</td>
						<td align="center">Net Cost</td>
					</tr>
					<%--			for cart=null pring cart is empty  --%>
					<c:forEach items="${cartItems}" var="cItem" varStatus="idx">
						<tr height="40px">
							<td align="left" style="font-size: 20px"><strong>${cItem.book.title}</strong>
							</td>
							<td align="right" style="font-size: 20px"><fmt:formatNumber
									value="${cItem.book.price}" type="currency" /></td>
							<td align="center" style="font-size: 20px"><c:url
									value="incrementQuantity" var="addOne">
									<c:param name="isbn" value="${cItem.book.isbn}"></c:param>
								</c:url> <a href="<c:url value="${addOne}"/>">(+) </a> <fmt:formatNumber
									value="${cItem.quantity}" type="number" /> <c:url
									value="decrementQuantity" var="subOne">
									<c:param name="isbn" value="${cItem.book.isbn}"></c:param>
								</c:url> <a href="<c:url value="${subOne}"/>"> (-)</a></td>
							<td align="center" style="font-size: 20px"><fmt:formatNumber
									value="${cItem.book.discount*100}" type="number" />%</td>
							<td align="right" style="font-size: 20px"><fmt:formatNumber
									value="${cItem.cost}" type="currency" /></td>
						</tr>

					</c:forEach>
					<tr height="40px">
						<td align="right" style="font-size: 20px" colspan=5><strong>Total
								cost of Items in cart: <fmt:formatNumber
									value="${totalItemCost}" type="currency" />
						</strong></td>
					</tr>
					<tr height="40px">
						<td align="right" style="font-size: 20px" colspan=5><strong>TAX:
								<fmt:formatNumber value="${totalTAX}" type="currency" />
						</strong></td>
					</tr>
					<tr height="40px">
						<td align="right" style="font-size: 20px" colspan=5><strong>SHIPPING:
								<fmt:formatNumber value="${totalShipping}" type="currency" />
						</strong></td>
					</tr>
					<tr height="40px">
						<td align="right" style="font-size: 20px" colspan=5><strong>Total
								Due: <fmt:formatNumber
									value="${totalItemCost+totalShipping+totalShipping}"
									type="currency" />
						</strong></td>
					</tr>
				</table>
			</form:form>
		</c:otherwise>
	</c:choose>
	<input type="submit" value="Checkout" />


</body>
<%@ include file="footer.jsp"%>
</html>
