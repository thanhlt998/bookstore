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

	<section style="min-height: 100vh;">
        <div class="container my-5 p-4">
            <div class="row">
                <div id="storage-management">
					<i class="fas fa-warehouse fa-5x mr-3 text-secondary mb-3"></i><span class="display-4">Storage Management</span><br>
					<div class="btn-group tab mb-3">
						<button class="btn btn-outline-dark active" data-target="#import-book">Import Book</button>
						<button class="btn btn-outline-dark" data-target="#view-importation">View Importation</button>
						<button class="btn btn-outline-dark" data-target="#view-exportation">View Exportation</button>
					</div>
					<div id="import-book">
						<div class="form-group">
							<label for="storage-id">Storage Id</label>
							<select name="storage-id" id="storage-id" class="form-control" context-path="${pageContext.request.contextPath}"></select>
							<small class="form-text text-muted" id="storage-id-feedback"></small>
						</div>
						<div class="form-group">
							<label for="book-id">Book Id</label>
							<input type="number" class="form-control" id="book-id">
							<small class="form-text text-muted" id="book-id-feedback"></small>
						</div>
						<div class="form-group">
							<label for="quantity">Quantity</label>
							<input type="text" class="form-control" id="quantity" min="1">
							<small class="form-text text-muted" id="quantity-feedback"></small>
						</div>
						<div class="form-group">
							<label for="import-price">Import Price</label>
							<input type="number" class="form-control" id="import-price">
							<small class="form-text text-muted" id="import-price-feedback"></small>
						</div>
						<button class="btn btn-outline-dark" id="import-book-button" context-path="${pageContext.request.contextPath}">Import</button>
						<small class="form-text text-muted" id="import-book-feedback"></small>
					</div>

					<div id="view-importation" style="display: none;">
						<table class="table table-striped">
							<thead>
								<td colspan="6">
									<div class="form-inline">
										<input type="number" class="form-control mr-3" id="importation-id" placeholder="Id">
										<input type="number" class="form-control mr-3" id="storage-id" placeholder="Storage Id">
										<input type="number" class="form-control mr-3" id="book-id" placeholder="Book Id">
										<input type="date" class="form-control mr-3" id="import-date">
									</div>
								</td>
							</thead>
							<thead class="thead-light text-center">
								<th>Id</th>
								<th>Storage Id</th>
								<th>Book Id</th>
								<th>Quantity</th>
								<th>Import Date</th>
								<th>Import Price</th>
							</thead>
							<tbody>

							</tbody>
						</table>
						<button class="btn btn-outline-dark" id="previous-button" type-button="previous" page="0" context-path="${pageContext.request.contextPath}"
						 disabled>Previous</button>
						<button class="btn btn-outline-dark" id="next-button" type-button="next" page="0" context-path="${pageContext.request.contextPath}">Next</button>
					</div>

					<div id="view-exportation" style="display: none;">
						<table class="table table-striped">
							<thead>
								<td colspan="5">
									<div class="form-inline">
										<input type="number" class="form-control mr-3" id="exportation-id" placeholder="Id">
										<input type="number" class="form-control mr-3" id="storage-id" placeholder="Storage Id">
										<input type="number" class="form-control mr-3" id="book-id" placeholder="Book Id">
										<input type="date" class="form-control mr-3" id="export-date">
									</div>
								</td>
							</thead>
							<thead class="thead-light text-center">
								<th>Id</th>
								<th>Storage Id</th>
								<th>Book Id</th>
								<th>Quantity</th>
								<th>Export Date</th>
							</thead>
							<tbody>

							</tbody>
						</table>
						<button class="btn btn-outline-dark" id="previous-button" type-button="previous" page="0" context-path="${pageContext.request.contextPath}"
						 disabled>Previous</button>
						<button class="btn btn-outline-dark" id="next-button" type-button="next" page="0" context-path="${pageContext.request.contextPath}">Next</button>
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