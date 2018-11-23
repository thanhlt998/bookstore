<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Đăng nhập</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	 crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	 crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.css" />
    <link rel="icon" href="${pageContext.request.contextPath }/resources/favicon.ico" type="image/x-icon"/>
</head>

<body>
    <jsp:include page="navbar.jsp"></jsp:include>
    
    <section style="height: 100vh;">
        <div class="container h-100 p-4">
            <div class="row">
                <div class="col-md-5" style="margin-top: 20%">
                    <h2 class="mb-4">Login Form</h2>
                    <form action="${pageContext.request.contextPath }/login" method="post">
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
                        <br>
                        <div class="text-right"><button class="btn btn-dark" type="submit">Login</button></div>
                        <br> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </div>
                <div class="col-md-5 offset-md-1 p-4 mt-3">
                    <h3>Register Form</h3>
                    <form action="${pageContext.request.contextPath }/register" id="registerForm" method="POST">
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
                            <input type="password" class="form-control" name="password" placeholder="Password" id="password" required> <small id="password-feedback"
                             class="form-text text-muted d-block"></small>
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
                            <br>
                            <input type="date" class="form-control" name="birthDate" id="birthDate" required>
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
                            </select> <br>
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
                        <br>
                        <button class="btn btn-dark" type="submit">Register</button>
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
    <script src="../../resources/javascript/script.js"></script>

    <script>
    </script>
</body>

</html>