<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<c:if test="${empty user}">
	<meta http-equiv="refresh" content="0;URL=index">
</c:if>
<meta charset="UTF-8">
<title>商品登録</title>
<link href="css/commons.css" rel="stylesheet">
<script src="js/commons.js"></script>
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
		<div class="discription">
			<p>
				簡易登録リストに追加しよう！（<span class="required"></span>は必須です）
			</p>
		</div>

		<div class="form_body">
			<p class="error">${error }</p>
			<p>${msg }</p>
			<form:form modelAttribute="bento" method="post">
				<fieldset class="label-130">
					<div id="free" style="none">
						<label class="required">ごはん名</label>
						<form:input type="text" path="name" class="base-text"
							placeholder="入力してください" />
						<form:errors path="name" cssStyle="color: red"  />
					</div>
					<div>
						<label class="required">値段</label>
						<form:input type="number" path="price" class="base-text" id="price" />
						<form:errors path="price" cssStyle="color: red" />
					</div>
					<div>
						<label class="required">かろりー</label>
						<form:input type="number" path="cal" class="base-text" id="cal" />
						<form:errors path="cal" cssStyle="color: red" />
					</div>
					<div>
						<form:input path="userId" type="hidden" value="${user.id }" />
					</div>
				</fieldset>
				<div class="btns">
					<button type="button" onclick="openModal()" class="basic_btn">
						<fmt:message key="form.lbl.register" />
					</button>
					<input type="button" onclick="location.href='menu'" value="戻る"
						class="cancel_btn">
				</div>
				<div id="modal">
					<p class="modal_message">
						<fmt:message key="form.lbl.register" />
						しますか？
					</p>
					<div class="btns" id="fadeLayer">
						<form:button type="submit" formaction="bentoRegister" class="basic_btn">
							<fmt:message key="form.lbl.register" />
						</form:button>
						<button type="button" onclick="closeModal()" class="cancel_btn">
							<fmt:message key="form.lbl.cancel" />
						</button>
					</div>
				</div>
			</form:form>
			<form></form>
		</div>
	</div>
	<div id="fadeLayer"></div>
</body>
</html>
<script>
	let price = {<c:forEach var="bento" items="${bentoList}">
		"${bento.name}" : "${bento.price}",</c:forEach>};
	let cal = {<c:forEach var="bento" items="${bentoList}">
	"${bento.name}" : "${bento.cal}",</c:forEach>};
	//select要素の取得
	var select = document.querySelector("#food");

	//option要素の取得（配列）
	var options = document.querySelectorAll("#food option");

	//select要素のchangeイベントの登録
	select.addEventListener('change', b, false);
	function b() {
		let Price = document.querySelector("input[type='text']#price");
		//選択されたoption番号を取得
		Price.value = price[this.value] //にoption要素のvalue属性値
		//options[ index ].innerHTML にoption要素内の文字列
		let Cal = document.querySelector("input[type='text']#cal");
		Cal.value = cal[this.value]
	}
</script>
<script>
function formSwitch() {
        check = document.getElementsByName('radio')
        if (check[0].checked) {
            document.getElementById('easy').style.display = "";
            document.getElementById('free').style.display = "none";
        } else if(check[1].checked){
            document.getElementById('easy').style.display = "none";
            document.getElementById('free').style.display = "";
        }
    }
    window.addEventListener('load', formSwitch());
    </script>