<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="products" class="bg-light">
	<div class=" container my-3">
	<h2>Products</h2>
	<hr>
	<div class="row align-items-center text-center">
		<c:forEach var="book" items="${bookList }">
			<div class="col-md-4 p-5 border-right bg-white">
				<div class="book-list-view w-100">
					<a href="${pageContext.request.contextPath }/viewBookDetail?bookId=${book.bookId}">
						<img src="${pageContext.request.contextPath }/resources/images/${book.imageUrl}" alt="" class="img-fluid mb-5">
					</a>
					<c:if test="${book.discount > 0 }">
						<span class="promotion-badge badge badge-danger rounded-circle py-4">
							-${book.discount }%
						</span>
					</c:if>
				</div>
				<h3>${book.bookName }</h3>
				<span class="h3 font-italic text-danger">
					<c:if test="${book.discount > 0 }">
						<del>${book.price }</del>
					</c:if> &nbsp;
				</span><br>
				<span class="display-4">${book.currentPrice }</span>
				<br>
				<a class="btn btn-dark text-white" href="${pageContext.request.contextPath }/viewBookDetail?bookId=${book.bookId}">
					More
				</a>
				<button class="btn btn-outline-danger add-cart" bookid="${book.bookId}" quantity="1"  context-path="${pageContext.request.contextPath}"><i class="fas fa-cart-plus"></i>
					Add Cart</button>
				<hr>
			</div>

		</c:forEach>
	</div>
	</div>
</section>