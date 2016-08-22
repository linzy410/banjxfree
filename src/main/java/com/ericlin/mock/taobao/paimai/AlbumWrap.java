package com.ericlin.mock.taobao.paimai;

import java.util.List;

public class AlbumWrap {
	private String date;
	private Integer albumNum;
	private List<Album> albums;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getAlbumNum() {
		return albumNum;
	}

	public void setAlbumNum(Integer albumNum) {
		this.albumNum = albumNum;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
}
