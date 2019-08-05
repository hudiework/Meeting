package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cwu.jsj.entity.HostelBean;

public class HostelDao {
	DBManager sql;

	public HostelDao() {
		super();
		sql = DBManager.createInstance();
		sql.connectDB();
		// TODO Auto-generated constructor stub
	}

	// 创建表
	public void isHotelExist() {
		String isExist = "select count(*) from information_schema.TABLES where TABLE_NAME='hotel'";
		String isExisthostelName = "select count(*) from information_schema.TABLES where TABLE_NAME='hostelName'";
		boolean returnValue ,returnValue2 = false;
		try {
			returnValue = sql.executeUpdate2(isExist);
			returnValue2 =sql.executeUpdate2(isExisthostelName);
			if (returnValue) {
				System.out.println("查询表对象hotel存在。。。。/n");
				if(returnValue2){
					System.out.println("hostelName不存在。。。。/n");
					String createHostelName="create table hostelName(hotelId int primary"
							+ " key auto_increment not null,"
							+ "hotelName varchar(150) not null"
							+ ")default charset=utf8;";
					if(sql.executeUpdate2(createHostelName)){
						System.out.println("创建hostelName成功。。。。/n");
					}
					
				}

			} else {
				System.out.println("查询表对象hotel不存在。。。。将自动为您创建/n");
				String createTableSql = "create table hotel(hotelId int primary"
						+ " key auto_increment not null,"
						+ "hotelName varchar(150) not null,"
						+ "roomPeopleNumber int,"
						+ "hotelBookRoomNumber int,"
						+ "roomAvailableNumber int,"
						+ "hotelBookTime varchar(150),"
						+ "hotelEndTime varchar(1500),"
						+ "hotelAddress varchar(1500),"
						+ "hotelPhoneNumber varchar(1500),"
						+ "roomType varchar(1500),"
						+ "hotelCompany varchar(1500),"
						+ "hotelDetail varchar(5000),"
						+ "money varchar(1500),"
						+ "hotelEval float," + "hotelState int)default charset=utf8";

				
				try {
					returnValue = sql.executeUpdate2(createTableSql);
					if (returnValue) {
						System.out.println("创建hotel成功。。。。/n");
					}

				} catch (Exception e) {
					System.out.println("创建表语句有问题。。。执行不成功创建hotel失败");
				}
			}

		} catch (Exception e) {
			System.out.println("数据库又崩了。。。/n");
			e.printStackTrace();

		}

	}

	// 增删改查

	// 增加hotel
	public Boolean createHotel(HostelBean hotel) {
		String createSql = "insert into hotel(hotelName,roomPeopleNumber,hotelBookRoomNumber,roomAvailableNumber,hotelBookTime,hotelEndTime,money,hotelAddress ,hotelPhoneNumber,roomType,hotelCompany,hotelDetail ,hotelEval, hotelState) values('"
				+ hotel.getHotelName()
				+ "',"
				+ hotel.getRoomPeopleNumber()
				+ ","
				+ hotel.getHotelBookRoomNumber()
				+ ","
				+ hotel.getRoomAvailableNumber()
				+ ",'"
				+ hotel.getHotelBookTime()
				+ "','"
				+ hotel.getHotelEndTime()
				+ "','"
				+ hotel.getMoney()
				+ "','"
				+ hotel.getHotelAddress()
				+ "','"
				+ hotel.getHotelPhoneNumber()
				+ "','"
				+ hotel.getRoomType()
				+ "','"
				+ hotel.getHotelCompany()
				+ "','"
				+ hotel.getHotelDetail()
				+ "','"
				+ hotel.getHotelEval()
				+ "','"
				+ hotel.getHotelState()
				+ "') ";
		String createhotelNameTableSql = "insert hostelName(hotelName) values('"+hotel.getHotelName()+"')";
		System.out.println(createSql);
		boolean returnValue = false;
		try {
			String createHostelNameSql = "select * from hostelName";
			ResultSet rs = sql.executeQuery(createHostelNameSql);
			int equal = 0;
			while(rs.next()){
				String hotelName = rs.getString("hotelName");
				if(hotelName.equals(hotel.getHotelName())){
					equal = equal+1;
					continue;
				}
			}
			if(equal==0){
				if(sql.executeUpdate2(createhotelNameTableSql)){
					System.out.println("新插入对象到HostelName表中");
					
				}
			}
			return sql.executeUpdate2(createSql);
		} catch (Exception e) {
			System.out.println("数据库又崩了。。。/n");
			e.printStackTrace();
			return returnValue;

		}
	}

