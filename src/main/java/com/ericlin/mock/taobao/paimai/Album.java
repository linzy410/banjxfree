package com.ericlin.mock.taobao.paimai;

import java.io.Serializable;

public class Album implements Serializable {

	private static final long serialVersionUID = 535825394304759842L;
	private Long albumsId;
	private String albumUrl;
	private String title;
	private String bannerUrl;
	private Long startTime;
	private Long endTime;
	private String startTimeStr;
	private String endTimeStr;
	private Integer status;
	private Integer bidCount;
	private String sellerNick;
	private Long sellerId;
	private Integer bidNum;
	private Integer onlookerNum;
	private Boolean recomment;
	private Integer activityTag;

	public Long getAlbumsId() {
		return albumsId;
	}

	public void setAlbumsId(Long albumsId) {
		this.albumsId = albumsId;
	}

	public String getAlbumUrl() {
		return albumUrl;
	}

	public void setAlbumUrl(String albumUrl) {
		this.albumUrl = albumUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBidCount() {
		return bidCount;
	}

	public void setBidCount(Integer bidCount) {
		this.bidCount = bidCount;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getBidNum() {
		return bidNum;
	}

	public void setBidNum(Integer bidNum) {
		this.bidNum = bidNum;
	}

	public Integer getOnlookerNum() {
		return onlookerNum;
	}

	public void setOnlookerNum(Integer onlookerNum) {
		this.onlookerNum = onlookerNum;
	}

	public Boolean getRecomment() {
		return recomment;
	}

	public void setRecomment(Boolean recomment) {
		this.recomment = recomment;
	}

	public Integer getActivityTag() {
		return activityTag;
	}

	public void setActivityTag(Integer activityTag) {
		this.activityTag = activityTag;
	}
}
