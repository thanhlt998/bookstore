<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

	<!-- View profile -->
	<section id="profile" style=" margin-top: 54px; min-height: 100vh;">
		<div class="container h-100 w-100">
			<div class="row h-100">
				<div class="col-md-3 py-5 mt-5">
					<div class="list-group align-middle mt-5">
						<li class="list-group-item active" data-target="#view-profile">View
							profile</li>
						<li class="list-group-item" data-target="#change-password">Change
							Password</li>
						<li class="list-group-item" data-target="#order-history">View
							Order History</li>
						<li class="list-group-item" data-target="#order-detail">Order
							Detail</li>
					</div>
				</div>
				<div class="col-md-9 py-5">
					<div class="row" id="view-profile">
						<div class="ml-5">
							<i class="fas fa-user-circle fa-7x text-secondary"></i> <span class="display-4 ml-3">Your Profile</span>
						</div>
						<div class="table-responsive">
							<table class="table table-hover mx-5" id="table-profile">
								<thead>
									<td style="width: 60px;"></td>
									<td></td>
									<td style="width: 50px;"></td>
								</thead>
								<tbody>
									<tr>
										<td>Username</td>
										<td><span>${user.username }</span>
											<div class="inputChange mt-2">
												<input class="form-control" value="${user.username }" disabled>
											</div>
										</td>
										<td><i class="far fa-edit"></i></td>
									</tr>
									<tr>
										<td>Name</td>
										<td><span>${user.name }</span>
											<div class="inputChange mt-2">
												<input type="text" class="form-control changeField" id="name" name="name" value="${user.name }">
											</div>
										</td>
										<td><i class="far fa-edit"></i></td>
									</tr>
									<tr>
										<td>Email</td>
										<td><span>${user.email }</span>
											<div class="inputChange mt-2">
												<input type="email" class="form-control changeField" id="email" name="email" value="${user.email }">
											</div>
										</td>
										<td><i class="far fa-edit"></i></td>
									</tr>
									<tr>
										<td>Birth Date</td>
										<td><span>
												<fmt:formatDate value="${user.birthDate }" pattern="yyyy-MM-dd" /></span>
											<div class="inputChange mt-2">
												<input type="date" class="form-control changeField" id="birthDate" name="birthDate" value="<fmt:formatDate value="
												 ${user.birthDate }" pattern="yyyy-MM-dd" />">
											</div>
										</td>
										<td><i class="far fa-edit"></i></td>
									</tr>
									<tr>
										<td>Gender</td>
										<td><span>${user.gender }</span>
											<div class="inputChange mt-2">
												<select type="gender" class="form-control changeField" id="gender" name="gender" id="gender" required>
													<option value="MALE" selected>Male</option>
													<option value="FEMALE">Female</option>
													<option value="OTHER">Other</option>
												</select>
											</div>
										</td>
										<td><i class="far fa-edit"></i></td>
									</tr>
									<tr>
										<td>Address</td>
										<td><span>${user.address }</span>
											<div class="inputChange mt-2">
												<input type="text" class="form-control changeField" id="address" name="address" value="${user.address }">
											</div>
										</td>
										<td><i class="far fa-edit"></i></td>
									</tr>
									<tr>
										<td>Phone</td>
										<td><span>${user.phone }</span>
											<div class="inputChange mt-2">
												<input type="text" class="form-control changeField" id="phone" name="phone" value="${user.phone }">
											</div>
										</td>
										<td><i class="far fa-edit"></i></td>
									</tr>
									<tr>
										<td colspan="3" class="text-right">
											<button class="btn btn-outline-dark" id="save-changes-button" context-path="${pageContext.request.contextPath}"
											 disabled>Save
												changes</button> <br> <small id="table-profile-feedback" class="text-muted"></small>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div class="row" id="change-password" style="display: none;">
						<div class="col-md-8 offset-md-2">
							<div class="mb-3">
								<i class="fas fa-unlock-alt fa-7x text-secondary"></i><span class="display-4 ml-3">Change password</span>
								<hr>
							</div>
							<div class="form-group">
								<label for="old-password">Old Password</label>
								<input type="text" class="form-control" name="old-password" id="old-password">
								<small class="input-form-text text-muted" id="old-password-feedback"></small>
							</div>
							<div class="form-group">
								<label for="new-password">New Password</label>
								<input type="text" class="form-control" name="new-password" id="new-password">
								<small class="input-form-text text-muted" id="new-password-feedback"></small>
							</div>
							<div class="form-group">
								<label for="confirm-new-password">New Password</label>
								<input type="text" class="form-control" name="confirm-new-password" id="confirm-new-password">
								<small class="input-form-text text-muted" id="confirm-new-password-feedback"></small>
							</div>
							<div class="text-right">
								<button class="btn btn-outline-dark" id="change-password-button" context-path="${pageContext.request.contextPath }">Change
									password</button>
								<br><span id="change-password-feedback"></span>
							</div>
						</div>
					</div>
					<div class="row" id="order-history" style="display: none">
						<div class="col">
							<i class="fas fa-history fa-5x text-secondary ml-3"></i><span class="display-4 ml-3">Order History</span>
							<div class="table-responsive">
								<table class="table table-striped my-3">
									<thead>
										<td>Order Id</td>
										<td>Order Date</td>
										<td>Ship Date</td>
										<td>Ship Address</td>
										<td>Total amount</td>
										<td>Payment Method</td>
										<td>Order Status</td>
									</thead>
									<tbody>

									</tbody>
								</table>
							</div>
							<div class="text-right">
								<button class="btn btn-dark" id="view-order-history-button" page="0" context-path="${pageContext.request.contextPath}">View
									Order History</button>
							</div>
						</div>
					</div>
					<div class="row" id="order-detail" style="display: none;">
						<div class="col">
							<i class="fas fa-info-circle fa-5x text-secondary mr-3"></i><span class="display-4">View Order Detail</span>
							<hr>
							<div class="form-inline mb-3">
								<input type="number" class="form-control mr-4 ml-auto" id="order-id-input">
								<button class="btn btn-secondary view-order-button" context-path="${pageContext.request.contextPath}" id="view-order-button" disabled>View
									Order</button>
							</div>
							<div id="order-detail-view" style="display: none;">
								<div class="row mb-3">
									<div class="col-md-6">
										<h4>Thông tin thanh toán</h4>
										<p>
											<span class="name">Lorem ipsum dolor sit.</span><br>
											<span class="email">abcd@gmail.com</span><br>
											<span class="phone">0123456789</span>
										</p>
									</div>
									<div class="col-md-6">
										<h4>Địa chỉ giao hàng</h4>
										<p>
											<span class="name">Lorem ipsum dolor sit.</span><br>
											<span class="email">abcd@gmail.com</span><br>
											<span class="address">1231 Ha Noi</span>
											<span class="phone">0123456789</span>
										</p>
									</div>
								</div>
								<p>
									Phương thức thanh toán: <span class="paymentMethod"></span><br>
									Thời gian giao hàng: <span class="shipDate"></span>
								</p>
								<h3>Chi tiết đơn hàng</h3>
								<div class="table-responsive">
									<table class="table table-striped">
										<thead>
											<td>No.</td>
											<td>Book</td>
											<td>Quantity</td>
											<td>Price Per Unit</td>
											<td>Amount</td>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
							</div>
							<span class="display-4" id="order-detail-feedback"></span>
						</div>
					</div>
				</div>
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

	</script>
</body>


</html>