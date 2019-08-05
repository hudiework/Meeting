package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDao {
	static int LOGIN_FAILED = 0;
	static int LOGIN_SUCCEEDED = 1;
	static int REGISTER_FAILED = 2;
	static int REGISTER_SUCCEEDED = 3;
	static int RESET_FAILED = 4;
	static int RESET_SUCCEEDED = 5;

	public int login(String mobilephone, String password) {

		// 获取Sql查询语句
		String logSql = "select * from register where registerMobilePhone ='"
				+ mobilephone + "' and password ='" + password + "'";

		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();

		// 操作DB对象
		try {
			ResultSet rs = sql.executeQuery(logSql);
			if (rs.next()) {
				sql.closeDB();
				return LOGIN_SUCCEEDED;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql.closeDB();
		return LOGIN_FAILED;
	}

	public int register(String mobilephone, String password,String imagePath) {

		// 获取Sql查询语句
		String regSql = "insert into register(registerMobilePhone,password,imagePath) values('"
				+ mobilephone + "','" + password  + "','"+imagePath+"') ";

		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();

		int ret = sql.executeUpdate(regSql);
		if (ret != 0) {
			sql.closeDB();
			return REGISTER_SUCCEEDED;
		}
		sql.closeDB();

		return REGISTER_FAILED;
	}

	public int reset(String mobilephone, String newPassword) {

		// 获取Sql查询语句
		String regSql = "update register set password = '" + newPassword
				+ "' where registerMobilePhone ='" + mobilephone + "'";

		// 获取DB对象
		DBManager sql = DBManager.createInstance();
		sql.connectDB();

		int ret = sql.executeUpdate(regSql);
		if (ret != 0) {
			sql.closeDB();
			return RESET_SUCCEEDED;
		}
		sql.closeDB();

		return RESET_FAILED;
	}

	public Boolean checkPhone(String mobilephone) {
		// 获取Sql查询语句
				String logSql = "select * from register where  registerMobilePhone ='"
						+ mobilephone + "'";

				// 获取DB对象
				DBManager dbManager = DBManager.createInstance();
		        dbManager.connectDB(); // 连接数据库

				// 操作DB对象
				try {
					ResultSet rs = dbManager.executeQuery(logSql);
					if (rs.next()) {
						dbManager.closeDB();
						return true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				dbManager.closeDB();
				return false;
	}
}
