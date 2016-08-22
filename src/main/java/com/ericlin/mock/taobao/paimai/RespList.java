package com.ericlin.mock.taobao.paimai;

import java.util.List;

public class RespList {


	private Long nowTime;
	private String link;
	private boolean isRecommend;
	private List<AlbumWrap> list;

	public Long getNowTime() {
		return nowTime;
	}

	public void setNowTime(Long nowTime) {
		this.nowTime = nowTime;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isRecommend() {
		return isRecommend;
	}

	public void setRecommend(boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	public List<AlbumWrap> getList() {
		return list;
	}

	public void setList(List<AlbumWrap> list) {
		this.list = list;
	}


}
