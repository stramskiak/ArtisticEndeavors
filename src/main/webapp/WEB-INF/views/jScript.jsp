<%-- <%@ include file="include.jsp"%> --%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js">
	
</script>
<script type="text/javascript">
	$(document).ready(function($) {
		$('div.add_to_cart').click(function() {
			$.ajax({
				type : 'PUT',
				url : 'api/favs/${item.itemid}',
				cache : 'false',
				success : function(response) {
					alert('Item Added To Favorites!');
					incart();
				},
				error : function() {
					alert('Item Not Added To Favorites');
				}
			});
		});
	});
	$(document).ready(
			function($) {
				$('div.see_reviews').click(
						function() {
							$.ajax({
								type : 'POST',
								url : 'reviews',
								data : {
									'isbn' : '${item.itemid}'
								},
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
<%-- response based on http://www.sitepoint.com/use-jquerys-ajax-function/ --%>
	$(document).ready(
			function infavs() {
				$.ajax({
					type : 'POST',
					url : 'api/favsNum',
					cache : 'false',
					success : function(response) {
						$('div.in_favs').empty();
						$('div.in_favs').append(
								"<a href=&#8220#favs&#8221>Favorites List ("
										+ response + ")</a>");
					},
					error : function() {
						alert('favesNum failed');
					}
				});
			});
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
