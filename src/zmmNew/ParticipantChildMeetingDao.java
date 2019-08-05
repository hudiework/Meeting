package zmmNew;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cwu.jsj.dao.DBManager;
import cwu.jsj.entity.MeetingBean;

public class ParticipantChildMeetingDao {
	/**
	 * 根据主会议id获取它所有的子会议
	 * */
	public List<ChildMeetingBean> queryChildMeetingByMainMeetingId(
			String meetingId) throws SQLException {
		List<ChildMeetingBean> childMeetingList = new ArrayList<ChildMeetingBean>();
		ChildMeetingBean childMeeting;
		// 先查找到所有报过名的会议

		System.out.println("输出方法中的mainmeetingid:" + meetingId);
		String sql = "select * from childMeeting  where mainMeetingId="
				+ meetingId;

		// 连结数据库
		DBManager dbManager = DBManager.createInstance();
		dbManager.connectDB();

		// 获取结果ResultSet
		ResultSet participantChildMeeting = (ResultSet) dbManager
				.executeQuery(sql);

		while (participantChildMeeting.next()) {
			// 获取到meetingID
			int childMeetingId = participantChildMeeting
					.getInt("childMeetingId");
			String childMeetingName = participantChildMeeting
					.getString("childMeetingName");
			String childMeetingTime = participantChildMeeting
					.getString("childMeetingDate");
			String childMeetingPlace = participantChildMeeting
					.getString("childMeetingPlace");
			String childMeetingContent = participantChildMeeting
					.getString("childMeetingContent");
			String childMeetingCompere = participantChildMeeting
					.getString("childMeetingCompere");
			childMeeting = new ChildMeetingBean(childMeetingId,
					childMeetingName, childMeetingTime, childMeetingPlace,
					childMeetingContent, childMeetingCompere);
			childMeetingList.add(childMeeting);
		}
		return childMeetingList;
	}

	/**
	 * 根据参会人员手机号获取她报名的该主会议的所有子会议
	 * 
	 * @throws SQLException
	 * */
	public List<ChildMeetingBean> getMyChildmeetingByParticipantTelNum(String telNum,
			String mainMeetingId) throws SQLException {
		
		List<ChildMeetingBean> childMeetingList = new ArrayList<ChildMeetingBean>();
		ChildMeetingBean childMeeting;
		// 先查找到所有报过名的会议

		System.out.println("输出方法中的mainmeetingid:" + mainMeetingId);
		String sql = "select * from childMeeting where mainMeetingId="
				+ mainMeetingId
				+ " and childMeetingId in("
				+ "select childMeetingId from participant_childMeeting_mapping where participantId ="
				+ telNum + ")";

		// 连结数据库
		DBManager dbManager = DBManager.createInstance();
		dbManager.connectDB();

		// 获取结果ResultSet
		ResultSet participantChildMeeting = (ResultSet) dbManager
				.executeQuery(sql);

		while (participantChildMeeting.next()) {
			// 获取到meetingID
			int childMeetingId = participantChildMeeting
					.getInt("childMeetingId");
			String childMeetingName = participantChildMeeting
					.getString("childMeetingName");
			String childMeetingTime = participantChildMeeting
					.getString("childMeetingDate");
			String childMeetingPlace = participantChildMeeting
					.getString("childMeetingPlace");
			String childMeetingContent = participantChildMeeting
					.getString("childMeetingContent");
			String childMeetingCompere = participantChildMeeting
					.getString("childMeetingCompere");
			childMeeting = new ChildMeetingBean(childMeetingId,
					childMeetingName, childMeetingTime, childMeetingPlace,
					childMeetingContent, childMeetingCompere);
			childMeetingList.add(childMeeting);
		}
		return childMeetingList;
	}
}
