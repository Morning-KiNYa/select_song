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
import model.Tag;

public class TagDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	private void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:comp/env/mariadb");
		this.con = ds.getConnection();
	}
	
	private void disconnect() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertTag(Tag tag) {
		try {
			this.connect();
			ps = con.prepareStatement("INSERT INTO tag_map(entry_id,tag_id) VALUES(?,?)");
			ps.setInt(1, tag.getEntryId());
			ps.setInt(2, tag.getTagId());
			ps.execute();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Song> findTag(int tagId){
		List<Song> list = new ArrayList<>();
		try {
			this.connect();
			ps = con.prepareStatement("SELECT * FROM songs JOIN tag_map ON songs.id=tag_map.entry_id WHERE tag_map.tag_id = ? GROUP BY songs.id");
			ps.setInt(1, tagId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String artist = rs.getString("artist");
				String link = rs.getString("link");
				String artwork = rs.getString("artwork");
				String progress = rs.getString("progress");
//				System.out.println("読み込んだ");
				list.add(new Song(id, title, artist, link, artwork, progress));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		} return list;
	}

}
