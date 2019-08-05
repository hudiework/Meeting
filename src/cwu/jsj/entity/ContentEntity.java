package cwu.jsj.entity;

public class ContentEntity{

	private String meeting;
	private String content;
	private String date;
	private String tag;

	public ContentEntity(String content,String date, String tag) {
		super();
		this.content = content;
		this.date=date;
		this.tag = tag;
	}

	public String getMeeting() {
		return meeting;
	}

	public void setMeeting(String meeting) {
		this.meeting = meeting;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
