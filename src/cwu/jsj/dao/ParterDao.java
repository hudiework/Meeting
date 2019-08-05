package cwu.jsj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cwu.jsj.entity.ParticipantBean;

public class ParterDao {
	DBManager sql;

	public ParterDao() {
		super();
		// TODO Auto-generated constructor stub
		sql = DBManager.createInstance();
		sql.connectDB();
	}
	public List<ParticipantBean>lookAllSignUp() throws SQLException{
		List<ParticipantBean> list=new ArrayList<>();
		String lookSelectsql = "select * from participant";
		ResultSet rs = sql.executeQuery(lookSelectsql);
		if (rs != null) {
			while (rs.next()) {
				ParticipantBean participant = new ParticipantBean(rs.getString("participantMobilePhone"), rs.getInt("participantMeetingId"), rs.getString("participantName"), rs.getString("participantPosition"), rs.getString("participantCompany"), rs.getInt("participantChoseChildMeeting"), rs.getString("participantDemaind"), rs.getInt("participantTag"));
				list.add(participant);
			}
			return list;
		}
		return list;
	}
	

}
