package com.project.AIPoweredBRD.entity;

public class ScenarioDTO {

	private String BRD_name;
	private String ticketId;
	public ScenarioDTO(String bRD_name, String ticketId) {
		super();
		BRD_name = bRD_name;
		this.ticketId = ticketId;
	}
	public String getBRD_name() {
		return BRD_name;
	}
	public void setBRD_name(String bRD_name) {
		BRD_name = bRD_name;
	}
	public String getTicketId() {
		return ticketId;
	}
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	
	
}
