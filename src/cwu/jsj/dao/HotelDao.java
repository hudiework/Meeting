package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cwu.jsj.entity.HotelBean;

public class HotelDao {
	//根据meetingId查找酒店预订情况
	public List<HotelBean>getHotelDetailByMeetingId(String meetingId) throws SQLException{
		List<HotelBean> hotelList=new ArrayList<HotelBean>();
		HotelBean hotel;
		String sql = "select hotelGalleryful,hotelRoomNum,hotelRepast from hotel  where hotelMeetingId="+meetingId;
		DBManager dbManager = DBManager.createInstance();
        dbManager.connectDB(); // 连接数据库
		// 获取结果ResultSet
		ResultSet ret = dbManager.executeQuery(sql);
		while(ret.next()){
			hotel=new HotelBean();
			hotel.setHotelGalleryful(ret.getInt("hotelGalleryful"));//获取房间容纳人数
			hotel.setHotelRoomNum(ret.getInt("hotelRoomNum"));
			hotel.setHotelRepast(ret.getInt("hotelRepast"));
			hotelList.add(hotel);
		}
		dbManager.closeDB();
		return hotelList;
	}
}
