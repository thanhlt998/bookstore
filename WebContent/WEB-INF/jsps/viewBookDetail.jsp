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
<!-- View book detail -->
<section id="bookDetail">

    <div class="container">
        <div class="row py-5 align-items-center mb-3 bg-light" id="overview">
            <div class="col-sm-4 col-md-2">
            	<c:forEach var="imageUrl" items="${bookDetail.imageUrls }">
            		<div class="row">
                    <div class="col">
                        <img src="${pageContext.request.contextPath }/resources/images/${imageUrl}.jpg" class="img-fluid small-img" alt="">
                    </div>
                </div>
            	</c:forEach>                
            </div>
            <div class="col-sm-8 col-md-4">
                <img src="${pageContext.request.contextPath }/resources/images/${bookDetail.imageUrls[0]}.jpg" class="img-fluid" id="main-img" alt="">
            </div>
            <div class="col-sm-12 col-md-6">
                <h2 class="text-secondary">${bookDetail.bookName }</h2>
                <span id="font-italic" class="text-secondary">Author: ${bookDetail.author }</span>
                <hr>
                <p>
                	<c:if test="${bookDetail.discount > 0 }">
                    Old price: <span class="h4 text-danger font-italic"><del>${bookDetail.price }đ</del></span><br>
                    On sale: 
                    </c:if>
                    <span class="h3 text-danger">${bookDetail.currentPrice }đ</span>
                </p>
                <hr>
                <span class="small text-muted">
                    Giao hàng tiêu chuẩn Dự kiến giao Thứ bảy - Chủ nhật, 13/10 - 14/10 chi phí 14.000 ₫, miễn phí giao
                    hàng tiêu chuẩn cho đơn hàng từ 150.000 đ</span>
                <hr>
                <div>
                    <div class="form-group">
                        <label for="">Quantity</label>
                        <div class="input-group w-25">
                            <div class="input-group-prepend">
                                <div class="input-group-text" id="minus-button">
                                        <i class="fas fa-minus"></i>
                                </div>
                            </div>
                            <input type="number" class="form-control w-25" value="1" id="quantity-add">
                            <div class="input-group-append" id="add-button">
                                <div class="input-group-text">
                                    <i class="fas fa-plus"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-outline-danger btn-lg add-cart" bookid="${bookDetail.bookId }" quantity="1" id="add-cart-button"><i class="fas fa-cart-plus"></i> Add Cart</button>
                </div>

            </div>
        </div>
        <hr>
        <div class="row mb-3" id="details">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <td class="h3" colspan="2">Book Details</td>
                    </tr>
                </thead>
                <tbody>
                	<tr>
                        <td>Id</td>
                        <td>${bookDetail.bookId }</td>
                    </tr>
                    <tr>
                        <td>Title</td>
                        <td>${bookDetail.bookName }</td>
                    </tr>
                    <tr>
                        <td>Author</td>
                        <td>${bookDetail.author }</td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td>${bookDetail.category }</td>
                    </tr>
                    <tr>
                        <td>Manufacturer</td>
                        <td>${bookDetail.manufacturer }</td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td>${bookDetail.bookDescription }</td>
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
	$('.slider').slick({
        dots: true,
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 3,
        autoplay: true,
        autoplaySpeed: 2000,
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 2,
                    infinite: true,
                    dots: true
                }
            },
            {
                breakpoint: 600,
                settings: {
                    slidesToShow: 1,
                    slidesToScroll: 1
                }
            }
        ]
    });

		$("#username").blur({
			url: "${pageContext.request.contextPath}/checkAvailableUsername"
        }, validateUsernameField);
        
        $(".add-cart").click({
            url: "${pageContext.request.contextPath }/addCart"
        }, addCart);
	</script>
</body>


</html>