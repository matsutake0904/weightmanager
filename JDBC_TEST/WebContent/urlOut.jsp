<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>URL一覧</title>
</head>
<body>
	<form action="url_list_remove">
		<table border="1">
			<tr>
				<th>URL</th>
				<th>削除</th>
			</tr>
			<% List<String> urlList = (List<String>) session.getAttribute("list");
		for (String item : urlList){
			out.println("<tr>");
			out.print("<td><a href="+item + ">"+ item +" </a></td>");
			out.println("<td><input type=\"checkbox\" name=\"delete\" value=\""+ item + "\"/></td><br />");
			out.println("</tr>");
		}
		%>
		</table>
		<input type="submit" value="更新" />
	</form>
	<a href="Index.html">もどる</a>
	<a href="Test.jsp">もどる->jsp</a>
	<hr />
</body>
</html>