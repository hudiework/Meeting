package cwu.jsj.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import zmmNew.ChildMeetingBean;
import cwu.jsj.entity.MeetingBean;
import cwu.jsj.entity.WorkerBean;

public class MeetingDao {
	private MeetingBean meeting;

	public int addMeeting(MeetingBean meetingBean) {
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;

		// 获取Sql查询语句
		String Sql = "insert into meeting(meetingName,meetingDate,meetingPlace,meetingContent,meetingAgenda,meetingGuide,meetingCompere,meetingOverDate) values(?,?,?,?,?,?,?,?)";

		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		conn = sql.connectDB2();

		int id = 0;// 存放数据库返回的用户注册过后的id
		try {
			ps = conn.prepareStatement(Sql, Statement.RETURN_GENERATED_KEYS);
			System.out.println("MeetingDao里的会议名："+meetingBean.getMeetingName());
			System.out.println("MeetingDao里的会议名："+meetingBean.getMeetingName());
			ps.setString(1, meetingBean.getMeetingName());
			ps.setString(2, meetingBean.getMeetingDate());
			ps.setString(3, meetingBean.getMeetingPlace());
			ps.setString(4, meetingBean.getMeetingContent());
			ps.setString(5, meetingBean.getMeetingAgenda());
			ps.setString(6, meetingBean.getMeetingGuide());
			ps.setString(7, meetingBean.getMeetingCompere());
			ps.setString(8, meetingBean.getMeetingOverDate());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();// 这一句代码就是得到插入的记录的id
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sql.closeDB();
		System.out.println("MeetingDao返回的主会议ID" + id);
		return id;
	}

	public MeetingBean selectMeetingByName(String name) {

		// 获取Sql查询语句
		String logSql = "select * from meeting where meetingName ='" + name
				+ "'";

		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();

		// 操作DB对象
		try {
			ResultSet rs = sql.executeQuery(logSql);
			if (rs.next()) {
				sql.closeDB();
				meeting = new MeetingBean(rs.getInt("meetingId"), rs.getString("meetingName"),
						rs.getString("meetingDate"), rs.getString("meetingPlace"), rs.getString("meetingContent"),
						rs.getString("meetingAgenda"), rs.getString("meetingGuide"), rs.getString("meetingCompere"),rs.getString("meetingOverDate"));
				sql.closeDB();
				return meeting;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql.closeDB();
		return null;
	}
	

	// 插入子会议
	public String InsertChild(int id, ChildMeetingBean childMeet) {
		String InSql = "insert into childMeeting(childMeetingName,childMeetingDate,childMeetingPlace,childMeetingContent,childMeetingCompere,mainMeetingId) "
				+ "values('"
				+ childMeet.getChildMeetingName()
				+ "','"
				+ childMeet.getChildMeetingTime()
				+ "','"
				+ childMeet.getChildMeetingPlace()
				+ "','"
				+ childMeet.getChildMeetingContent()
				+ "','"
				+ childMeet.getChildMeetingCompere() + "'," + id + ")";
		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();

		int ret = sql.executeUpdate(InSql);
		if (ret != 0) {
			sql.closeDB();
			System.out.println("插入子会议成功");
			return "success";
		}
		sql.closeDB();
		System.out.println("插入子会议失败");
		return "failure";

	}

	// 插入角色
	public String InsertWorkers(int meetingID, WorkerBean worker, String role) {
		String inSql = "insert into worker(meetingId,workerName,workerCompany,workerMobilePhone,role) values("
				+ meetingID
				+ ","
				+ '"'
				+ worker.getWorkerName()
				+ '"'
				+ ","
				+ '"'
				+ worker.getWorkerCompany()
				+ '"'
				+ ","
				+ '"'
				+ worker.getWorkerMobilePhone()
				+ '"'
				+ ","
				+ '"'
				+ role
				+ '"'
				+ ")";
		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();

		int ret = sql.executeUpdate(inSql);
		if (ret != 0) {
			sql.closeDB();
			System.out.println("插入工作人员表成功");
			return "success";
		}
		sql.closeDB();
		System.out.println("插入工作人员表失败");
		return "failure";

	}

	public MeetingBean selectMeetingByMeetingId(String meetingId) {
		// TODO 自动生成的方法存根
		// 获取Sql查询语句
				String logSql = "select * from meeting where meetingId ='" + meetingId
						+ "'";

				// 获取DB对象
				DBManager sql = DBManager.createInstance();
				sql.connectDB();

				// 操作DB对象
				try {
					ResultSet rs = sql.executeQuery(logSql);
					if (rs.next()) {
						sql.closeDB();
						meeting = new MeetingBean(rs.getInt("meetingId"), rs.getString("meetingName"),
								rs.getString("meetingDate"), rs.getString("meetingPlace"), rs.getString("meetingContent"),
								rs.getString("meetingAgenda"), rs.getString("meetingGuide"), rs.getString("meetingCompere"),rs.getString("meetingOverDate"));
						return meeting;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				sql.closeDB();
				return null;
	}
}