	// 修改hotel
	// 变更数量
	public Boolean addHotelAvailableNumberByCompanyName(int changeNumber,
			HostelBean hotel) {
		int AfterChangeNumber = hotel.getRoomAvailableNumber() + changeNumber;

		String updateHotelSql = "update hotel set roomAvailableNumber ="
				+ AfterChangeNumber + "  where hotelName='"
				+ hotel.getHotelName() + "' and hotelType='"
				+ hotel.getRoomType() + "'";

		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(updateHotelSql);
			sql.closeDB();
			System.out.println("增加" + hotel.getHotelName() + "酒店房间"
					+ changeNumber + "间成功。。。。/n");
			return returnValue;
		} catch (Exception e) {
			System.out.println("增加" + hotel.getHotelName() + "又崩了。。。/n");
			e.printStackTrace();
			sql.closeDB();
			return returnValue;

		}
	}

	public Boolean subHotelAvailableNumberByCompanyName(int changeNumber,
			HostelBean hotel) {
		int AfterChangeNumber = hotel.getRoomAvailableNumber() - changeNumber;

		String updateHotelSql = "update hotel set roomAvailableNumber ="
				+ AfterChangeNumber + "  where hotelName='"
				+ hotel.getHotelName() + "' and hotelType='"
				+ hotel.getRoomType() + "'";

		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(updateHotelSql);
			sql.closeDB();
			System.out.println("减少" + hotel.getHotelName() + "酒店房间"
					+ changeNumber + "间成功。。。。/n");
			return returnValue;
		} catch (Exception e) {
			System.out.println("减少" + hotel.getHotelName() + "又崩了。。。/n");
			e.printStackTrace();
			sql.closeDB();
			return returnValue;

		}
	}

	// 删除酒店信息
	public Boolean deleteHotelByName(String hotelName,String roomType) {
		String deleteByHotelName = "delete from hotel where hotelName="
				+ hotelName + " and roomType="
				+ roomType ;

		boolean returnValue = false;
		try {
			System.out.println(deleteByHotelName);
			returnValue = sql.executeUpdate2(deleteByHotelName);
			if(returnValue){
				sql.closeDB();
				System.out.println("删除" + hotelName + "酒店"+roomType+"成功。。。。/n");
				return returnValue;
				
			}
			sql.closeDB();
			return returnValue;
			
		} catch (Exception e) {
			System.out.println("删除" + hotelName + "数据库又崩了。。。/n");
			e.printStackTrace();
			sql.closeDB();
			return returnValue;

		}

	}

	/* 所有的查询方法 */
	/* 列出所有的实例酒店对象 */
	public List<HostelBean> getAllHotel() throws SQLException {
		String selectAllHotel = "select * from hotel";
		List<HostelBean> listHotel = new ArrayList<>();
		HostelBean hostelBean = null;

		ResultSet rs = sql.executeQuery(selectAllHotel);
		if (rs != null) {
			while (rs.next()) {
				hostelBean = new HostelBean(rs.getString("hotelName"),
						rs.getInt("roomPeopleNumber"),
						rs.getInt("roomAvailableNumber"),
						rs.getString("money"), rs.getString("hotelAddress"),
						rs.getString("hotelPhoneNumber"),
						rs.getString("roomType"), rs.getString("hotelCompany"));
				listHotel.add(hostelBean);
				System.out.println(hostelBean.getHotelName());

			}
			return listHotel;
		}
		return listHotel;

	}

	/* 根据hotelName&roomType筛选出所有的酒店 */
	public List<HostelBean> getHotelByHotelName(String hotelName,
			String roomType) throws SQLException {
		String selectAllHotel = "select * from hotel where hotelName='"
				+ hotelName + "' and roomType='" + roomType+"'";
		System.out.println("selectAllHotel"+selectAllHotel);
		List<HostelBean> listHotel = new ArrayList<>();
		  

		ResultSet rs = sql.executeQuery(selectAllHotel);
		if (rs != null) {
			while (rs.next()) {
				HostelBean	hostelBean = new HostelBean(rs.getString("hotelName"),
						rs.getInt("roomPeopleNumber"),
						rs.getInt("roomAvailableNumber"),
						rs.getString("money"), rs.getString("hotelAddress"),
						rs.getString("hotelPhoneNumber"),
						rs.getString("roomType"), rs.getString("hotelCompany"));
				System.out.println(hostelBean.toString());
				listHotel.add(hostelBean);
				System.out.println(hostelBean);
				System.out.println(hostelBean.getHotelName());

			}
			return listHotel;
		}
		return listHotel;

	}
	public List<HostelBean> exeSql(String sentence){
		List<HostelBean> list = new ArrayList<>();
		ResultSet rs =sql.executeQuery(sentence);
		try {
			while(rs.next()){
				HostelBean bean = new HostelBean(rs.getString("hotelName"), rs.getInt("roomPeopleNumber"), rs.getInt("hotelBookRoomNumber"), rs.getInt("roomAvailableNumber"), rs.getString("hotelBookTime"), rs.getString("hotelEndTime"), rs.getString("money"), rs.getString("hotelAddress"), rs.getString("hotelPhoneNumber"), rs.getString("roomType"), rs.getString("hotelCompany"), rs.getString("hotelDetail"), rs.getFloat("hotelEval"), rs.getInt("hotelState"));
				list.add(bean);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

}
