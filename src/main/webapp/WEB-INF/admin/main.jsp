<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*" %>
<%
	List<Song> list = (List<Song>)request.getAttribute("list");
	String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>楽曲データ管理</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style>
	input.form-control,
	.alert{
		width:500px;
	}
</style>
</head>
<body>
<br>
<div align="center">
<a href="/karaoke_/Main">トップへ戻る</a><br>
<% if(msg != null) { %>
<div class="alert alert-success" role="alert">
	<%=msg %>
</div>
<% } %>
<form action="/karaoke_/Admin" method="post" class="container">
<h2>楽曲データ追加</h2>
<table>
<tr><td>曲名</td><td><input type="text" name="title" required><small>*必須</small></td></tr>
<tr><td>歌手名</td><td><input type="text" name="artist" required><small>*必須</small></td></tr>
<tr><td>参考URL</td><td><input type="url" name="link"></td></tr>
<tr><td valign="top">ASIN</td><td><input type="text" name="artwork"><br><small>(Amazon商品ページURL内「/dp/」の後に続く10桁の英数字)</small></td></tr>
<tr><td>タグ</td><td>
	<label><input type="checkbox" name="tag" value="1">盛り上がる</label>
	<label><input type="checkbox" name="tag" value="2">1曲目</label>
	<label><input type="checkbox" name="tag" value="3">シメ</label><br>
	<label><input type="checkbox" name="tag" value="4">春</label>
	<label><input type="checkbox" name="tag" value="5">夏</label>
	<label><input type="checkbox" name="tag" value="6">秋</label>
	<label><input type="checkbox" name="tag" value="7">冬</label><br>
	<label><input type="checkbox" name="tag" value="8">ウェディング</label>
	<label><input type="checkbox" name="tag" value="9">送別会</label>
	<label><input type="checkbox" name="tag" value="10">歓迎会</label>
	<label><input type="checkbox" name="tag" value="11">同窓会</label><br>
	<label><input type="checkbox" name="tag" value="12">ラブソング</label>
	<label><input type="checkbox" name="tag" value="13">失恋</label>
	<label><input type="checkbox" name="tag" value="14">片思い</label>
</td></tr>
<tr><td>練習状況</td><td><select name="progress" required>
	<option value="十八番">十八番</option>
	<option value="習得済">習得済</option>
	<option value="練習中" selected>練習中</option>
	<option value="未着手">未着手</option>
</select><small>*必須</small></td></tr>
</table>
<button type="submit">登録</button>
</form>
</div>
<br>
<div align="center">
<% if(list != null && list.size()>0) { %>
	<h2>楽曲データ編集</h2>
	<table class="table table-bordered mt-5">
	<tr>
		<th></th>
		<th>曲名</th>
		<th>歌手名</th>
		<th>参考URL</th>
		<th>練習状況</th>
	</tr>
	<% for (Song s : list) { %>
		<tr>
			<td align="center"><img src="https://images-fe.ssl-images-amazon.com/images/P/<%=s.getArtwork() %>.09.TZZZZZZZ"></td>
			<td><%= s.getTitle() %></td>
			<td><%= s.getArtist() %></td>
			<td><a href="<%= s.getLink() %>">聴く</a></td>
			<td><%= s.getProgress() %></td>
			<td><a href="/karaoke_/Admin/Update?id=<%=s.getId() %>">編集</a></td>
			<td><a href="/karaoke_/Admin/Delete?id=<%=s.getId() %>" onclick="return confirm('削除してよろしいですか？')">削除</a></td>
		</tr>
	<% } %>
	</table>
<% } %>
</div>
</body>
</html>