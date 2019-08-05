package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cwu.jsj.entity.WorkerBean;

public class WorkerDao {

	// 根据workerId查找worker信息,但没有职能信息
	public WorkerBean queryWorkerFrWorker(String workerId) throws SQLException {
		String sql = "select * from worker where workerId=" + workerId;
		// 获取DB对象
		DBManager dbManager = DBManager.createInstance();
		dbManager.connectDB();
		// 获取结果ResultSet
		ResultSet ret = dbManager.executeQuery(sql);
		WorkerBean workerBean = new WorkerBean();
		if (ret.next()) {
			workerBean.setWorkerId(workerId);
			workerBean.setWorkerName(ret.getString("workerName"));
			workerBean.setWorkerCompany(ret.getString("workerCompany"));
			workerBean.setWorkerMobilePhone(ret.getString("workerMobilePhone"));
		}
		dbManager.closeDB();
		return workerBean;

	}

	public int selectIdByPhone(int phone) throws SQLException {
		String sql = "select workerId from worker where workerMobilePhone="
				+ phone;
		// 获取DB对象
		DBManager dbManager = DBManager.createInstance();
		dbManager.connectDB();
		// 获取结果ResultSet
		ResultSet ret = dbManager.executeQuery(sql);
		if (ret.next())
			return ret.getInt(1);
		return 0;

	}

	public WorkerBean queryWorkerByPhone(String phone) throws SQLException {
		String sql = "select * from worker where workerMobilePhone=" + phone;
		// 获取DB对象
		DBManager dbManager = DBManager.createInstance();
		dbManager.connectDB();
		// 获取结果ResultSet
		ResultSet ret = dbManager.executeQuery(sql);
		WorkerBean workerBean = new WorkerBean();
		if (ret.next()) {
			workerBean.setWorkerId(ret.getInt("workerId") + "");
			workerBean.setWorkerName(ret.getString("workerName"));
			workerBean.setWorkerCompany(ret.getString("workerCompany"));
			workerBean.setWorkerMobilePhone(ret.getString("workerMobilePhone"));
			return workerBean;
		}
		dbManager.closeDB();
		return null;
	}
}
