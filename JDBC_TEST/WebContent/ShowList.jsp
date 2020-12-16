<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.matsu.model.WeightData;"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>access from JSP</title>

<!-- StyleSheet の読み込み -->

<link rel ="stylesheet" type="text/css"
	href = "Num01.css" />
</head>
<body>
	<h1>URLのリスト</h1>
	<%
	  if(request.getAttribute("resist") != null) out.print("<h3>登録しました。</h3>");
	%>
		<a href= "Index.html" >登録画面</a>
		<p class="test">
			<b>体重の変遷</b>
		</p>
		<table border="2">
		<tr>
			<th>体重</th>
			<th>日付</th>
		</tr>
		<% List<WeightData> wdList = (List<WeightData>) request.getAttribute("list");
	for (WeightData wd : wdList){
		out.println("<tr>");
		out.print("<td>"+ wd.weight +"</td>");
		out.println("<td>"+ wd.date + "</td><br />");
		out.println("</tr>");
	}
	%>
	</table>





</body>
</html>