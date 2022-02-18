package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="METADATA")
public class Metadata {

	@Id
	@Column(name="METADATA_PK")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long metadataPk;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DURATION")
	private long duration;
	
	@Column(name="EXPLICIT")
	private String explicit;
	
	@Column(name="ISRC")
	private String isrc;

	public long getMetadataPk() {
		return metadataPk;
	}

	public void setMetadataPk(long metadataPk) {
		this.metadataPk = metadataPk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getExplicit() {
		return explicit;
	}

	public void setExplicit(String explicit) {
		this.explicit = explicit;
	}

	public String getIsrc() {
		return isrc;
	}

	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}
	
	
}
