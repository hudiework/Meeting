package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*根据传参得到sql语句，通过DBManager类的 createInstance() 方法实例化对象，
 * 调用本类的操作方法，完成数据操作。*/
public class FilesDao {

	static int SAVEFILE_FAILED = 6;
	static int SAVEFILE_SUCCEEDED = 7;

	public int PostFiles(String filename,int meetingId) {
		// 获取Sql查询语句
		String saveSql = "insert into savefiles(filename,meetingId) values('" + filename
				+ "',"+meetingId+") ";

		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();

		int ret = sql.executeUpdate(saveSql);
		if (ret != 0) {
			sql.closeDB();
			return SAVEFILE_SUCCEEDED;
		}
		sql.closeDB();

		return SAVEFILE_FAILED;
	}

	public List<String> QueryAllFiles(int meetingId) {

		List<String> list = new ArrayList<String>();

		// 获取Sql查询语句
		String querrySql = "select * from savefiles where meetingId = "+meetingId;

		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();

		// 操作DB对象
		try {
			ResultSet rs = sql.executeQuery(querrySql);
			while (rs.next()) {
				String filename = rs.getString("filename");
				list.add(filename);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql.closeDB();
		return list;
	}

	public Boolean QueryFile(String filename,int meetingId) {
		// 获取Sql查询语句
		String querrySql = "select * from savefiles where filename='"
				+ filename + "'"+"and meetingId="+meetingId;

		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();
		// 操作DB对象
		ResultSet rs = sql.executeQuery(querrySql);
		try {
			if (rs.next()) {
				System.out.println("结果集不不为空，已存在了！");
				sql.closeDB();
				return false; // 已存在了
			} else {
				System.out.println("结果集为空空,此文件名不存在！！");
				sql.closeDB();
				return true; // 不存在
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql.closeDB();
		return true; // 不存在
	}

	public int UpdateFile(String filename) {
		int ret = 0;
		String updateSql = "update savefiles set filename ='" + filename
				+ "'where filename = '" + filename + "'";
		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();

		ret = sql.executeUpdate(updateSql);
		sql.closeDB();
		return ret;
	}

}
