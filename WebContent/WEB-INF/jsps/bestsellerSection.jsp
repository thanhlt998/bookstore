<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="best-seller-products">
	<div class="container py-3 my-3">
		<h2 class="d-inline-block">Best Seller</h2>
		<hr>
		<div class="row text-center">
			<div class="col">
				<div class="slider responsive">
					<c:forEach var="book" items="${bestSellerBookList }">
						<div class="card p-4">
							<div class="card-img p-3">
								<div class="book-list-view w-100 mt-4">
									<a href="${pageContext.request.contextPath }/viewBookDetail?bookId=${book.bookId}">
										<img src="${pageContext.request.contextPath }/resources/images/${book.imageUrl}.jpg" alt="" class="img-fluid">
									</a>
									<c:if test="${book.discount > 0 }">
										<span class="promotion-badge badge badge-danger rounded-circle py-4">
											-${book.discount }% </span>
									</c:if>
								</div>
							</div>
							<div class="card-body">
								<span id="card-title" class="h3">${book.bookName }</span><br>
								<span class="h3 font-italic text-danger">
									<c:if test="${book.discount > 0 }">
										<del>${book.price }</del>
									</c:if> &nbsp;
								</span><br> <span id="price" class="display-4">${book.currentPrice }</span><br>
								<button class="btn btn-outline-danger add-cart" bookid="${book.bookId}" quantity="1" context-path="${pageContext.request.contextPath}">
									<i class="fas fa-cart-plus"></i> Add Cart
								</button>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</section>