package cwu.jsj.entity;

public class MessageEntity{
	
	private String meeting;
    private String name;
	private String date;
	private String message;
	private String role;
	
	public MessageEntity() {
	}

	public MessageEntity(String date,String message) {
		this.date = date;
		this.message = message;
	}
      
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMeeting() {
		return meeting;
	}

	public void setMeeting(String meeting) {
		this.meeting = meeting;
	}
    
}
