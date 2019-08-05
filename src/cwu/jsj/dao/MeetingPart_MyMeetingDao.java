package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cwu.jsj.entity.MeetingBean;

public class MeetingPart_MyMeetingDao {
	//根据登录手机号 查找该人员所涉及的会议
		public List<MeetingBean> queryAllMeetingByTel(String telNum) throws SQLException{
			String participant_sql = "select * from participant  where participantMobilePhone="+telNum;
			String worker_sql="select * from worker  where workerMobilePhone="+telNum;
			//连结数据库
			DBManager dbManager = DBManager.createInstance();
			dbManager.connectDB();
			
			// 获取结果ResultSet
			ResultSet participant_rst = (ResultSet) dbManager.executeQuery(participant_sql);
			ResultSet worker_rst=(ResultSet) dbManager.executeQuery(worker_sql);
			List<MeetingBean> meetingList=new ArrayList<MeetingBean>();
			if(participant_rst.next()){
				System.out.println("参加过会议！！");
				//参加过会议
				//这是在participant表中查到的第一个会议，她可能参加多个会议,因此需要while循环
				String participantId= participant_rst.getString("participantId");
				String sql="select participantMeetingId from participant where participantId="+participantId;
				ResultSet rst=(ResultSet) dbManager.executeQuery(sql);
				MeetingBean meeting;
				if(rst.next()){
					int meetingId=rst.getInt("participantMeetingId");
					meeting=new MeetingBean();
					meeting=queryMeetingById(meetingId+"");
					meeting.setMyRole("participant");
					meetingList.add(meeting);
				}
				while (participant_rst.next()){
					int meetingIds=participant_rst.getInt("participantMeetingId");
					meeting=new MeetingBean();
					meeting=queryMeetingById(meetingIds+"");
					meeting.setMyRole("participant");
					meetingList.add(meeting);
				}
			}
			if(worker_rst.next()){
			System.out.println("举办过会议！！！");
				//举办过会议
				//举办过会议
				String workerId= worker_rst.getString("workerId");
				System.out.println("输出workerID："+workerId);
				String sql="select meetingId from worker_meeting_mapping where workerId="+workerId;
				ResultSet rst=(ResultSet) dbManager.executeQuery(sql);
				MeetingBean meeting;
				while(rst.next()){
					int meetingId=rst.getInt("meetingId");
					System.out.println("输出meetingId："+meetingId);
					meeting=new MeetingBean();
					meeting=queryMeetingById(2+"");
					String sqll="select role from worker_meeting_mapping where meetingId="+meetingId+" and workerId="+workerId;
					ResultSet roleRst= (ResultSet) dbManager.executeQuery(sqll);
					if(roleRst.next()){
						meeting.setMyRole(roleRst.getString("role"));
					}
					meetingList.add(meeting);
					/***这里做了假数据，未解决乱码问题，记得将2替换成meetingId****/
				}
			}
			if(meetingList.size()==0){
				dbManager.closeDB();
				return null;//新用户，还没有任何会议
			}else{
				dbManager.closeDB();
				System.out.println("成功返回结果i哦："+meetingList.size());
				return meetingList;
			}
			
		}
		
		//根据meetingId查找相关会议
		public MeetingBean queryMeetingById(String meetingId) throws SQLException{
			String sql="select * from meeting where meetingId="+meetingId;
			DBManager dbManager = DBManager.createInstance();
			dbManager.connectDB();
			// 获取结果ResultSet
			ResultSet rst = (ResultSet) dbManager.executeQuery(sql);
			MeetingBean meeting=new MeetingBean();
			if(rst.next()){
				System.out.println("输出meetingName："+rst.getString("meetingName"));
				meeting.setMeetingName(rst.getString("meetingName"));
				meeting.setMeetingAgenda(rst.getString("meetingAgenda"));
				meeting.setMeetingCompere(rst.getString("meetingCompere"));
				meeting.setMeetingContent(rst.getString("meetingContent"));
				meeting.setMeetingDate(rst.getString("meetingDate"));
				meeting.setMeetingGuide(rst.getString("meetingGuide"));
				meeting.setMeetingPlace(rst.getString("meetingPlace"));
				System.out.println("获取到meetingName+"+meeting.getMeetingName());
			}
			dbManager.closeDB();
			return meeting;
		}

}
