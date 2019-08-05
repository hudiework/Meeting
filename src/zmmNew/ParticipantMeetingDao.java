package zmmNew;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cwu.jsj.dao.DBManager;
import cwu.jsj.entity.MeetingBean;


//参会方查看报了名还没结束的会议和报过名结束了的历史会议
public class ParticipantMeetingDao {
public List<List<MeetingBean>> queryParticipantOngingMeeting(String telNum) throws SQLException{
	List<List<MeetingBean>> list=new ArrayList<List<MeetingBean>>();
	List<MeetingBean> ongoingMeetingList=new ArrayList<MeetingBean>();
	List<MeetingBean> meetingOverList=new ArrayList<MeetingBean>();
	MeetingBean meeting;
	//先查找到所有报过名的会议
	
	String sql = "select * from participant  where participantMobilePhone="+telNum;
	
	//连结数据库
	DBManager dbManager = DBManager.createInstance();
	dbManager.connectDB();
	
	// 获取结果ResultSet
	ResultSet participantMeeting = (ResultSet) dbManager.executeQuery(sql);
	List<Integer> meetingIdList=new ArrayList<Integer>();
	//System.out.println("查询到改参会人与"+participantMeeting.next());
	while(participantMeeting.next()){
		//获取到meetingID
		
		int meetingId=participantMeeting.getInt("participantMeetingId");
		meetingIdList.add(meetingId);
		System.out.println("meetingId:::::"+meetingId);
	}
	 //select * from meeting order by STR_TO_DATA(meetingDate,'%Y-%m-%d') desc
	for (int i=0;i<meetingIdList.size();i++){
		String sqll="select * from meeting  where meetingId="+meetingIdList.get(i);
		ResultSet meetingRS=(ResultSet)dbManager.executeQuery(sqll);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//将字符串转化成Date类型
		Date overDate = null;
		Date todayDate=new Date();
		while (meetingRS.next()){
			meeting=new MeetingBean();
			meeting.setMeetingId(meetingRS.getInt("meetingId"));
			meeting.setMeetingName(meetingRS.getString("meetingName"));
			meeting.setMeetingDate(meetingRS.getString("meetingDate"));
			meeting.setMeetingPlace(meetingRS.getString("meetingPlace"));
			meeting.setMeetingCompere(meetingRS.getString("meetingCompere"));
			meeting.setMeetingContent(meetingRS.getString("meetingContent"));
			meeting.setMeetingAgenda(meetingRS.getString("meetingAgenda"));
			meeting.setMeetingGuide(meetingRS.getString("meetingGuide"));
			String meetingOverDate=meetingRS.getString("meetingOverDate");
			meeting.setMeetingOverDate(meetingOverDate);
			System.out.println("查询到会议"+meeting.getMeetingAgenda());
		        try {
		        	overDate = dateFormat.parse(meetingOverDate);
		        System.out.println(overDate.toLocaleString().split(" ")[0]);//切割掉不要的时分秒数据
		        } catch (ParseException e) {
		            e.printStackTrace();
		        }
		        //查找到已经结束的会议
			if(overDate.before(todayDate)){
				
				meetingOverList.add(meeting);
				System.out.println("啦啦啦meetingOverList.sise"+meetingOverList.size());
			}else{
				//这里是进行中的会议（或还未结束的）
				ongoingMeetingList.add(meeting);
				System.out.println("啦啦啦meetingOverList.sise"+ongoingMeetingList.size());
				
			}
		}
	}
	list.add(meetingOverList);
	list.add(ongoingMeetingList);
	dbManager.closeDB();
	return list;
}


}


