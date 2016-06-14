<%@ include file="include.jsp"%>
<div style="text-align: right">
	<c:choose>
		<c:when test="${empty username}">
			<p>
				<a href="<c:url value="/login"/>">Login</a>
			</p>
		</c:when>
		<c:otherwise>
			<div>
				<fmt:message key="hello" />
				${ username } (<a href="<c:url value="login"/>"
					style="font-size: 16px">User Info</a>) (<a
					href="<c:url value="logout"/>" style="font-size: 16px">Logout</a>)
			</div>
		</c:otherwise>
	</c:choose>
	<%-- <br> --%>
</div>
