<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*"%>
<%
	List<Song> list = (List<Song>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>選曲お助けアプリ</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container" align="center">
<h2>SONG LIST</h2>
<a href="/karaoke_/Admin">楽曲データ管理へ</a>
<form method="post" action="/karaoke_/MainTag">
			テーマ別に絞り込み：
			<select name="tag" required>
				<option value="0">全表示</option>
				<option value="1">盛り上がる</option>
				<option value="2">1曲目</option>
				<option value="3">シメ</option>
				<option value="4">春</option>
				<option value="5">夏</option>
				<option value="6">秋</option>
				<option value="7">冬</option>
				<option value="8">ウェディング</option>
				<option value="9">送別会</option>
				<option value="10">歓迎会</option>
				<option value="11">同窓会</option>
				<option value="12">ラブソング</option>
				<option value="13">失恋</option>
				<option value="14">片思い</option>
			</select>
			<button type="submit">表示</button>
		</form>
	<% if(list != null && list.size()>0) { %>
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
			</tr>
		<% } %>
		</table>
	<% } %>
	<% if (list == null || list.size()<0) { %>
	<p>楽曲データが登録されていないか、読み込むことができません。
	<% } %>
</div>
</body>
</html>