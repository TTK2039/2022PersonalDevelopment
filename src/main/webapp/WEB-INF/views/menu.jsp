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
<link href="css/commons.css" rel="stylesheet">
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
					<a href="menu"><img src="./images/logo.png"></a>
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
	<%-- 	<c:if test="${user.id == 1}"> --%>
	<div class="btn">
		<a class="basic_btn regist" href="insert">もぐもぐ</a>
	</div>
	<div class="btn">
		<a class="basic_btn regist" href="bento">簡易登録用リストの更新</a>
	</div>
	<%-- 	</c:if> --%>
	<span class="error">${errorSelect}</span>
	<select id="changeSelect" name="selectbox" onchange="entryChange());">
		<option value="select1">お弁当の名前</option>
		<option value="select2">日付を指定</option>
		<option value="select3">日付で範囲を指定</option>
	</select>
	<form id="firstBox" method="get" action="serch"
		class="search_container ">
		<input type="text" name="key" size="25" placeholder="キーワード検索">
		<input type="submit" value="&#xf002">
	</form>
	<form>
		<div id="secondBox">
			<input type="date" name="day" /> <input type="submit" value="&#xf002"
				formaction="serchDate">
			<%-- 		<form:errors path="createdAt" cssStyle="color: red" /> --%>
		</div>
	</form>
	<form>
		<div id="thirdBox">
			<input type="date" name="day1" />から<input type="date" name="day2" />まで
			<input type="submit" formaction="serchDateRange">
			<%-- 		<form:errors path="createdAt" cssStyle="color: red" /> --%>
		</div>
	</form>
	<p>${msg }</p>
	<%-- 	<c:if test="${!(empty count) }"> --%>
	<div class="caption">
		<p>
			検索結果：${lunchList.size()}件<br>${resultSort }</p>
	</div>
	<table border="1" id="sort_table">
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
	<div style="width: 800px">
		<canvas id="mychart"></canvas>
	</div>
	<div class="container" style="width: 100%">
		<canvas id="canvas"></canvas>
	</div>
	<footer></footer>
</body>
<script>
window.addEventListener('load', makeChart);
	function makeChart(){		
	ctx = document.getElementById("canvas").getContext("2d");
			window.myCanvas = new Chart(ctx, {
				type : 'bar',
				data : barChartData,
				options : complexChartOption
			})
	};
			document.getElementById('canvas').addEventListener('click', e => {
					console.log(e)
		    	  const elements = window.myCanvas.getElementAtEvent(e);
		    	  if (elements[0]._model.label.length) {
		    		  window.location.href = './eat?day=' + [elements[0]._model.label];
	    			  console.log('elements', [elements[0]._model.label]);
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
<script>

//参考サイト  //https://blog.ver001.com/javascript-table-sort/ 

let column_no = 0; //今回クリックされた列番号
let column_no_prev = 0; //前回クリックされた列番号
window.addEventListener('load', function () {
	document.querySelectorAll('#sort_table th').forEach(elm => {
		elm.onclick = function () {
			column_no = this.cellIndex; //クリックされた列番号
			let table = this.parentNode.parentNode.parentNode;
			let sortType = 0; //0:数値 1:文字
			let sortArray = new Array; //クリックした列のデータを全て格納する配列
			for (let r = 1; r < table.rows.length; r++) {
				//行番号と値を配列に格納
				let column = new Object;
				column.row = table.rows[r];
				column.value = table.rows[r].cells[column_no].textContent;
				sortArray.push(column);
				//数値判定
				if (isNaN(Number(column.value))) {
					sortType = 1; //値が数値変換できなかった場合は文字列ソート
				}
			}
			if (sortType == 0) { //数値ソート
				if (column_no_prev == column_no) { //同じ列が2回クリックされた場合は降順ソート
					sortArray.sort(compareNumberDesc);
				} else {
					sortArray.sort(compareNumber);
				}
			} else { //文字列ソート
				if (column_no_prev == column_no) { //同じ列が2回クリックされた場合は降順ソート
					sortArray.sort(compareStringDesc);
				} else {
					sortArray.sort(compareString);
				}
			}
			//ソート後のTRオブジェクトを順番にtbodyへ追加（移動）
			let tbody = this.parentNode.parentNode;
			for (let i = 0; i < sortArray.length; i++) {
				tbody.appendChild(sortArray[i].row);
			}
			//昇順／降順ソート切り替えのために列番号を保存
			if (column_no_prev == column_no) {
				column_no_prev = -1; //降順ソート
			} else {
				column_no_prev = column_no;
			}
		};
	});
});
//数値ソート（昇順）
function compareNumber(a, b)
{
	return a.value - b.value;
}
//数値ソート（降順）
function compareNumberDesc(a, b)
{
	return b.value - a.value;
}
//文字列ソート（昇順）
function compareString(a, b) {
	if (a.value < b.value) {
		return -1;
	} else {
		return 1;
	}
	return 0;
}
//文字列ソート（降順）
function compareStringDesc(a, b) {
	if (a.value > b.value) {
		return -1;
	} else {
		return 1;
	}
	return 0;
}
</script>
<script type="text/javascript">
document.getElementById('changeSelect').onchange = entryChange;

function entryChange(){
	if(document.getElementById('changeSelect')){
		id = document.getElementById('changeSelect').value;
		if(id == 'select1'){
			document.getElementById('firstBox').style.display = "";
			document.getElementById('secondBox').style.display = "none";
			document.getElementById('thirdBox').style.display = "none";
		}else if(id == 'select2'){
			//フォーム
			document.getElementById('firstBox').style.display = "none";
			document.getElementById('secondBox').style.display = "";
			document.getElementById('thirdBox').style.display = "none";
		}else if(id == 'select3'){
			document.getElementById('firstBox').style.display = "none";
			document.getElementById('secondBox').style.display = "none";
			document.getElementById('thirdBox').style.display = "";
		}
	}
}
//オンロードさせ、リロード時に選択を保持
window.onload = entryChange;
</script>

</html>
