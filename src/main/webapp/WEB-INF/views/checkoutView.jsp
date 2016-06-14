<%@ include file="include.jsp"%>
<html>
<head>
<title><fmt:message key="checkout" /></title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function($) {
        $('div.addone').click(function add(isbn) {
            $.ajax({
                type : 'POST',
                url : 'incrementQuantity',
                data : {
                    'isbn=': isbn
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
        $('div.subone').click(function sub(isbn) {
            $.ajax({
                type : 'POST',
                url : 'decrementQuantity',
                data : {
                    'isbn':'${ book.isbn }'
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
<%@ include file="bannerHome.jsp"%>
<body>
	<hr>
	<div>
		<a href="<c:url value="/"/>">Return to Book List</a>
	</div>
	<hr>
	<br>
	<table border="1">
		<tr height="40px">
			<td align="center">Item Title</td>
			<td align="center">Item Price</td>
			<td align="center">Item Quantity</td>
			<td align="center">Item Discount</td>
		</tr>
		<c:forEach items="${cart.cartItems}" var="cItem">
			<tr height="40px">
				<td align="left" style="font-size: 20px"><strong>${cItem.itemTitle}</strong>
				</td>
				<td align="right" style="font-size: 20px"><fmt:formatNumber
						value="${cItem.cost}" type="currency" /></td>
				<td align="center" style="font-size: 20px"><c:url
						value="incrementQuantity" var="addOne">
						<c:param name="cartItemID" value="${cItem.itemID}"></c:param>
					</c:url> <a href="<c:url value="${addOne}"/>">(+) </a> <fmt:formatNumber
						value="${cItem.quantity}" type="number" /> <c:url
						value="decrementQuantity" var="subOne">
						<c:param name="cartItemID" value="${cItem.itemID}"></c:param>
					</c:url> <a href="<c:url value="${subOne}"/>"> (-)</a></td>
				<td align="center" style="font-size: 20px"><fmt:formatNumber
						value="${cItem.discount*100}" type="number" />%</td>
			</tr>

		</c:forEach>
	</table>
</body>
<%@ include file="footer.jsp"%>
</html>
