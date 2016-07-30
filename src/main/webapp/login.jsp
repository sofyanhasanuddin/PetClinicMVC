<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:set var="ctx" value="${ pageContext.servletContext.contextPath }" />
<c:set var="loginUrl" value="j_spring_security_check" />

<div class="container">
	<div class="row">
		<div style="margin-top: 0%"></div>
		<div class="col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Pet Clinic Login</h3>
				</div>
				<div class="panel-body">
					<form role="form" action="${loginUrl}" method="post">
						<sec:csrfInput />
						<div>
							<c:if test="${not empty param.login_error}">
								<p class="formError">Your login attempt was not successful,
									try again.</p>
								<p class="formError">Reason:
									${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
							</c:if>
						</div>
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="Username"
									name="username" type="text" autofocus>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="Password"
									name="password" type="password" value="">
							</div>
							<button class="btn btn-primary" type="submit" class="btn">
								Login
							</button>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>