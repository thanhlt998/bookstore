<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form class="mb-3" action="${pageContext.request.contextPath}/register" id="registerForm" method="post">
	<div class="modal-body">

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
			<input type="text" class="form-control" name="username" placeholder="Username" id="username" required context-path="${pageContext.request.contextPath}"><br>
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
	</div>

	<div class="modal-footer">
		<p class="text-right small font-italic text-muted align-middle">
			<a href="" data-dismiss="modal" data-toggle="modal" data-target="#loginModal">Already have an account?</a>
		</p>
		<button class="btn btn-dark" type="submit">Register</button>
	</div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>