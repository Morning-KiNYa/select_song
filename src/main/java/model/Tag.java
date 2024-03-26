package model;

import java.io.Serializable;

public class Tag implements Serializable {
	private int entryID;
	private int tagId;

	public Tag() {
	}

	public Tag(int songId, int tagId) {
		this.entryID = songId;
		this.tagId = tagId;
	}

	public int getEntryId() {
		return entryID;
	}

	public void setEntryId(int songId) {
		this.entryID = songId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

}
