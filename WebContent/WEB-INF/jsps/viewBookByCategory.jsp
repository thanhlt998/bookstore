<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.css" />
</head>

<body>
	<jsp:include page="navbar.jsp"></jsp:include>

	<!-- View book detail -->
	<section id="viewBookByCategory">
	<div class="container my-5 py-5">
		<h3>${category.categoryName }</h3>
		<hr>
		<c:if test="${bookList.isEmpty() }">
			<p class="text-center display-3">No results found.</p>
		</c:if>
		<div class="row align-items-center text-center bg-light">
			<c:forEach var="book" items="${bookList }">
				<div class="col-md-4 p-5 border-right">
					<div class="book-list-view w-100">
						<a
							href="${pageContext.request.contextPath }/viewBookDetail?bookId=${book.bookId}">
							<img
							src="${pageContext.request.contextPath }/resources/images/${book.imageUrl}.jpg"
							alt="" class="img-fluid mb-5">
						</a>
						<c:if test="${book.discount > 0 }">
							<span
								class="promotion-badge badge badge-danger rounded-circle py-4">
								-${book.discount }% </span>
						</c:if>
					</div>
					<h3>${book.bookName }</h3>
					<span class="h3 font-italic text-danger"> <c:if
							test="${book.discount > 0 }">
							<del>${book.price }</del>
						</c:if> &nbsp;
					</span><br> <span class="display-4">${book.currentPrice }</span><br>
					<button class="btn btn-outline-danger add-cart"
						bookid="${book.bookId}" quantity="1" context-path="${pageContext.request.contextPath}">
						<i class="fas fa-cart-plus"></i> Add Cart
					</button>
					<hr>
				</div>
			</c:forEach>
		</div>

		<hr>
		<nav>
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${page == 1 }">
					<li class="page-item disabled"><a class="page-link" href="">Previous</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/viewBookByCategory?categoryId=${category.categoryId}&page=${page-1}&noPage=${noPage}">Previous</a>
					</li>
				</c:otherwise>
			</c:choose>
			<c:if test="${noPage < 10 }">
				<c:forEach var="i" begin="1" end="${noPage }">
					<c:choose>
						<c:when test="${i != page }">
							<li class="page-item"><a class="page-link"
								href="${pageContext.request.contextPath}/viewBookByCategory?categoryId=${category.categoryId}&page=${i}&noPage=${noPage}">${i
											}</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item active"><a class="page-link"
								href="${pageContext.request.contextPath}/viewBookByCategory?categoryId=${category.categoryId}&page=${i}&noPage=${noPage}">${i
											}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
			<c:if test="${noPage >= 10 }">
				<c:forEach var="i" begin="1" end="${page - 1 }">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/viewBookByCategory?categoryId=${category.categoryId}&page=${i}&noPage=${noPage}">${i
									}</a></li>
				</c:forEach>
				<li class="page-item active"><a class="page-link"
					href="${pageContext.request.contextPath}/viewBookByCategory?categoryId=${category.categoryId}&page=${page}&noPage=${noPage}">page</a></li>
				<li class="page-item"><a class="page-link">...</a></li>
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/viewBookByCategory?categoryId=${category.categoryId}&page=${noPage}&noPage=${noPage}">${noPage
								}</a></li>
			</c:if>
			<c:choose>
				<c:when test="${page != noPage }">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/viewBookByCategory?categoryId=${category.categoryId}&page=${page+1}&noPage=${noPage}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		</nav>

	</div>

	</section>

	<jsp:include page="footer.jsp"></jsp:include>

	<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/javascript/script.js"></script>

	<script>
	</script>
</body>

</html>