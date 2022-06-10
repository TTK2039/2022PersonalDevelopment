<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<c:if test="${empty user}">
	<meta http-equiv="refresh" content="0;URL=index.jsp">
</c:if>
<meta charset="UTF-8">
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<div class="header">
		<h1 class="site_logo">
			<a href="menu"><img src="./images/logo.png"></a>
		</h1>
		<div class="user">
			<p class="user_name">${fn:escapeXml(user.name)}さん、こんにちは</p>
			<form class="logout_form" action="logout">
				<button class="logout_button">
					<img src="images/ドアアイコン.png">ログアウト
				</button>
			</form>
		</div>
	</div>

	<hr>

	<div class="insert">
		<div class="form_body">
			<p class="error">${msg }</p>

			<form:form modelAttribute="update" method="get">
				<fieldset class="label-130">
					<div>
						<form:input path="id" type="hidden" name="id" value="${lunch.id}"/>
					</div>
					<div>
						<label>お弁当名</label>
						<form:input type="text" path="name"
							 class="base-text" value="${lunch.name }"/>
							<form:errors path="name" cssStyle="color: red" />
					</div>
					<div>
						<label>値段</label>
						<form:input path="price"
							 class="base-text" value="${lunch.price }"/>
						<form:errors path="price" cssStyle="color: red" />
					</div>
					<div>
						<label>カロリー</label>
						<form:input path="cal"
							 class="base-text" value="${lunch.cal }"/>
						<form:errors path="cal" cssStyle="color: red" />
					</div>
					<div>
						<form:input path="userId" type="hidden" name="id" value="${user.id}"/>
					</div>
				</fieldset>
				<div class="btns">
					<button type="button" onclick="openModal()" class="basic_btn">更新</button>
					<input type="button" onclick="location.href='menu'"
						value="メニューに戻る" class="cancel_btn">
				</div>
				<div id="modal">
					<p class="modal_message">更新しますか？</p>
					<div class="btns">
						<form:button type="submit" class="basic_btn" name="btn" formaction="doUpdateBento">更新</form:button>
						<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>