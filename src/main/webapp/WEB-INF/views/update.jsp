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

	<div class="insert">
		<div class="form_body">
			<p class="error">${msg }</p>

			<form:form modelAttribute="update" method="get">
				<fieldset class="label-130">
					<div>
						<input type="hidden" name="id" value="${product.id}">
					</div>
					<div>
						<label>商品ID</label>
						<form:input type="text" path="pdId"
							 class="base-text" />
							<form:errors path="pdId" cssStyle="color: red" />
					</div>
					<div>
						<label>商品名</label>
						<form:input path="pdName" value="${product.name}"
							class="base-text" />
						<form:errors path="pdName" cssStyle="color: red" />
					</div>
					<div>
						<label>単価</label>
						<form:input path="price"
							 class="base-text" />
						<form:errors path="price" cssStyle="color: red" />
					</div>
					<div>
						<label>カテゴリ</label>
						<form:select path="roleId" class="base-text">
							<c:forEach var="cd" items="${cdList}">
								<option value="${cd.id}">${cd.name}</option>
							</c:forEach>
						</form:select>
					</div>
					<div>
						<label>商品説明</label>
						<form:textarea class="base-text" path="description" />
					</div>
					<!--           <div> -->
					<!--             <label>画像</label> -->
					<!--             <input type="file" name="file"> -->
					<!--             <span class="error">エラーメッセージ</span> -->
					<!--           </div> -->
				</fieldset>
				<div class="btns">
					<button type="button" onclick="openModal()" class="basic_btn">更新</button>
					<input type="button" onclick="location.href='menu'"
						value="メニューに戻る" class="cancel_btn">
				</div>
				<div id="modal">
					<p class="modal_message">更新しますか？</p>
					<div class="btns">
						<button type="submit" class="basic_btn" name="btn" value="update">更新</button>
						<input type="button" class="basic_btn"
							onclick="location.href='./delete?id=${product.id}'" value="削除">
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