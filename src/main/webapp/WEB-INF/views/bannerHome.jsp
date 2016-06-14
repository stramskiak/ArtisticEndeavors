<%@ include file="include.jsp"%>
<%-- GLOBAL NAV BAR --%>
<!-- 	<div id="wrapper"> -->
<!-- LOGO -->
<div id="logo">
	<a href="/ArtisticEndeavors">LeatherSwan Artistic Endeavors</a>
</div>

<!-- DROP DOWN MENU -->
<ul>
	<!-- MENU List Item: HOME -->
	<li><a href="home" Title="GoTo or Refresh the Home Page">Home</a></li>
	<!-- MENU List Item: LOGIN <or> ACCOUNT -->
	<c:choose>
		<c:when test="${empty username}">
			<li><a href="<c:url value="userLogin" />"
				Title="Login for personal info and more!"> Login</a></li>
		</c:when>
		<c:otherwise>
			<li><a>My Information</a>
				<ul>
					<li><a href="view_account" Title="View My Information">Account
							Info</a></li>
					<li><a href="address"
						Title="Enter or Edit Address Information">Address Form</a></li>
					<li><a href="/logout">LogOut</a></li>
				</ul></li>
		</c:otherwise>
	</c:choose>
	<!-- MENU List Item: Favorites (#) -->
	<li><div class="in_favs">
			<a>Favorites (a)</a>
		</div> <!-- MENU List Item: Favorites (#) -->
	<li><a>Favorites (<fmt:formatNumber
				value="${ favs.numFavsItems() }" type="number" />)
	</a>
		<ul>
			<c:choose>
				<c:when test="${empty username}">
					<!-- MENU List Item: Favorites (#) (Ghost) View Favorites -->
					<li><a href="/leatherswan" Title="Login to use Favorites list"> <font
							color="pink">View Favorites</font></a></li>
				</c:when>
				<c:otherwise>
					<!-- MENU List Item: Favorites (#) (Select) View Favorites -->
					<li><a href="gotofavs">View Favorites</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${empty username}">
					<!-- MENU List Item: FAVORITES LIST (#) (Ghost) Clear -->
					<li><a href="/leatherswan" Title="Login to process Favorites">
							<font color="pink">Checkout</font>
					</a></li>
				</c:when>
				<c:otherwise>
					<!-- MENU List Item: FAVORITES LIST (#) (Select) Clear -->
					<li><a href="clearFavs">Clear your Favorites List</a></li>
				</c:otherwise>
			</c:choose>
		</ul></li>
	<!-- MENU List Item: LogOut -->
	<li><a href="logout">LogOut</a></li>
</ul>
<!-- 	</div> -->
