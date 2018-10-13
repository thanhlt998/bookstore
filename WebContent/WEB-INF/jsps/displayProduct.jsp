<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="products"">
	<div class=" container my-3">
		<h2>Products</h2>
		<hr>
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
							<div class="promotion-badge rounded-circle">-${book.discount }%</div>
						</c:if>
					</div>
					<h3>${book.bookName }</h3>
					<span class="h3 font-italic text-danger"> <c:if
							test="${book.discount > 0 }">
							<del>${book.price }</del>
						</c:if> &nbsp;
					</span><br> <span class="display-4">${book.price * (100 - book.discount) / 100 }</span><br>
					<a class="btn btn-dark text-white" href="">More</a> <a
						class="btn btn-danger text-white"><i class="fas fa-cart-plus"></i>
						Add Cart</a>
					<hr>
				</div>
				
			</c:forEach>
		</div>
	</div>
</section>


