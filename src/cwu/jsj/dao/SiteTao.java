package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cwu.jsj.entity.SiteBean;

public class SiteTao {
	DBManager sql;

	public SiteTao() {
		super();
		// TODO Auto-generated constructor stub
		sql = DBManager.createInstance();
		sql.connectDB();
	}

	// 创建表
	public void isSiteExist() {
		String isExist = "select count(*) from information_schema.TABLES where TABLE_NAME='site'";
		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(isExist);
			if (returnValue) {
				System.out.println("查询表对象site存在。。。。/n");

			} else {
				System.out.println("查询表对象site不存在。。。。将自动为您创建/n");
				String createTableSql = "create table site(siteId int primary"
						+ " key auto_increment not null,"
						+ "siteName varchar(50) not null,"
						+ "capacity int," + "siteAvailableNumber int,"
						+ "Address varchar(50)," + "bookTime varchar(50),"
						+ "endTime varchar(50)," + "money varchar(50),"
						+ "siteType varchar(50),"
						+ "sitePhoneNumber varchar(50),"
						+ "siteBookNumber int,"
						+ "siteCompany varchar(50),"
						+ "siteDetail varchar(500)," + "siteEval float,"
						+ "siteState int)default charset=utf8;";

				try {
					returnValue = sql.executeUpdate2(createTableSql);
					if (returnValue) {
						System.out.println("创建Site成功。。。。/n");
					}

				} catch (Exception e) {
					System.out.println("创建表语句有问题。。。执行不成功创建Site失败");
				}
			}

		} catch (Exception e) {
			System.out.println("数据库又崩了。。。/n");
			e.printStackTrace();

		}

	}

	public  Boolean createSite(SiteBean site) {
		String createSql = "insert into site(siteName,capacity,siteAvailableNumber,Address,bookTime,endTime,money,siteType,sitePhoneNumber,siteBookNumber,siteCompany,siteDetail,siteEval,siteState) values('"
				+ site.getSiteName()
				+ "',"
				+ site.getCapacity()
				+ ","
				+ site.getSiteAvailableNumber()
				+ ",'"
				+ site.getAddress()
				+ "','"
				+ site.getBookTime()
				+ "','"
				+ site.getEndTime()
				+ "','"
				+ site.getMoney()
				+ "','"
				+ site.getSiteType()
				+ "','"
				+ site.getSitePhoneNumber()
				+ "','"
				+ site.getSiteBookNumber()
				+ "','"
				+ site.getSiteCompany()
				+ "','"
				+ site.getSiteDetail()
				+ "','"
				+ site.getSiteEval()
				+ "','"
				+ site.getSiteState() + "') ";
		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(createSql);
			System.out.println("场地插入对象成功。。。。/n");
			return returnValue;
		} catch (Exception e) {
			System.out.println("数据库又崩了。。。/n");
			e.printStackTrace();
			return returnValue;

		}

	}

	// 修改site
	// 变更数量
	public Boolean addSiteAvailableNumberBySiteName(int changeNumber,
			SiteBean site) {
		int AfterChangeNumber = site.getSiteAvailableNumber() + changeNumber;

		String updateSiteSql = "update site set siteAvailableNumber ="
				+ AfterChangeNumber + "  where siteName='"
				+ site.getSiteName() + "' and siteType='"
				+ site.getSiteType() + "'";

		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(updateSiteSql);
			System.out.println("增加" + site.getSiteName() + "场地数量"
					+ changeNumber + "成功。。。。/n");
			return returnValue;
		} catch (Exception e) {
			System.out.println("增加" + site.getSiteName() + "又崩了。。。/n");
			e.printStackTrace();
			return returnValue;

		}
	}

	public Boolean subSiteAvailableNumberBySiteName(int changeNumber,
			SiteBean site) {
		int AfterChangeNumber = site.getSiteAvailableNumber() - changeNumber;

		String updateSiteSql = "update site set siteAvailableNumber ="
				+ AfterChangeNumber + "  where siteName='"
				+ site.getSiteName() + "' and siteType='"
				+ site.getSiteType() + "'";

		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(updateSiteSql);
			System.out.println("减少" + site.getSiteName() + "场地数量"
					+ changeNumber + "成功。。。。/n");
			return returnValue;
		} catch (Exception e) {
			System.out.println("减少" + site.getSiteName() + "又崩了。。。/n");
			e.printStackTrace();
			return returnValue;

		}
	}


	// 删除与第三方合作的场地信息
	public Boolean deleteSiteBySiteName(String siteName,String siteType) {
		String deleteSiteBySiteName = "delete from site where siteName="
				+ siteName + " and siteType="
				+ siteType ;
		boolean returnValue = false;
		try {
			returnValue = sql.executeUpdate2(deleteSiteBySiteName);
			System.out.println("删除" + siteName + "场地成功。。。。/n");
			return returnValue;
		} catch (Exception e) {
			System.out.println("删除" + siteName + "数据库又崩了。。。/n");
			e.printStackTrace();
			return returnValue;

		}

	}

	/* 所有的查询方法 */
	/* 列出所有的实例场地对象 */
	public List<SiteBean> getAllSite() throws SQLException {
		String selectAllSite = "select * from site";
		List<SiteBean> listSite = new ArrayList<>();
		SiteBean site = null;

		ResultSet rs = sql.executeQuery(selectAllSite);
		if (rs != null) {
			while (rs.next()) {
				site = new SiteBean(rs.getString("siteName"), rs.getInt("capacity"), rs.getString("money"), rs.getString("address"), rs.getInt("siteAvailableNumber"), rs.getString("siteType"), rs.getString("sitePhoneNumber"), rs.getString("bookTime"), rs.getString("endTime"), rs.getInt("siteBookNumber"), rs.getString("siteCompany"), rs.getString("siteDetail"), Float.parseFloat(rs.getString("siteEval")), rs.getInt("siteState"));
				listSite.add(site);
				System.out.println(site.getSiteName());

			}
			return listSite;
		}
		return listSite;

	}

	/* 根据hotelName&roomType筛选出所有的酒店 */
	public List<SiteBean> getSiteBySiteName(String siteName)
			throws SQLException {
		String selectAllSite = "select * from site where siteName='"
				+ siteName + "'";
		List<SiteBean> listSite = new ArrayList<>();
		SiteBean site = null;

		ResultSet rs = sql.executeQuery(selectAllSite);
		if (rs != null) {
			while (rs.next()) {
				site = new SiteBean(rs.getString("siteName"), rs.getInt("capacity"), rs.getString("money"), rs.getString("address"), rs.getInt("siteAvailableNumber"), rs.getString("siteType"), rs.getString("sitePhoneNumber"), rs.getString("bookTime"), rs.getString("endTime"), rs.getInt("siteBookNumber"), rs.getString("siteCompany"), rs.getString("siteDetail"), Float.parseFloat(rs.getString("siteEval")), rs.getInt("siteState"));
				listSite.add(site);
				System.out.println(site.getSiteName());

			}
			return listSite;
		}
		return listSite;

	}


}
