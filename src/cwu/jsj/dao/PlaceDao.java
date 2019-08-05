package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cwu.jsj.entity.PlaceBean;

public class PlaceDao {
	//跟据meetingId查找场地预订情况
		public List<PlaceBean>getPlaceDetailByMeetingId(String meetingId) throws SQLException{
			List<PlaceBean> placeList=new ArrayList<PlaceBean>();
			PlaceBean place;
			String sql = "select placeName,placeGalleryful,placeNum,placeAddress from place  where placeMeetingId="+meetingId;
			DBManager dbManager = DBManager.createInstance();
	        dbManager.connectDB(); // 连接数据库
			// 获取结果ResultSet
			ResultSet ret = dbManager.executeQuery(sql);
			while(ret.next()){
				place=new PlaceBean();
				place.setPlaceAddress(ret.getString("placeAddress"));//获取场地地址
				place.setPlaceGalleryful(ret.getInt("placeGalleryful"));//获取场地可容纳人数
				place.setPlaceName(ret.getString("placeName"));//获取场地名称（规格）
				place.setPlaceNum(ret.getInt("placeNum"));//获取场地的个数
				placeList.add(place);
			}
			dbManager.closeDB();
			return placeList;
		}
}
