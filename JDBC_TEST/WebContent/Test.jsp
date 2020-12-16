<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>access from JSP</title>
</head>
<body>
	<h1>URLのリスト</h1>
	<% if(request.getAttribute("login") == null){
		out.print("<h3>ログアウトしました。</h3>");
		out.print("<a href= \"Index.html\" >ログイン画面へ</a>");

	}else{
		out.print("<h3>ログインしています。</h3>");
	}
	%>

	<form action="url_list" method ="post">
		URLを入力:<input type="text" name="url" />
				 <input type="submit"
			value="追加" />
			<input type="hidden" name="epoc" value="<%= System.currentTimeMillis() %>"/>
	</form>
	<a href="url_list_view">URLの一覧を表示</a>
	<a href="url_list_iv">ログアウト</a>
	<form action="url_list_iv" method="get">
		<input type="submit" value="ログアウト" />
	</form>

</body>
</html>