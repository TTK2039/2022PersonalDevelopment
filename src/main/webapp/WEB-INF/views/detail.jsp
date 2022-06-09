<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<c:if test="${empty user}">
	<meta http-equiv="refresh" content="0;URL=index">
</c:if>
<meta charset="UTF-8">
<title>商品詳細</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

	<div class="header">
		<h1 class="site_logo">
			<a href="menu.html">商品管理システム</a>
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

	<div class="update">
		<div class="form_body">
			<div class="img_block">
				<img src="images/マッキー.png" class="product_img"><br>
			</div>
			<form method="get">
				<fieldset class="label-130 product_block">
					<p class="error">${error }</p>
					<div>
						<label>食べた日</label> <input type="text" name="pdId"
							value="${lunch.createdAt}" readonly class="base-text">
					</div>
					<div>
						<label>たべたもの</label> <input type="text" name="userName"
							value="${lunch.name}" readonly class="base-text">
					</div>
					<div>
						<label>値段</label> <input type="text" name="tel"
							value="${lunch.price}" readonly class="base-text">
					</div>
					<div>
						<label>カロリー</label> <input type="text" name="roleName"
							value="${lunch.cal}" readonly class="base-text">
					</div>
				</fieldset>
				<div>
					<div class="btns">
						<%-- 						<c:if test="${user.userId == 1}"> --%>
						<input type="button" onclick="openModal()" value="削除"	class="basic_btn">
						<input type="button" class="basic_btn" onclick="location.href='./update?id=${lunch.id}'" value="更新" />
						<%-- 						</c:if> --%>
						<input type="button" onclick="location.href='menu'" value="戻る"
							class="cancel_btn">
					</div>
					<div id="modal">
						<p class="modal_message">削除しますか？</p>
						<div class="btns">
							<input type="button" class="basic_btn"
								onclick="location.href='./delete?id=${lunch.id}'" value="削除">
							<button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>