<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	 crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	 crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.css" />
</head>

<body>

	<jsp:include page="navbar.jsp"></jsp:include>

	<section id="viewCart" style="min-height: 100vh;">
		<div class="container my-5 p-5">
			<div class="row p-1">
				<h3 class="display-4 mb-3">Your cart detail</h3>
				<table class="table">
					<thead class="text-center">
						<tr>
							<td style="width: 150px;">Image</td>
							<td>Book Name</td>
							<td>Price per Unit</td>
							<td>Quantity</td>
							<td>Amount</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cartItem" items="${cart.cart }">
							<tr>
								<td class="text-center align-middle"><img src="${pageContext.request.contextPath }/resources/images/${cartItem.book.imageUrl}.jpg"
									 alt="" class="img-fluid"></td>
								<td class="align-middle text-center">${cartItem.book.bookName }</td>
								<td class="align-middle text-center">${cartItem.book.currentPrice }</td>
								<td class="align-middle text-center">${cartItem.quantity }</td>
								<td class="align-middle text-center">${cartItem.book.currentPrice * cartItem.quantity }</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="4" class="text-center">Total Price</td>
							<td>${cart.totalPrice }</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="4" class="text-right">
								<sec:authorize access="isAuthenticated()">
									<c:if test="${cart.totalQuantity > 0 }">
										<button class="btn btn-outline-danger" id="order-info-button">Order</button>
									</c:if>
								</sec:authorize>
								<sec:authorize access="!isAuthenticated()">
									<p>
										Please login to order <a href="" data-dismiss="modal" data-toggle="modal" data-target="#loginModal">Already
											have
											an account?</a>
									</p>
								</sec:authorize>
								<div class="info-form text-left mb-5" style="display: none;">
									<div class="form-group">
										<label for="shipAddress">Ship address</label> <input type="text" class="form-control" name="shipAddress" id="shipAddress"
										 placeholder="Ship Address">
									</div>
									<div class="form-group">
										<label for="paymentMethod">Payment Method</label> <select name="paymentMethod" id="paymentMethod" class="form-control"
										 required>
											<option value=""></option>
											<option value="COD">Cash on delivery</option>
											<option value="INTERNET_BANKING">Internet Banking</option>
										</select> <br>
									</div>
									<div class="text-right">
										<button class="btn btn-outline-danger" id="confirm-order-button">Order</button>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</section>

	<jsp:include page="footer.jsp"></jsp:include>

	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	 crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	 crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	 crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.js"></script>
	<script src="${pageContext.request.contextPath }/resources/javascript/script.js"></script>

	<script>
		$("#username").blur({
			url: "${pageContext.request.contextPath}/checkAvailableUsername"
		}, validateUsernameField);

		$(".add-cart").click({
			url: "${pageContext.request.contextPath }/addCart"
		}, addCart);

		$("#confirm-order-button").click({
			url: "${pageContext.request.contextPath }/"
		}, createOrder);
	</script>
</body>

</html>