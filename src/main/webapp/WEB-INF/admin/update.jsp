<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*" %>
<%
	Song song = (Song)request.getAttribute("song");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>楽曲データ編集</title>
</head>
<body>
<form action="/karaoke_/Admin/Update" method="post">
	<table>
		<tr><td>曲名</td><td><input type="text" name="title" value="<%=song.getTitle() %>" required>*必須</td></tr>
		<tr><td>歌手名</td><td><input type="text" name="artist" value="<%=song.getArtist() %>" required>*必須</td></tr>
		<tr><td>参考URL</td><td><input type="url" name="link" value="<%=song.getLink() %>"></td></tr>
		<tr><td>ASIN</td><td><input type="text" name="artwork" value="<%=song.getArtwork() %>"><br>(Amazon商品ページURL内「/dp/」の後に続く10桁の英数字。アートワークを取得します)</td></tr>
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
			<option value="十八番" <% if(song.getProgress().equals("十八番")){ %>selected<% } %>>十八番</option>
			<option value="習得済" <% if(song.getProgress().equals("習得済")){ %>selected<% } %>>習得済</option>
			<option value="練習中" <% if(song.getProgress().equals("練習中")){ %>selected<% } %>>練習中</option>
			<option value="未着手" <% if(song.getProgress().equals("未着手")){ %>selected<% } %>>未着手</option>
		</select>*必須</td></tr>
	</table>
	<input type="hidden" name="id" value="<%=song.getId() %>">
	<button type="submit">更新</button>
</form>
</body>
</html>