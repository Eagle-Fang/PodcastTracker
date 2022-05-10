package com.skilldistillery.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Episode {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String summary;
	
	@Column (name="image_url")
	private String imageUrl;

	private String link;
	
	private boolean explicit;
	
	@Column (name="release_date")
	private String released;
	
	@Column (name="episode_number")
	private Integer epNum;
	
	private Integer season;
	
	private String copyright; 
	
	
	private Integer length;
	
	@ManyToOne
	@JoinColumn (name="podcast_id")
	private Podcast podcast;

	public Episode() {
		super();
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isExplicit() {
		return explicit;
	}

	public void setExplicit(boolean explicit) {
		this.explicit = explicit;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public Integer getEpNum() {
		return epNum;
	}

	public void setEpNum(Integer epNum) {
		this.epNum = epNum;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Podcast getPodcast() {
		return podcast;
	}

	public void setPodcast(Podcast podcast) {
		this.podcast = podcast;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, season);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Episode other = (Episode) obj;
		return id == other.id && Objects.equals(season, other.season);
	}

	public Episode(int id, String title, String summary, String imageUrl, String link, boolean explicit,
			String released, Integer epNum, Integer season, String copyright, Integer length, Podcast podcast) {
		super();
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.imageUrl = imageUrl;
		this.link = link;
		this.explicit = explicit;
		this.released = released;
		this.epNum = epNum;
		this.season = season;
		this.copyright = copyright;
		this.length = length;
		this.podcast = podcast;
	}

	@Override
	public String toString() {
		return "Episode [id=" + id + ", title=" + title + ", summary=" + summary + ", imageUrl=" + imageUrl + ", link="
				+ link + ", explicit=" + explicit + ", released=" + released + ", epNum=" + epNum + ", season=" + season
				+ ", copyright=" + copyright + ", length=" + length + ", podcast=" + podcast + "]";
	}
	
	
	
}
