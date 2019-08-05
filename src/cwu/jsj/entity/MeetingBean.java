package cwu.jsj.entity;

import java.io.Serializable;

/**
 * Created by Stephanie_z on 2018/3/16.
 */

public class MeetingBean implements Serializable{

	private Integer meetingId;
	private String meetingName, meetingDate, meetingPlace, meetingCompere,
			meetingContent, meetingAgenda, meetingGuide,myRole,meetingOverDate;

	private int meetingNumber;
	

	public MeetingBean(String meetingName, String meetingDate, String meetingPlace,
			String meetingContent, String meetingAgenda, String meetingGuide,
			String meetingCompere,String meetingOverDate) {
		super();
		this.meetingName = meetingName;
		this.meetingDate = meetingDate;
		this.meetingPlace = meetingPlace;
		this.meetingCompere = meetingCompere;
		this.meetingContent = meetingContent;
		this.meetingAgenda = meetingAgenda;
		this.meetingGuide = meetingGuide;
		this.meetingOverDate=meetingOverDate;
		
	}
	
	public MeetingBean(String meetingName, String meetingDate, String meetingPlace,
			String meetingContent, String meetingAgenda, String meetingGuide,
			String meetingCompere,String meetingOverDate,int meetingNumber) {
		super();
		this.meetingName = meetingName;
		this.meetingDate = meetingDate;
		this.meetingPlace = meetingPlace;
		this.meetingCompere = meetingCompere;
		this.meetingContent = meetingContent;
		this.meetingAgenda = meetingAgenda;
		this.meetingGuide = meetingGuide;
		this.meetingOverDate=meetingOverDate;
		this.meetingNumber=meetingNumber;
		
	}

	public int getMeetingNumber() {
		return meetingNumber;
	}

	public void setMeetingNumber(int meetingNumber) {
		this.meetingNumber = meetingNumber;
	}

	public MeetingBean(Integer meetingId, String meetingName, String meetingDate,
			String meetingPlace, String meetingContent, String meetingAgenda,
			String meetingGuide, String meetingCompere,String meetingOverDate) {
		super();
		this.meetingId = meetingId;
		this.meetingName = meetingName;
		this.meetingDate = meetingDate;
		this.meetingPlace = meetingPlace;
		this.meetingCompere = meetingCompere;
		this.meetingContent = meetingContent;
		this.meetingAgenda = meetingAgenda;
		this.meetingGuide = meetingGuide;
		this.meetingOverDate=meetingOverDate;
		
	}
	
	public MeetingBean( String meetingName, String meetingDate,
			String meetingPlace, String meetingContent, String meetingAgenda,
			String meetingGuide, String meetingCompere) {
		super();
		this.meetingId = meetingId;
		this.meetingName = meetingName;
		this.meetingDate = meetingDate;
		this.meetingPlace = meetingPlace;
		this.meetingCompere = meetingCompere;
		this.meetingContent = meetingContent;
		this.meetingAgenda = meetingAgenda;
		this.meetingGuide = meetingGuide;
		
	}

	public MeetingBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMeetingOverDate() {
		return meetingOverDate;
	}

	public void setMeetingOverDate(String meetingOverDate) {
		this.meetingOverDate = meetingOverDate;
	}
	public String getMyRole() {
		return myRole;
	}

	public void setMyRole(String myRole) {
		this.myRole = myRole;
	}


	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public String getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetingPlace() {
		return meetingPlace;
	}

	public void setMeetingPlace(String meetingPlace) {
		this.meetingPlace = meetingPlace;
	}

	public String getMeetingCompere() {
		return meetingCompere;
	}

	public void setMeetingCompere(String meetingCompere) {
		this.meetingCompere = meetingCompere;
	}

	public String getMeetingContent() {
		return meetingContent;
	}

	public void setMeetingContent(String meetingContent) {
		this.meetingContent = meetingContent;
	}

	public String getMeetingAgenda() {
		return meetingAgenda;
	}

	public void setMeetingAgenda(String meetingAgenda) {
		this.meetingAgenda = meetingAgenda;
	}

	public String getMeetingGuide() {
		return meetingGuide;
	}

	public void setMeetingGuide(String meetingGuide) {
		this.meetingGuide = meetingGuide;
	}
}
