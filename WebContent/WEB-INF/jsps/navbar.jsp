<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav
	class="navbar navbar-expand-md navbar-dark bg-dark text-white fixed-top ">
	<div class="container">
		<div class="row w-100 align-items-center">
			<div class="col-md-3 text-center">
				<a href="${pageContext.request.contextPath }/" class="navbar-brand">
					<i class="fas fa-book fa-2x pr-3"></i><span class="display-5">BookStore</span>
				</a>
				<button class="navbar-toggler" data-toggle="collapse"
					data-target="#navbarBar">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
			<div class="col-md-5 py-2">
				<form action="${pageContext.request.contextPath }/viewSearchResult">
					<div class="input-group">
						<div class="input-group-prepend">
							<button class="btn btn-light input-group-text text-dark">
								<i class="fas fa-search"></i>
							</button>
						</div>
						<input type="text" class="form-control" placeholder="Search"
							name="searchValue">
					</div>
					<input type="hidden" class="form-control" name="page" value="1">
				</form>
			</div>
			<div class="col-md-4">
				<div class="collapse navbar-collapse" id="navbarBar">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item dropdown mr-3"><a href=""
							class="nav-link dropdown-toggle" data-toggle="dropdown">
								Categories </a>
							<div class="dropdown-menu">
								<c:forEach var="category" items="${categoryList }">
									<a
										href="${pageContext.request.contextPath }/viewBookByCategory?categoryId=${category.categoryId}&page=1"
										class="dropdown-item"> ${category.categoryName } </a>
								</c:forEach>
							</div></li>
						<sec:authorize access="isAuthenticated()">
							<li class="nav-item dropdown mr-3"><a href="#"
								class="nav-link dropdown-toggle" data-toggle="dropdown"> <i
									class="fas fa-user"></i>
							</a>
								<div class="dropdown-menu">
									<a href="#" class="dropdown-item"> <i
										class="fas fa-user-circle"></i> Profile
									</a> <a href="#" class="dropdown-item"> <i class="fas fa-cog"></i>
										Setting
									</a>
								</div></li>
						</sec:authorize>
						<sec:authorize access="!isAuthenticated()">
							<li class="nav-item"><a href="#" class="nav-link"
								data-toggle="modal" data-target="#loginModal"> <i
									class="fas fa-lock"></i> Login
							</a></li>
						</sec:authorize>
						<sec:authorize access="isAuthenticated()">
							<li class="nav-item"><a
								href="${pageContext.request.contextPath }/logout"
								class="nav-link""> <i
									class=" fas
                                fa-sign-out-alt"></i>
									Logout
							</a></li>
						</sec:authorize>
						<li class="nav-item d-inline-block dropdown" id="shopCart"><a
							href="#" class="nav-link" style="position: relative;"> <i
								class="fas fa-shopping-cart fa-2x"></i><span id="badge"
								class="badge badge-danger rounded-circle">${cart.totalQuantity}</span>
						</a>
							<div class="dropdown-menu p-3" id="cartDetail"
								style="width: 280px;">
								<table class="table table-striped small">
									<thead>
										<tr>
											<td>Name</td>
											<td>No.</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="cartItem" items="${cart.cart }">
											<tr>
												<td>${cartItem.book.bookName }</td>
												<td>${cartItem.quantity }</td>
											</tr>
										</c:forEach>
										<tr>
											<td>Total Price</td>
											<td>${cart.totalPrice }</td>
										</tr>
									</tbody>

								</table>
								<a href="${pageContext.request.contextPath }/viewCartDetail" class="btn btn-danger btn-sm mr-1">View Cart</a>
							</div></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</nav>

<sec:authorize access="!isAuthenticated()">
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

					<jsp:include page="loginForm.jsp"></jsp:include>

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

					<jsp:include page="registerForm.jsp"></jsp:include>

				</div>
			</div>
		</div>

	</section>
</sec:authorize>