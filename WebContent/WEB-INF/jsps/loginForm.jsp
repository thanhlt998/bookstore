<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<form action="${pageContext.request.contextPath }/login" method="post">
	<div class="modal-body">

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


	</div>

	<div class="modal-footer">
		<p class="text-right small font-italic text-muted">
			<a href="" data-dismiss="modal" data-toggle="modal" data-target="#registerModal">Not have an account? </a>
		</p>
		<button class="btn btn-dark" type="submit">Login</button>
		<br> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</div>
</form>