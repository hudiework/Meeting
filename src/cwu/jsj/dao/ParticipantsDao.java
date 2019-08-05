package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cwu.jsj.entity.HotelBean;
import cwu.jsj.entity.PlaceBean;
import cwu.jsj.entity.WorkerBean;

/*根据传参得到sql语句，通过DBManager类的 createInstance() 方法实例化对象，
 * 调用本类的操作方法，完成数据操作。*/
public class ParticipantsDao {

	// 查找参会人员的全部数量（查找participant表）
	public int queryParticipantsNum(String meetingId) throws SQLException {
		String sql = "select count(*) as num from participant  where participantMeetingId="
				+ meetingId;
		DBManager dbManager = DBManager.createInstance();
		dbManager.connectDB(); // 连接数据库
		// 获取结果ResultSet
		ResultSet ret = dbManager.executeQuery(sql);

		if (ret.next()) {
			return ret.getInt("num");
		}
		dbManager.closeDB();
		return 0;
	}

	// 查找某一会议的各个子会议的报名人数
	public List<Map<String, Integer>> queryChildParticipantsNum(String meetingId)
			throws SQLException {
		String sql = "select childMeetingId from childMeeting  where mainMeetingId="
				+ meetingId;
		DBManager dbManager = DBManager.createInstance();
		dbManager.connectDB(); // 连接数据库
		// 获取结果ResultSet
		ResultSet ret = dbManager.executeQuery(sql);
		Map<String, Integer> map;
		List<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
		while (ret.next()) {
			map = new HashMap<String, Integer>();
			map.put("childMeetingId", ret.getInt(0));
			String sql2 = "select count(*) from participant_childMeeting_mapping where childMeetingId="
					+ ret.getInt(0);
			ResultSet ret2 = dbManager.executeQuery(sql2);
			if (ret2.next()) {
				map.put("participantNum", ret2.getInt(0));
			}
			list.add(map);
		}
		dbManager.closeDB();
		return list;
	}

}
