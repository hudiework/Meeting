package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cwu.jsj.entity.CarBean;
import cwu.jsj.entity.HostelBean;

public class CarTao {
	DBManager sql;

	public CarTao() {
		super();
		// TODO Auto-generated constructor stub
		sql = DBManager.createInstance();
		sql.connectDB();
	}

	// 创建表
	public void isCarExist() {
		String isExist = "select count(*) from information_schema.TABLES where TABLE_NAME='car'";
		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(isExist);
			if (returnValue) {
				System.out.println("查询表对象Car存在。。。。/n");

			} else {
				System.out.println("查询表对象Car不存在。。。。将自动为您创建/n");
				String createTableSql = "create table car(carId int primary"
						+ " key auto_increment not null,"
						+ "carCompany varchar(50) not null,"
						+ "carPeopleNumber int," + "carAvailableNumber int,"
						+ "carBookNumber int," + "destination varchar(50),"
						+ "sourceCity varchar(50)," 
						+ "money varchar(50),"
						+ "address varchar(50),"
						+ "carPhoneNumber varchar(50),"
						
						+ "vehicleType varchar(50),"
						+ "pickCarTime varchar(50),"
						+ "returnCarTime varchar(50),"
						+ "carDetail varchar(500)," + "carEval float,"
						+ "carState int)default charset=utf8;";

				try {
					returnValue = sql.executeUpdate2(createTableSql);
					if (returnValue) {
						System.out.println("创建Car成功。。。。/n");
					}

				} catch (Exception e) {
					System.out.println("创建表语句有问题。。。执行不成功创建Car失败");
				}
			}

		} catch (Exception e) {
			System.out.println("数据库又崩了。。。/n");
			e.printStackTrace();

		}

	}

	public Boolean createCar(CarBean car) {
		String createSql = "insert into car(carCompany,carPeopleNumber,carAvailableNumber,carBookNumber,destination,sourceCity,money,address,carPhoneNumber,vehicleType,pickCarTime,returnCarTime,carDetail,carEval,carState) values('"
				+ car.getCarCompany()
				+ "',"
				+ car.getCarPeopleNumber()
				+ ","
				+ car.getCarAvailableNumber()
				+ ",'"
				+ car.getCarBookNumber()
				+ "','"
				+ car.getDestination()
				+ "','"
				+ car.getSourceCity()
				+ "','"
				+ car.getMoney()
				+ "','"
				+ car.getAddress()
				+ "','"
				+ car.getCarPhoneNumber()
				+ "','"
				+ car.getVehicleType()
				+ "','"
				+ car.getPickCarTime()
				+ "','"
				+ car.getReturnCarTime()
				+ "','"
				+ car.getCarDetail()
				+ "','"
				+ car.getCarEval()
				+ "','"
				+ car.getCarState() + "') ";
		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(createSql);
			System.out.println("车辆插入对象成功。。。。/n");
			return returnValue;
		} catch (Exception e) {
			System.out.println("数据库又崩了。。。/n");
			e.printStackTrace();
			return returnValue;

		}

	}

	// 修改hotel
	// 变更数量
	public Boolean addCarAvailableNumberByCompanyName(int changeNumber,
			CarBean car) {
		int AfterChangeNumber = car.getCarAvailableNumber() + changeNumber;

		String updateHotelSql = "update car set carAvailableNumber ="
				+ AfterChangeNumber + "  where carCompany='"
				+ car.getCarCompany() + "' and vehicleType='"
				+ car.getVehicleType() + "'";

		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(updateHotelSql);
			System.out.println("增加" + car.getCarCompany() + "车辆数量"
					+ changeNumber + "辆成功。。。。/n");
			return returnValue;
		} catch (Exception e) {
			System.out.println("增加" + car.getCarCompany() + "又崩了。。。/n");
			e.printStackTrace();
			return returnValue;

		}
	}

	public Boolean subCarAvailableNumberByCompanyName(int changeNumber,
			CarBean car) {
		int AfterChangeNumber = car.getCarAvailableNumber() - changeNumber;

		String updateHotelSql = "update car set carAvailableNumber ="
				+ AfterChangeNumber + "  where carCompany='"
				+ car.getCarCompany() + "' and vehicleType='"
				+ car.getVehicleType() + "'";

		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(updateHotelSql);
			System.out.println("减少" + car.getCarCompany() + "车辆数量"
					+ changeNumber + "辆成功。。。。/n");
			return returnValue;
		} catch (Exception e) {
			System.out.println("减少" + car.getCarCompany() + "又崩了。。。/n");
			e.printStackTrace();
			return returnValue;

		}
	}

	// 删除与第三方合作的车辆信息
	public Boolean deleteCarByCarCompany(HostelBean hotel) {
		String deleteByCarCompany = "delete from hotel where hotelName='"
				+ hotel.getHotelName() + "' and hotelType='"
				+ hotel.getRoomType() + "'";

		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(deleteByCarCompany);
			System.out.println("删除" + hotel.getHotelName() + "酒店成功。。。。/n");
			return returnValue;
		} catch (Exception e) {
			System.out.println("删除" + hotel.getHotelName() + "数据库又崩了。。。/n");
			e.printStackTrace();
			return returnValue;

		}

	}

	/* 所有的查询方法 */
	/* 列出所有的实例酒店对象 */
	public List<CarBean> getAllCar() throws SQLException {
		String selectAllHotel = "select * from car";
		List<CarBean> listCar = new ArrayList<>();
		CarBean car = null;

		ResultSet rs = sql.executeQuery(selectAllHotel);
		if (rs != null) {
			while (rs.next()) {
				car = new CarBean(rs.getInt("carBookNumber"),
						rs.getInt("carPeopleNumber"),
						rs.getString("destination"),
						rs.getInt("carAvailableNumber"),
						rs.getString("sourceCity"), rs.getString("money"),
						rs.getString("address"),
						rs.getString("carPhoneNumber"),
						rs.getString("vehicleType"),
						rs.getString("pickCarTime"),
						rs.getString("returnCarTime"),
						rs.getString("carDetail"), rs.getString("carCompany"),
						rs.getFloat("carEval"), rs.getInt("carState"));

				listCar.add(car);
				System.out.println(car.getCarCompany());

			}
			return listCar;
		}
		return listCar;

	}

	/* 根据hotelName&roomType筛选出所有的酒店 */
	public List<CarBean> getcarByCarCompany(String carCompany)
			throws SQLException {
		String selectAllHotel = "select * from car where carCompany='"
				+ carCompany + "'";
		List<CarBean> listCar = new ArrayList<>();
		CarBean car = null;

		ResultSet rs = sql.executeQuery(selectAllHotel);
		if (rs != null) {
			while (rs.next()) {
				car = new CarBean(rs.getInt("carBookNumber"),
						rs.getInt("carPeopleNumber"),
						rs.getString("destination"),
						rs.getInt("carAvailableNumber"),
						rs.getString("sourceCity"), rs.getString("money"),
						rs.getString("address"),
						rs.getString("carPhoneNumber"),
						rs.getString("vehicleType"),
						rs.getString("pickCarTime"),
						rs.getString("returnCarTime"),
						rs.getString("carDetail"), rs.getString("carCompany"),
						rs.getFloat("carEval"), rs.getInt("carState"));
				listCar.add(car);
				System.out.println(car.getCarCompany());

			}
			return listCar;
		}
		return listCar;

	}

}
