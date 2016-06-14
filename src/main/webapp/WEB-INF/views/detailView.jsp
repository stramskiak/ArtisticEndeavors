<%@ include file="include.jsp"%>
<html>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/craftsmain.css" />" />
<head>
<title>Artistic Detail</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<nav onload="incart();">
	<div id="wrapperdetail">
		<%@ include file="bannerHome.jsp"%>
		<hr>
		<small><i> <font color="black"><fmt:message
						key="ondetail" /> </font></i></small>
		<hr>
	</div>
</nav>
<body>
	<div id=image>
		<img width="350px" src="<c:url value='${item.image}' />"
			alt="No image available"> <br>
	</div>
	<div id="section">
		<strong>Title: ${item.title}</strong><br>
				<strong>Artist: ${item.artist} </strong><br>
		<strong>Price: <fmt:formatNumber value="${item.price}"
				type="currency" /></strong><br> <strong>Detail:
			${item.description}</strong><br>
		<c:choose>
			<c:when test="${empty username}">
				<font color="gray"><i>Login to place in shopping cart</i></font>
			</c:when>
			<c:otherwise>
				<c:url value="/addtocart" var="addQuantityURL">
					<c:param name="isbn" value="${item.isbn}"></c:param>
				</c:url>
				<a href="<c:url value="${addQuantityURL}"/>"> <i>click here
						to place in shopping cart</i></a>
			</c:otherwise>
		</c:choose>
	</div>
	<div id=footer>
		<hr>
		<div class="clickme">Click me</div>
		<div class="review"></div>
		<br>
		<div class="see_reviews">
			<input type="button" value="Click to see reviews!">
		</div>
		<div class="show_reviews"></div>
		<br>
		<c:choose>
			<c:when test="${empty username}">
				<font color="gray"><i>Login to add your review!</i></font>
			</c:when>
			<c:otherwise>
				<div class="add_review">
					<input type="button" value="add_review">
					<textarea rows="3" cols="80" id="reviewtext">Add Your Review</textarea>
				</div>
			</c:otherwise>
		</c:choose>
		<%--	<p>Please provide a review of this item:</p>
	<div class="new_review">
		<input type="text" />
	</div>
	Add Your Review: <input type="text" name="reviewtext"><br>
	<div class="add_review">
	<input type="button" value="Submit"></div> --%>
		<hr>
		<div>
			<a href="<c:url value="/"/>">Return to Item List</a>
		</div>
	</div>
	<div id=footer>
		<hr>
		<%@ include file="footer.jsp"%></div>
</body>
<script type="text/javascript">
$(document).ready(function($) {
    $('div.clickme').click(function() {
        $.ajax({
            type : 'POST',
            url : 'review',
            data : {
                'isbn':'${ item.isbn }'
            },
            cache : 'false',
            success : function(response) {
                $('div.review').append(response.text);
            },
            error : function() {
                alert('Whoops');
            }
        });  
    });
});
</script>
<script type="text/javascript">
  $(document).ready(function($) {
        $('div.see_reviews').click(function() {
            $.ajax({
                type : 'POST',
                url : 'reviews',
                data : {
                    'isbn':'${item.isbn}'
                },
                dataType : 'json',
                cache : 'false',
                success : function(response) {
               	  $.each(response, function(idx) {
                      $('div.show_reviews').append("<div id=textblock>").append(response[idx].date).append("<br>");
                      $('div.show_reviews').append(response[idx].username).append("<br>");
                      $('div.show_reviews').append(response[idx].text).append("</div>");
               		  });
                },
                error : function() {
                    alert('No Reviews To Show');
                }
            });  
        });
    });
  </script>
<%-- response based on http://www.sitepoint.com/use-jquerys-ajax-function/ --%>
<script type="text/javascript">
    $(document).ready(function($) {
        $('div.add_review').submit(function() {
                $.ajax({
                    type : 'POST',
                    url : 'add_review',
                    data : {
                        'isbn':'${ item.isbn }',
                        'username':'${ username }',
                        'reviewtext':'${ document.getElementById("reviewtext").value }'
                    },
                    dataType : 'json',
                    cache : 'false',
                    success : function(response) {
                     	  $.each(response, function(idx) {
                              $('div.show_reviews').append("<div id=textblock>").append(response[idx].date).append("<br>");
                              $('div.show_reviews').append(response[idx].username).append("<br>");
                              $('div.show_reviews').append(response[idx].text).append("</div>");
                       		  });
                   },
                    error : function() {
                        alert('No Review Added');
                    }
                });  
            });
    });
    </script>
</html>
