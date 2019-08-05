package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FunctionDao {
	//更新worker的职能
		public boolean updateValidByWorkerIdAndFunctionId(String workerId,String functionId) throws SQLException{
			String sql = "select valid from function where workerId="
					+ workerId+" and functionId="+functionId;
			 // 获取DB对象
	        DBManager dbManager = DBManager.createInstance();
	        dbManager.connectDB();
			// 获取结果ResultSet
			ResultSet ret = dbManager.executeQuery(sql);
			if(ret.next()){
				if(ret.getString("valid").equals("1")){
					String sql2="update function set valid=0 where workerId="+workerId+" and functionId="+functionId;
					System.out.println("更改权限，这里的workerID="+workerId+"functionId="+functionId);
					boolean ret2 = dbManager.executeUpdate2(sql2);
					if(ret2){
						dbManager.closeDB();
						return true;
					}else{
						dbManager.closeDB();
						return false;
					}
				}else{
					String sql2="update function set valid=1 where workerId="+workerId+" and functionId="+functionId;
					boolean ret2 = dbManager.executeUpdate2(sql2);
					if(ret2){
						dbManager.closeDB();
						return true;
					}else{
						dbManager.closeDB();
						return false;
					}
				}
			}else{
				dbManager.closeDB();
				return false;
			}
		}
		// 根据workerId查找worker的所有职能
		public List<String> queryFunctionFrMapping(String workerId)
				throws SQLException {
			System.out.println("执行按工号查找职能方法。。。workerId=" + workerId);
			String sql = "select functionId,valid from function where workerId="
					+ workerId;
			 // 获取DB对象
	        DBManager dbManager = DBManager.createInstance();
	        dbManager.connectDB();
			// 获取结果ResultSet
			ResultSet ret = dbManager.executeQuery(sql);
			List<String> list = new ArrayList<String>();
			while (ret.next()) {
				if (ret.getString("valid").equals("1")) {
					list.add(ret.getString("functionId"));
				}

			}
			dbManager.closeDB();
			return list;

		}
		
}
