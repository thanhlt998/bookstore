<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

	<!-- Admin menu -->
	<section id="admin-section" style="min-height: 100vh; margin-top: 54px;">
		<div class="row p-2">
			<div class="col-md-2">
				<div class="text-center my-3"><i class="fas fa-user-circle fa-5x text-secondary"></i></div>
				<div class="list-group">
					<li class="list-group-item active" data-target="#view-all-users">Users Management</li>
					<li class="list-group-item" data-target="#view-all-orders">Orders Management</li>
					<li class="list-group-item" data-target="#order-detail">Order Details Management</li>
					<li class="list-group-item" data-target="#product-management">Product Management</li>
				</div>
			</div>
			<div class="col-md-10 p-4">
				<div id="view-all-users">
					<span class="display-4">View All Users</span>
					<div class="table-responsive">
						<table class="table table-hover table-sm">
							<thead id="search-user-fields">
								<td colspan="10">
									<div class="form-inline">
										<input type="number" class="form-control mr-4" placeholder="User Id" id="user-id">
										<input type="text" class="form-control mr-4" placeholder="Username" id="username">
										<input type="text" class="form-control mr-4" placeholder="Name" id="name">
										<select name="authority" class="form-control mr-4" id="authority">
											<option value=""></option>
											<option value="ROLE_USER">USER</option>
											<option value="ROLE_ADMIN">ADMIN</option>
											<option value="ROLE_STOCKKEEPER">STOCKKEEPER</option>
										</select>
									</div>
								</td>
							</thead>
							<thead class="text-center thead-light">
								<td scope="col">User Id</td>
								<td scope="col">Username</td>
								<td scope="col">Name</td>
								<td scope="col">Email</td>
								<td scope="col">Birth Date</td>
								<td scope="col">Gender</td>
								<td scope="col">Address</td>
								<td scope="col">Phone</td>
								<td scope="col">Authority</td>
								<td scope="col" style="width: 50px;"></td>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<button class="btn btn-outline-dark" id="previous-button" type-button="previous" page="0" context-path="${pageContext.request.contextPath}"
					 disabled>Previous</button>
					<button class="btn btn-outline-dark" id="next-button" type-button="next" page="0" context-path="${pageContext.request.contextPath}">Next</button>
				</div>

				<div id="view-all-orders" style="display: none;">
					<span class="display-4">Orders Management</span>
					<div class="table-responsive">
						<table class="table table-hover">
							<thead id="search-order-fields">
								<td colspan="9">
									<div class="form-inline">
										<input type="number" class="form-control mr-3" id="order-id" placeholder="Order Id">
										<input type="number" class="form-control mr-3" id="user-id" placeholder="User Id">
										<select name="order-status" id="order-status" class="form-control mr-3">
											<option class="form-control" value=""></option>
											<option class="form-control" value="PREPARING">Preparing</option>
											<option class="form-control" value="SHIPPING">Shipping</option>
											<option class="form-control" value="CANCELED">Canceled</option>
											<option class="form-control" value="FINISH">Finish</option>
										</select>
									</div>
								</td>
							</thead>
							<thead class="text-center thead-light">
								<th>Order Id</th>
								<th>User Id</th>
								<th>Order Date</th>
								<th>Ship Date</th>
								<th>Ship Address</th>
								<th>Total amount</th>
								<th>Payment Method</th>
								<th>Order Status</th>
								<th style="width: 50px;"></th>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<button class="btn btn-outline-dark" id="previous-button" type-button="previous" page="0" context-path="${pageContext.request.contextPath}"
					 disabled>Previous</button>
					<button class="btn btn-outline-dark" id="next-button" type-button="next" page="0" context-path="${pageContext.request.contextPath}">Next</button>
				</div>

				<div class="row" id="order-detail" style="display: none;">
					<div class="col">
						<i class="fas fa-info-circle fa-5x text-secondary mr-3"></i><span class="display-4">View Order Detail</span>
						<hr>
						<div class="form-inline mb-3">
							<input type="number" class="form-control mr-4 ml-auto" id="order-id-input">
							<button class="btn btn-secondary view-order-button" context-path="${pageContext.request.contextPath}" id="admin-view-order-button"
							 disabled>View
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

				<div id="product-management" style="display: none;">
					<i class="fas fa-book fa-5x text-secondary mr-3"></i><span class="display-4">Book Management</span>
					<div class="row my-2">
						<div class="col-md-2">
							<div class="list-group">
								<div class="list-group-item" data-target="#category-management">Category</div>
								<div class="list-group-item" data-target="#manufacturer-management">Manufacturer</div>
								<div class="list-group-item" data-target="#book-management">Book</div>
								<div class="list-group-item" data-target="#promotion-management">Promotion</div>
							</div>
						</div>
						<div class="col-md-9">
							<div id="category-management" style="display: none">
								<div class="row">
									<div class="col-md-7" id="view-category">
										<div class="table-responsive">
											<table class="table table-striped">
												<thead>
													<td colspan="2">
														<div class="form-inline">
															<input type="number" class="form-control w-25 mr-4" placeholder="Id" id="category-id">
															<input type="text" class="form-control mr-4" placeholder="Category Name" id="category-name">
															<button class="btn btn-outline-dark" context-path="${pageContext.request.contextPath}" id="search-category-button">Search</button>
														</div>
													</td>
												</thead>
												<thead class="thead-light">
													<th>Category Id</th>
													<th>Category Name</th>
												</thead>
												<tbody>

												</tbody>

											</table>
											<span id="search-category-feedback"></span>
										</div>
									</div>
									<div class="col-md-5" id="add-category">
										<h4>Add category</h4>
										<div class="form-group">
											<label for="category-name">Category Name</label>
											<input type="text" class="form-control" id="category-name">
											<small class="form-text text-muted" id="add-category-feedback"></small>
										</div>
										<button class="btn btn-outline-dark" context-path="${pageContext.request.contextPath}" id="add-category-button">Add</button>
									</div>
								</div>
							</div>

							<div id="manufacturer-management" style="display: none">
								<div class="row">
									<div class="col-md-7" id="view-manufacturer">
										<div class="table-responsive">
											<table class="table table-striped">
												<thead>
													<td colspan="2">
														<div class="form-inline">
															<input type="number" class="form-control w-25 mr-4" placeholder="Id" id="manufacturer-id">
															<input type="text" class="form-control mr-4" placeholder="Manufacturer Name" id="manufacturer-name">
															<button class="btn btn-outline-dark" context-path="${pageContext.request.contextPath}" id="search-manufacturer-button">Search</button>
														</div>
													</td>
												</thead>
												<thead class="thead-light">
													<th>Manufacturer Id</th>
													<th>Manufacturer Name</th>
												</thead>
												<tbody>

												</tbody>

											</table>
											<span id="search-manufacturer-feedback"></span>
										</div>
									</div>
									<div class="col-md-5" id="add-manufacturer">
										<h4>Add manufacturer</h4>
										<div class="form-group">
											<label for="manufacturer-name">Manufacturer Name</label>
											<input type="text" class="form-control" id="manufacturer-name">
											<small class="form-text text-muted" id="add-manufacturer-feedback"></small>
										</div>
										<button class="btn btn-outline-dark" context-path="${pageContext.request.contextPath}" id="add-manufacturer-button">Add</button>
									</div>
								</div>
							</div>

							<div id="add-book-management" context-path="${pageContext.request.contextPath}">
								<h4>Add book</h4>
								<div class="form-group">
									<label for="category-id">Category</label>
									<select class="form-control" name="category-name" id="category-id" context-path="${pageContext.request.contextPath}">
									</select>
									<small class="form-text text-muted" id="category-id-feedback"></small>
								</div>
								<div class="form-group">
									<label for="book-name">Name</label>
									<input type="text" class="form-control" placeholder="Book Name" id="book-name">
									<small class="form-text text-muted" id="book-name-feedback"></small>
								</div>
								<div class="form-group">
									<label for="book-description">Book Description</label>
									<textarea name="book-description" id="book-description" cols="30" rows="5" class="form-control" id="book-description"
									 placeholder="Book Description"></textarea>
									<small class="form-text text-muted" id="book-description-feedback"></small>
								</div>
								<div class="form-group">
									<label for="manufacturer-id">Manufacturer</label>
									<select name="manufacturer-id" id="manufacturer-id" class="form-control" context-path="${pageContext.request.contextPath}">

									</select>
									<small class="form-text text-muted" id="manufacturer-id-feedback"></small>
								</div>
								<div class="form-group">
									<label for="author">Author</label>
									<input type="text" class="form-control" id="author" placeholder="Author">
									<small class="form-text text-muted" id="author-feedback"></small>
								</div>
								<div class="form-group">
									<label for="price">Price</label>
									<input type="number" class="form-control" id="price" placeholder="Price">
									<small class="form-text text-muted" id="price-feedback"></small>
								</div>

								<!-- <button class="btn btn-outline-dark" id="add-book-button" context-path="${pageContext.request.contextPath}">Add
									Book</button> -->
								<small class="form-text text-muted" id="add-book-management-feedback"></small>
								<form action="" id="upload-images-form"
								 enctype="multipart/form-data" method="POST">
									<input type="hidden" name="bookId" id="bookId">
									<div class="form-group">
										<label for="images">Images</label>
										<div class="custom-file">
											<input type="file" class="custom-file-input" id="file" name="file" multiple><br>
											<small class="form-text text-muted">3 images</small>
											<label for="file" class="custom-file-label">Choose Files</label>
										</div>
									</div>
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button class="btn btn-outline-dark" context-path="${pageContext.request.contextPath}" id="upload-images-button">Add book</button>
									<small class="form-text text-muted" id="upload-images-feedback" id="upload-images-button"></small>
								</form>
							</div>
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