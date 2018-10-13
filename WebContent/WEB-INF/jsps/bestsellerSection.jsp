<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="best-seller-products" class="bg-light text-dark">
	<div class="container py-3 my-3">
		<h2 class="d-inline-block">Best Seller</h2>
		<hr>
		<div class="row text-center">
			<div class="col">
				<div class="slider responsive">
					<c:forEach var="book" items="${bookList }">
						<div class="card p-4">
							<div class="card-img">
								<div class="book-list-view w-100 mt-4">
									<a href=""> <img src="${pageContext.request.contextPath }/resources/images/${book.imageUrl}.jpg" alt="" class="img-fluid">
									</a>
									<c:if test="${book.discount > 0 }">
										<div class="promotion-badge rounded-circle">-${book.discount }%</div>
									</c:if>
								</div>
							</div>
							<div class="card-body">
								<span id="card-title" class="h3">${book.bookName }</span><br>
								<span class="h3 font-italic text-danger">
									<c:if test="${book.discount > 0 }">
										<del>${book.price }</del>
									</c:if> &nbsp;
								</span><br> <span id="price" class="display-4">${book.price * (100 - book.discount) / 100 }</span><br>
								<button class="btn btn-outline-dark btn-block">Add Cart</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</section>