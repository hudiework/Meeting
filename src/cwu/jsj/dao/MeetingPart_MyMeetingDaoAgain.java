package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cwu.jsj.entity.MeetingBean;

public class MeetingPart_MyMeetingDaoAgain {
	//查找举办过的所有会议（包含进行中的和历史会议）
	public List<List<MeetingBean>> queryWorkerMeeting(String telNum) throws SQLException{
		List<List<MeetingBean>> list=new ArrayList<List<MeetingBean>>();
		List<MeetingBean> ongoingMeetingList=new ArrayList<MeetingBean>();
		List<MeetingBean> meetingOverList=new ArrayList<MeetingBean>();
		MeetingBean meeting;
		
		//先查找到所有举办过的会议
		String sql = "select meetingId,role from worker  where workerMobilePhone="+telNum;
		
		//连结数据库
		DBManager dbManager = DBManager.createInstance();
		dbManager.connectDB();
		
		// 获取结果ResultSet
		ResultSet workerMeeting = (ResultSet) dbManager.executeQuery(sql);
		List<Integer> meetingIdList=new ArrayList<Integer>();
		List<String> myRole=new ArrayList<String>();
		while(workerMeeting.next()){
			//获取到meetingID
			int meetingId=workerMeeting.getInt("meetingId");
			String role=workerMeeting.getString("role");
			meetingIdList.add(meetingId);
			myRole.add(role);
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
				meeting.setMyRole(myRole.get(i));
				String meetingOverDate=meetingRS.getString("meetingOverDate");
				meeting.setMeetingOverDate(meetingOverDate);
			        try {
			        	overDate = dateFormat.parse(meetingOverDate);
			        System.out.println(overDate.toLocaleString().split(" ")[0]);//切割掉不要的时分秒数据
			        } catch (ParseException e) {
			            e.printStackTrace();
			        }
			        //查找到已经结束的会议
				if(overDate.before(todayDate)){
					meetingOverList.add(meeting);
				}else{
					//这里是进行中的会议（或还未结束的）
					ongoingMeetingList.add(meeting);
					
				}
			}
		}
		list.add(meetingOverList);
		list.add(ongoingMeetingList);
		dbManager.closeDB();
		return list;
	}
}
