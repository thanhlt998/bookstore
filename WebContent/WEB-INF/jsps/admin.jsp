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
					<li class="list-group-item dropdown-item active" data-target="#view-all-users">View Users</li>
					<li class="list-group-item dropdown-item" data-target="#storage-management">Storage Management</li>
				</div>
			</div>
			<div class="col-md-10 p-4">
				<div id="view-all-users">
					<span class="display-4">View All Users</span>
					<table class="table table-hover table-sm">
						<thead id="search-fields">
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
							<!-- <tr>
								<td>132465</td>
								<td>Username</td>
								<td>Le Tuan Thanh</td>
								<td>thanh@gmail.com</td>
								<td>1998-28-10</td>
								<td>MALE</td>
								<td>Di Che, Tien Lu, Hung Yen</td>
								<td>132321321</td>
								<td class="p-2">
									<select name="authority" id="authority" class="form-control" disabled>
										<option value="ROLE_USER">USER</option>
										<option value="ROLE_ADMIN">ADMIN</option>
										<option value="ROLE_STOCKKEEPER">STOCKKEEPER</option>
									</select>
								</td>
								<td><i class="fas fa-user-edit" context-path="${pageContext.request.contextPath}"></i></td>
							</tr> -->
						</tbody>
					</table>
					<button class="btn btn-outline-dark" id="previous-button" type-button="previous" page="0" context-path="${pageContext.request.contextPath}"
					 disabled>Previous</button>
					<button class="btn btn-outline-dark" id="next-button" type-button="next" page="0" context-path="${pageContext.request.contextPath}">Next</button>
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