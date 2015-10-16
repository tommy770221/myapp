package com.tommy.model.youtube;



public class YoutubeSearchItems {
	
	private String kind;
	
	private String etag;
	
	private YoutubeSearchItemsId id;
	
	private YoutubeSearchItemsSnippet snippet;

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

	public YoutubeSearchItemsId getId() {
		return id;
	}

	public void setId(YoutubeSearchItemsId id) {
		this.id = id;
	}

	public YoutubeSearchItemsSnippet getSnippet() {
		return snippet;
	}

	public void setSnippet(YoutubeSearchItemsSnippet snippet) {
		this.snippet = snippet;
	}
	
	
	
	
	

}
