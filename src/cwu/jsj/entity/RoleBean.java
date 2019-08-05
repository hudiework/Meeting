package cwu.jsj.entity;

/**
 * Created by Stephanie_z on 2018/3/25.
 */

public class RoleBean {
    private Integer meetingId,workerId;
    private String  role;


    public RoleBean(Integer meetingId, Integer workerId) {
        this.meetingId = meetingId;
        this.workerId = workerId;
    }

    public RoleBean(Integer meetingId, Integer workerId, String role) {
        this.meetingId = meetingId;
        this.workerId = workerId;
        this.role = role;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
