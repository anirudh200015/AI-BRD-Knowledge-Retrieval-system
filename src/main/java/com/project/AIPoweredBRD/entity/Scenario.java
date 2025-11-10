package com.project.AIPoweredBRD.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Scenario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	private String BRDName;
	private String ticketId;
	private String uploadedBy;
	private Date uploadedDate;
	private String url;
	
	@JsonIgnore
    @Column(columnDefinition = "TEXT")
	private String embedding;
	
	@JsonIgnore
	private String extractedText;
	
	
	public Scenario() {
		
	}
	
	public Scenario(String BRDName, String ticketId, String uploadedBy, Date uploadedDate, String url, String embedding) {
		
		this.BRDName = BRDName;
		this.ticketId = ticketId;
		this.uploadedBy = uploadedBy;
		this.uploadedDate = uploadedDate;
		this.url=url;
		this.embedding=embedding;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getBRDName() {
		return BRDName;
	}


	public void setBRDName(String BRDName) {
		this.BRDName = BRDName;
	}


	public String getTicketId() {
		return ticketId;
	}


	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}


	public String getUploadedBy() {
		return uploadedBy;
	}


	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}


	public Date getUploadedDate() {
		return uploadedDate;
	}


	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmbedding() {
		return embedding;
	}

	public void setEmbedding(String embedding) {
		this.embedding = embedding;
	}

	public String getExtractedText() {
		return extractedText;
	}

	public void setExtractedText(String extractedText) {
		this.extractedText = extractedText;
	}
	
	
	
	
}
