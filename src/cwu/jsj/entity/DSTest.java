package cwu.jsj.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DSTest {

	private DataSource ds;
	private static DSTest test = new DSTest();

	private DSTest() {
		try {
			InitialContext ic = new InitialContext();
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/userDS");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public static DSTest getInstance() {
		return test;
	}

	public boolean insert(MessageEntity message) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("insert into chat values(null,?,?,?,?)");
			ps.setString(1,message.getDate());
			ps.setString(2, message.getMessage());
			ps.setString(3, message.getName());
			ps.setString(4, message.getMeeting());
			int count = ps.executeUpdate();
			if (count <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
    
	public boolean insert(ContentEntity content) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("insert into message values(null,?,?,?,?)");
			ps.setString(1,content.getContent());
			ps.setString(2,content.getDate());
			ps.setString(3,content.getTag());
			ps.setString(4,content.getMeeting());
			int count = ps.executeUpdate();
			if (count <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean insertSecretary(MessageEntity message) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("insert into onechat values(null,?,?,?,?)");
			ps.setString(1,message.getDate());
			ps.setString(2, message.getMessage());
			ps.setString(3, message.getName());
			ps.setString(4, message.getMeeting());
			int count = ps.executeUpdate();
			if (count <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean insertAll(MessageEntity message) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("insert into allchat values(null,?,?,?,?)");
			ps.setString(1,message.getDate());
			ps.setString(2, message.getMessage());
			ps.setString(3, message.getName());
			ps.setString(4, message.getMeeting());
			int count = ps.executeUpdate();
			if (count <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public List<MessageEntity> allMessage(String meeting) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement sql = null;
		ResultSet rs = null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		List<MessageEntity> list = new ArrayList<MessageEntity>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from chat where meeting='"
					+ meeting + "'");
			sql=con.createStatement();
			rs = ps.executeQuery();
			while (rs.next()) {
				MessageEntity message = new MessageEntity(rs.getString(2),rs.getString(3));
				String name=rs.getString(4);
				rs1=sql.executeQuery("select * from worker where workerName='"+name+"'");
				if(rs1.next()){
					message.setName(name);
					message.setRole(rs1.getString(6));
				}
				rs2=sql.executeQuery("select * from meeting where meetingid="+rs.getString(5));
				if(rs2.next()) {
					message.setMeeting(rs2.getString(2));
				}
				
				list.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	
	public List<MessageEntity> allSecretary(String meeting) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement sql = null;
		ResultSet rs = null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		List<MessageEntity> list = new ArrayList<MessageEntity>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from onechat where meeting='"
					+ meeting + "'");
			sql=con.createStatement();
			rs = ps.executeQuery();
			while (rs.next()) {
				MessageEntity message = new MessageEntity(rs.getString(2),rs.getString(3));
				String name=rs.getString(4);
				rs1=sql.executeQuery("select * from worker where workerName='"+name+"'");
				if(rs1.next()){
					message.setName(name);
					message.setRole(rs1.getString(6));
				}
				rs2=sql.executeQuery("select * from meeting where meetingid="+rs.getString(5));
				if(rs2.next()) {
					message.setMeeting(rs2.getString(2));
				}
				
				list.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	
	public List<MessageEntity> allPeople(String meeting) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement sql = null;
		ResultSet rs = null;
		ResultSet rs2=null;
		List<MessageEntity> list = new ArrayList<MessageEntity>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from allchat where meeting='"
					+ meeting + "'");
			sql=con.createStatement();
			rs = ps.executeQuery();
			while (rs.next()) {
				MessageEntity message = new MessageEntity(rs.getString(2),rs.getString(3));
				message.setName(rs.getString(4));
				rs2=sql.executeQuery("select * from meeting where meetingid="+rs.getString(5));
				if(rs2.next()) {
					message.setMeeting(rs2.getString(2));
				}
				list.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	
	public List<ContentEntity> allContent(String tag) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement sql = null;
		ResultSet rs = null;
		ResultSet rs1=null;
		List<ContentEntity> list = new ArrayList<ContentEntity>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from message where tags in ('"
					+ tag + "','3') order by date desc");
			sql=con.createStatement();
			rs = ps.executeQuery();
			while (rs.next()) {
				ContentEntity content = new ContentEntity(rs.getString(2),
						rs.getString(3), rs.getString(4));
				rs1=sql.executeQuery("select * from meeting where meetingid="+rs.getString(5));
				if(rs1.next()) {
					content.setMeeting(rs1.getString(2));
				}
				list.add(content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	
	// 搜索会议
	public List<MeetingBean> serachNumber(int number) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MeetingBean> list = new ArrayList<MeetingBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from meeting where meetingnumber="+number);
			rs = ps.executeQuery();
			while (rs.next()) {
				MeetingBean meet=new MeetingBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				list.add(meet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}
	
	public List<MeetingBean> serachName(String name) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MeetingBean> list = new ArrayList<MeetingBean>();
		try {
			con = ds.getConnection();
			ps = con.prepareStatement("select * from meeting where meetingName like '%"+name+"%'");
			rs = ps.executeQuery();
			while (rs.next()) {
				MeetingBean meet=new MeetingBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				list.add(meet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;

	}

}
