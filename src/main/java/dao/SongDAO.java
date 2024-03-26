package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Song;

public class SongDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	private void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/mariadb");
		this.con = ds.getConnection();
	}

	private void disconnect() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ConnectCheck() {
		try {
			this.connect();
			System.out.println("接続成功");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
	}

	public void insertOne(Song song) {
		try {
			this.connect();
			ps = con.prepareStatement("INSERT INTO songs(title,artist,link,artwork,progress) VALUES(?,?,?,?,?)");
			//			System.out.println(song.getTitle());
			ps.setString(1, song.getTitle());
			//			System.out.println(song.getArtist());
			ps.setString(2, song.getArtist());
			//			System.out.println(song.getLink());
			ps.setString(3, song.getLink());
			//			System.out.println(song.getArtwork());
			ps.setString(4, song.getArtwork());
			//			System.out.println(song.getProgress());
			ps.setString(5, song.getProgress());
			ps.execute();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
	}

	public List<Song> findAll() {
		List<Song> list = new ArrayList<>();
		try {
			this.connect();
			ps = con.prepareStatement("SELECT * FROM songs");
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String artist = rs.getString("artist");
				String link = rs.getString("link");
				String artwork = rs.getString("artwork");
				String progress = rs.getString("progress");
				list.add(new Song(id, title, artist, link, artwork, progress));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return list;
	}

	public Song findOne(int id) {
		Song song = null;
		try {
			this.connect();
			ps = con.prepareStatement("SELECT * FROM songs WHERE id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				String title = rs.getString("title");
				String artist = rs.getString("artist");
				String link = rs.getString("link");
				String artwork = rs.getString("artwork");
				String progress = rs.getString("progress");
				song = new Song(id,title,artist,link,artwork,progress);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		} return song;
	}
	
	public void updateOne(Song song) {
		try {
			this.connect();
			ps = con.prepareStatement("UPDATE songs SET title=?,artist=?,link=?,artwork=?,progress=? WHERE id=?");
			ps.setString(1, song.getTitle());
			ps.setString(2, song.getArtist());
			ps.setString(3, song.getLink());
			ps.setString(4, song.getArtwork());
			ps.setString(5, song.getProgress());
			ps.setInt(6, song.getId());
			ps.executeUpdate();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
	}
	
	public void deleteOne(int id) {
		try {
			this.connect();
			ps=con.prepareStatement("DELETE FROM songs WHERE id=?");
			ps.setInt(1, id);
			ps.execute();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
	}
}
