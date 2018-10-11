<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
	<jsp:include page="header.jsp"></jsp:include>

	<section id="best-seller-products" class="bg-light text-dark">
		<div class="container py-3 my-3">
			<h2 class="d-inline-block">Best Seller</h2>
			<hr>
			<div class="row text-center">
				<div class="col">
					<div class="slider responsive">
						<div class="card p-4">
							<div class="card-img">
								<img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid bounded">
							</div>
							<div class="card-body">
								<span id="card-title">Title</span><br> <span id="price">200.000</span>
								<button class="btn btn-outline-dark btn-block">Add Cart</button>
							</div>
						</div>
						<div class="card p-4">
							<div class="card-img">
								<img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid bounded">
							</div>
							<div class="card-body">
								<span id="card-title">Title</span><br> <span id="price">200.000</span>
								<button class="btn btn-outline-dark btn-block">Add Cart</button>
							</div>
						</div>
						<div class="card p-4">
							<div class="card-img">
								<img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid bounded">
							</div>
							<div class="card-body">
								<span id="card-title">Title</span><br> <span id="price">200.000</span>
								<button class="btn btn-outline-dark btn-block">Add Cart</button>
							</div>
						</div>
						<div class="card p-4">
							<div class="card-img">
								<img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid bounded">
							</div>
							<div class="card-body">
								<span id="card-title">Title</span><br> <span id="price">200.000</span>
								<button class="btn btn-outline-dark btn-block">Add Cart</button>
							</div>
						</div>
						<div class="card p-4">
							<div class="card-img">
								<img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid bounded">
							</div>
							<div class="card-body">
								<span id="card-title">Title</span><br> <span id="price">200.000</span>
								<button class="btn btn-outline-dark btn-block">Add Cart</button>
							</div>
						</div>
						<div class="card p-4">
							<div class="card-img">
								<img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid bounded">
							</div>
							<div class="card-body">
								<span id="card-title">Title</span><br> <span id="price">200.000</span>
								<button class="btn btn-outline-dark btn-block">Add Cart</button>
							</div>
						</div>
						<div class="card p-4">
							<div class="card-img">
								<img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid bounded">
							</div>
							<div class="card-body">
								<span id="card-title">Title</span><br> <span id="price">200.000</span>
								<button class="btn btn-outline-dark btn-block">Add Cart</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Products -->
	<section id="products"">
	<div class=" container my-3">
		<h2>Products</h2>
		<hr>
		<div class="row align-items-center text-center bg-light">
			<div class="col-md-4 p-5 border-right">
				<a href=""> <img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid mb-5">
				</a>
				<h3>Lorem, ipsum dolor.</h3>
				<span class="display-4">150.000</span><br>
				<button class="btn btn-outline-dark">More</button>
				<button class="btn btn-outline-danger">
					<i class="fas fa-heart"></i> Add Cart
				</button>
			</div>
			<div class="col-md-4 p-5 border-right">
				<a href=""> <img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid mb-5">
				</a>
				<h3>Lorem, ipsum dolor.</h3>
				<span class="display-4">150.000</span><br>
				<button class="btn btn-outline-dark">More</button>
				<button class="btn btn-outline-danger">
					<i class="fas fa-heart"></i> Add Cart
				</button>
			</div>
			<div class="col-md-4 p-5">
				<a href=""> <img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid mb-5">
				</a>
				<h3>Lorem, ipsum dolor.</h3>
				<span class="display-4">150.000</span><br>
				<button class="btn btn-outline-dark">More</button>
				<button class="btn btn-outline-danger">
					<i class="fas fa-heart"></i> Add Cart
				</button>
			</div>
		</div>
		<hr>
		<div class="row align-items-center text-center bg-light">
			<div class="col-md-4 p-5 border-right">
				<a href=""> <img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid mb-5">
				</a>
				<h3>Lorem, ipsum dolor.</h3>
				<span class="display-4">150.000</span><br>
				<button class="btn btn-outline-dark">More</button>
				<button class="btn btn-outline-danger">
					<i class="fas fa-heart"></i> Add Cart
				</button>
			</div>
			<div class="col-md-4 p-5 border-right">
				<a href=""> <img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid mb-5">
				</a>
				<h3>Lorem, ipsum dolor.</h3>
				<span class="display-4">150.000</span><br>
				<button class="btn btn-outline-dark">More</button>
				<button class="btn btn-outline-danger">
					<i class="fas fa-heart"></i> Add Cart
				</button>
			</div>
			<div class="col-md-4 p-5">
				<a href=""> <img src="${pageContext.request.contextPath }/resources/images/book1.jpg" alt="" class="img-fluid mb-5">
				</a>
				<h3>Lorem, ipsum dolor.</h3>
				<span class="display-4">150.000</span><br>
				<button class="btn btn-outline-dark">More</button>
				<button class="btn btn-outline-danger">
					<i class="fas fa-heart"></i> Add Cart
				</button>
			</div>
		</div>

		</div>
	</section>

	<!-- Login modal -->
	<section id="login-modal">
		<div class="modal fade text-dark" id="loginModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Login</h5>
						<button class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>
					<form action="${pageContext.request.contextPath }/login" method="post">
						<div class="modal-body">

							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-user"></i></span>
								</div>
								<input type="text" class="form-control" name="username" placeholder="Username">
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-lock"></i></span>
								</div>
								<input type="password" class="form-control" name="password" placeholder="Password">
							</div>


						</div>

						<div class="modal-footer">
							<p class="text-right small font-italic text-muted">
								<a href="" data-dismiss="modal" data-toggle="modal" data-target="#registerModal">Not have an account? </a>
							</p>
							<button class="btn btn-dark" type="submit">Login</button>
							<br> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</div>
					</form>
				</div>
			</div>
		</div>

	</section>

	<!-- register modal -->
	<section id="register-modal">
		<div class="modal fade text-dark" id="registerModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Register</h5>
						<button class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>
					<form class="mb-3" action="${pageContext.request.contextPath}/register" id="registerForm" method="post">
						<div class="modal-body">

							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-pencil-alt"></i></span>
								</div>
								<input type="text" class="form-control" name="name" placeholder="Full Name" id="name" required>

							</div>
							<br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="far fa-envelope"></i></span>
								</div>
								<input type="email" class="form-control" name="email" placeholder="Email" id="email" required>
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-user"></i></span>
								</div>
								<input type="text" class="form-control" name="username" placeholder="Username" id="username" required><br>
								<div class="input-group-append">
									<span class="input-group-text" id="usernameAppend"></span>
								</div>
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-lock"></i></span>
								</div>
								<input type="password" class="form-control" name="password" placeholder="Password" id="password" required>
								<small id="password-feedback" class="form-text text-muted d-block"></small>
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-lock"></i></span>
								</div>
								<input type="password" class="form-control d-block" name="confirmPassword" placeholder="Confirm Password" id="confirmPassword"
								 required>
								<div class="input-group-append">
									<span class="input-group-text" id="confirmPasswordAppend"></span>
								</div>
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="far fa-calendar-alt"></i></span>
								</div>
								<br><input type="date" class="form-control" name="birthDate" id="birthDate" required>
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-transgender"></i></span>
								</div>
								<select type="gender" class="form-control" name="gender" id="gender" required>
									<option value="MALE" selected>Male</option>
									<option value="FEMALE">Female</option>
									<option value="OTHER">Other</option>
								</select>
								<br>
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="far fa-address-book"></i></span>
								</div>
								<input type="text" class="form-control" name="address" placeholder="Address" id="address" required>
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text"><i class="fas fa-mobile-alt"></i></span>
								</div>
								<input type="text" class="form-control" name="phone" placeholder="Phone" id="phone" required>
							</div>
						</div>

						<div class="modal-footer">
							<p class="text-right small font-italic text-muted align-middle"> <a href="" data-dismiss="modal" data-toggle="modal"
								 data-target="#loginModal">Already have an account?</a></p>
							<button class="btn btn-dark" type="submit">Register</button>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
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
		$('.slider').slick({
			dots: true,
			infinite: true,
			slidesToShow: 4,
			slidesToScroll: 4,
			autoplay: true,
			autoplaySpeed: 2000,
			responsive: [{
				breakpoint: 1024,
				settings: {
					slidesToShow: 3,
					slidesToScroll: 3,
					infinite: true,
					dots: true
				}
			}, {
				breakpoint: 600,
				settings: {
					slidesToShow: 2,
					slidesToScroll: 2
				}
			}, {
				breakpoint: 480,
				settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				}
			}]
		});

		$("#username").blur({
			url: "${pageContext.request.contextPath}/checkAvailableUsername"
		}, validateUsernameField);
	</script>
</body>


</html>