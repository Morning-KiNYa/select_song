package model;

import java.io.Serializable;

public class Song implements Serializable {
	private int id;
	private String title;
	private String artist;
	private String link; // 練習用の参考音源
	private String artwork; // アートワーク取得用のASIN
	private String progress;
	
	public Song() {}

	public Song(int id, String title, String artist, String link, String artwork, String progress) {
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.link = link;
		this.artwork = artwork;
		this.progress = progress;
	}

	public Song(String title, String artist, String link, String artwork, String progress) {
		this.title = title;
		this.artist = artist;
		this.link = link;
		this.artwork = artwork;
		this.progress = progress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getArtwork() {
		return artwork;
	}

	public void setArtwork(String artwork) {
		this.artwork = artwork;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}
	
	
}
