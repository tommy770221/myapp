package com.tommy.model.youtube;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class YoutubeSearch {

	@JsonProperty("kind")
	private String kind;
	
	private String etag;
	
	private String nextPageToken;
	
	private YoutubeSearchPageInfo pageInfo;
	
	private List<YoutubeSearchItems> items;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public String getNextPageToken() {
		return nextPageToken;
	}

	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}

	public YoutubeSearchPageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(YoutubeSearchPageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public List<YoutubeSearchItems> getItems() {
		return items;
	}

	public void setItems(List<YoutubeSearchItems> items) {
		this.items = items;
	}

    

}
