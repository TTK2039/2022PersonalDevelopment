<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規ユーザー登録</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

	<div class="header">
		<h1 class="site_logo">
			<a href="menu"><img src="./images/logo.png"></a>
		</h1>
	</div>

	<hr>

	<div class="insert">
		<div class="form_body">
			<p class="error">${error }</p>
			<p>${msg }</p>
			<form:form modelAttribute="signUp" method="post">
				<fieldset class="label-130">
					<div>
						<label class="required"><fmt:message
								key="form.lbl.userName" /></label>
						<form:input type="text" path="userName" class="base-text" />
						<form:errors path="userName" cssStyle="color: red" />
					</div>
					<div>
						<label class="required"><fmt:message
								key="form.lbl.loginId" /></label>
						<form:input type="text" path="userId" class="base-text" />
						<form:errors path="userId" cssStyle="color: red" />
					</div>
					<div>
						<label class="required"><fmt:message key="form.lbl.password" /></label>
						<form:input type="password" path="password" class="base-text" />
						<form:errors path="password" cssStyle="color: red" />
					</div>
				</fieldset>
				<div class="btns">
					<button type="button" onclick="openModal()" class="basic_btn">
						<fmt:message key="form.lbl.register" />
					</button>
					<input type="button" onclick="location.href='index'" value="戻る"
						class="cancel_btn">
				</div>
				<div id="modal">
					<p class="modal_message">
						<fmt:message key="form.lbl.register" />
						しますか？
					</p>
					<div class="btns">
						<form:button type="submit" formaction="createUser" class="basic_btn">
							<fmt:message key="form.lbl.register" />
						</form:button>
						<button type="button" onclick="closeModal()" class="cancel_btn">
							<fmt:message key="form.lbl.cancel" />
						</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>

<script src="./js/commons.js"></script>
