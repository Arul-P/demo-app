package com.example.demo.pojo;

import java.util.ArrayList;

public class Item{
    public Album album;
    public ArrayList<Artist> artists;
    public ArrayList<String> available_markets;
    public int disc_number;
    public int duration_ms;
    public boolean explicit;
    public ExternalIds external_ids;
    public ExternalUrls external_urls;
    public String href;
    public String id;
    public boolean is_local;
    public String name;
    public int popularity;
    public Object preview_url;
    public int track_number;
    public String type;
    public String uri;
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public ArrayList<Artist> getArtists() {
		return artists;
	}
	public void setArtists(ArrayList<Artist> artists) {
		this.artists = artists;
	}
	public ArrayList<String> getAvailable_markets() {
		return available_markets;
	}
	public void setAvailable_markets(ArrayList<String> available_markets) {
		this.available_markets = available_markets;
	}
	public int getDisc_number() {
		return disc_number;
	}
	public void setDisc_number(int disc_number) {
		this.disc_number = disc_number;
	}
	public int getDuration_ms() {
		return duration_ms;
	}
	public void setDuration_ms(int duration_ms) {
		this.duration_ms = duration_ms;
	}
	public boolean isExplicit() {
		return explicit;
	}
	public void setExplicit(boolean explicit) {
		this.explicit = explicit;
	}
	public ExternalIds getExternal_ids() {
		return external_ids;
	}
	public void setExternal_ids(ExternalIds external_ids) {
		this.external_ids = external_ids;
	}
	public ExternalUrls getExternal_urls() {
		return external_urls;
	}
	public void setExternal_urls(ExternalUrls external_urls) {
		this.external_urls = external_urls;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isIs_local() {
		return is_local;
	}
	public void setIs_local(boolean is_local) {
		this.is_local = is_local;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	public Object getPreview_url() {
		return preview_url;
	}
	public void setPreview_url(Object preview_url) {
		this.preview_url = preview_url;
	}
	public int getTrack_number() {
		return track_number;
	}
	public void setTrack_number(int track_number) {
		this.track_number = track_number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
    
    
}
