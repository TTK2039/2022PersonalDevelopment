<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<c:if test="${empty user}">
	<meta http-equiv="refresh" content="0;URL=index">
</c:if>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.2.0/chart.min.js"
	integrity="sha512-VMsZqo0ar06BMtg0tPsdgRADvl0kDHpTbugCBBrL55KmucH6hP9zWdLIWY//OTfMnzz6xWQRxQqsUFefwHuHyg=="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns@next/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.bundle.min.js"></script>

<title>メニュー</title>
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">

</head>
<body>
	<div id="app">
		<header>
			<div class="header">
				<h1 class="site_logo">
					<a href="menu.jsp">商品管理システム</a>
				</h1>
				<div class="user">
					<p class="user_name">${user.name}さん、こんにちは</p>
					<form class="logout_form" action="logout">
						<button class="logout_button">
							<img src="images/ドアアイコン.png">ログアウト
						</button>
					</form>
				</div>
			</div>
		</header>
	</div>
	<hr>
	<c:if test="${user.id == 1}">
		<div class="btn">
			<a class="basic_btn regist" href="insert">新規登録</a>
		</div>
	</c:if>
	<span class="error">${errorSelect}</span>
	<form method="get" action="serch" class="search_container ">
		<input type="text" name="key" size="25" placeholder="キーワード検索">
		<input type="submit" value="&#xf002">
	</form>

	<div class="order">
		<select
			onChange="location.href='TableServlet?sort=' + value + '&find=&keyword=' + document.getElementById('keyword').value">
			<option>並び替え</option>
			<option value="sortId">商品ID</option>
			<option value="sortCate">カテゴリ</option>
			<option value="sortPriceLow">単価：安い順</option>
			<option value="sortPriceHigh">単価：高い順</option>
			<option value="sortDayOld">登録日：古い順</option>
			<option value="sortDayNew">登録日：新しい順</option>
		</select>
	</div>
	<p>${msg }</p>
	<%-- 	<c:if test="${!(empty count) }"> --%>
	<div class="caption">
		<p>
			検索結果：${lunchList.size()}件<br>${resultSort }</p>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>食べた日</th>
				<th>ご飯名</th>
				<th>値段</th>
				<th>摂取カロリー</th>
				<th>詳細</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="eat" items="${lunchList}">
				<tr>
					<td>${eat.createdAt}</td>
					<td>${eat.name}</td>
					<td>${eat.price}</td>
					<td>${eat.cal}</td>
					<td><a class="detail_btn" href="./detail?id=${eat.id}"><fmt:message
								key="form.lbl.detail" /></a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<c:forEach var="bento" items="${bentoList }">${bento.id },${bento.name}</c:forEach>
	<div style="width: 800px">
		<canvas id="mychart"></canvas>
	</div>
	<p>
		<c:forEach var="product" items="${testList}">${product.createdAt}<br>
		</c:forEach>
	</p>
	<div class="container" style="width: 100%">
		<canvas id="canvas"></canvas>
	</div>
	<script>
		window.onload = function() {
			ctx = document.getElementById("canvas").getContext("2d");
			window.myCanvas = new Chart(ctx, {
				type : 'bar',
				data : barChartData,
				options : complexChartOption
			});
		}
		document.getElementById('canvas').addEventListener('click', e => {
				console.log(e)
		      const elements = window.myCanvas.getElementAtEvent(e);
		      if (elements.length) {
		    	  console.log('elements', [elements[0]._model.label]);
		      	alert(`${elements[0]._model.label}`);
		      }else {
		    	  alert('aa');
		      }
		    });
	</script>



	<script>
		// とある4週間分のデータログ
		var barChartData = {
			labels : [<c:forEach var = "lunch" items = "${tableList}">'${lunch.createdAt}',</c:forEach>],
			datasets : [
					{
						type : 'line',
						label : 'お弁当の値段',
						data : [<c:forEach var = "lunch" items = "${tableList}">'${lunch.price}',</c:forEach>],
						borderColor : "rgba(254,97,132,0.8)",
						pointBackgroundColor : "rgba(254,97,132,0.8)",
						fill : false,
						yAxisID : "y-axis-1",// 追加
					},
					{
						type : 'bar',
						label : '一日のカロリー摂取量',
						data : [<c:forEach var = "lunch" items = "${tableList}">'${lunch.cal}',</c:forEach>],
						borderColor : "rgba(54,164,235,0.8)",
						backgroundColor : "rgba(54,164,235,0.5)",
						yAxisID : "y-axis-2",
					}, ],
		};

	</script>



	<script>
		var complexChartOption = {
			responsive : true,
			scales : {
				yAxes : [ {
					id : "y-axis-1",
					type : "linear",
					position : "left",
					ticks : {
						max : 2000,
 						min : 0,
// 						stepSize : 0.1
					},
				}, {
					id : "y-axis-2",
					type : "linear",
					position : "right",
					ticks : {
// 						max : 1.5,
// 						min : 0,
// 						stepSize : .5
					},
					gridLines : {
						drawOnChartArea : false,
					},
				} ],
			}
		};
	</script>
	<footer></footer>
</body>
</html>
