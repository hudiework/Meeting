package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cwu.jsj.entity.RoleBean;

public class RoleDao {
	public Boolean addRole(RoleBean role) {

		// 获取Sql查询语句
		String regSql = "insert into worker_meeting_mapping values('"
				+ role.getMeetingId() + "','" + role.getWorkerId() + "','"
				+ role.getRole() + "') ";

		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();

		int ret = sql.executeUpdate(regSql);
		if (ret != 0) {
			sql.closeDB();
			return true;
		}
		sql.closeDB();

		return false;
	}

	// 执行从function表中查询指定会议的全部工作人员数据,返回map对象
	public List<Map<String, String>> queryAllWorFrMapping(String meetingId)
			throws SQLException {
		// 1.由会议号meetingId 查找worker_meeting_mapping所有的workerId
		String sql1 = "select * from worker where meetingId="
				+ meetingId;
		// DBManager dbManager = new DBManager();
		// dbManager.creatConnection(); // 连接数据库
		// 获取DB对象
		DBManager dbManager = DBManager.createInstance();
		dbManager.connectDB();
		// 获取结果ResultSet
		ResultSet ret1 = dbManager.executeQuery(sql1);
		String str = "select * from function where workerId=";
		while (ret1.next()) {
			int workerId = ret1.getInt("workerId");
			str = str + workerId + " or workerId=";
		}
		System.out.println("输出str:"+str);
		String sql2 = str.substring(0, str.length() - 12);
		System.out.println("截取字符后的字符串输出为：" + sql2);
		ResultSet ret2 = dbManager.executeQuery(sql2);
		// 获取结果的列数
		ResultSetMetaData metaData = ret2.getMetaData();
		int columnCount = metaData.getColumnCount();
		System.out.println("打印输出结果列数:应该是3" + columnCount);
		// 返回的map对象
		Map<String, String> map;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 遍历ResultSet中的每条数据

		// 遍历每一列
		while (ret2.next()) {
			map = new HashMap<String, String>();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnLabel(i);
				String value = ret2.getString(columnName);
				System.out.println("map 中的columnName：" + columnName);
				System.out.println("map 中的value：" + value);
				map.put(columnName, value);
			}
			list.add(map);
		}
		dbManager.closeDB();
		return list;
	}
}
