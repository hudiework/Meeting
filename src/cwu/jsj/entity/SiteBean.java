package cwu.jsj.entity;

public class SiteBean {
	public String siteName;//场地名字
	public int capacity;//容纳人数
	public String money;//预定价格
	public String Address;//场地地址
	public int siteAvailableNumber;//场地可用数量
	public String siteType;
	//场地类型分为sport,companyMeeting,entertainment,nomalMeeting
	//LargeScaleEventMeeting
	public String sitePhoneNumber;//场馆联系方式
	public String bookTime;//预定时间
	public String endTime;//结束时间
	public int siteBookNumber;//预定场地个数
	public String siteCompany;//场地隶属公司，场地所有人
	public String siteDetail;//场地详细信息
	public float siteEval;//场地评价
	public int siteState;//场地状态
	
	public int getSiteState() {
		return siteState;
	}
	public void setSiteState(int siteState) {
		this.siteState = siteState;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getSiteAvailableNumber() {
		return siteAvailableNumber;
	}
	public void setSiteAvailableNumber(int siteAvailableNumber) {
		this.siteAvailableNumber = siteAvailableNumber;
	}
	public String getSiteType() {
		return siteType;
	}
	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}
	public String getSitePhoneNumber() {
		return sitePhoneNumber;
	}
	public void setSitePhoneNumber(String sitePhoneNumber) {
		this.sitePhoneNumber = sitePhoneNumber;
	}
	public String getBookTime() {
		return bookTime;
	}
	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getSiteBookNumber() {
		return siteBookNumber;
	}
	public void setSiteBookNumber(int siteBookNumber) {
		this.siteBookNumber = siteBookNumber;
	}
	public String getSiteCompany() {
		return siteCompany;
	}
	public void setSiteCompany(String siteCompany) {
		this.siteCompany = siteCompany;
	}
	public String getSiteDetail() {
		return siteDetail;
	}
	public void setSiteDetail(String siteDetail) {
		this.siteDetail = siteDetail;
	}
	public float getSiteEval() {
		return siteEval;
	}
	public void setSiteEval(float siteEval) {
		this.siteEval = siteEval;
	}
	
	public SiteBean(String siteName, int capacity, String money,
			String address, int siteAvailableNumber, String siteType,
			String sitePhoneNumber, String bookTime, String endTime,
			int siteBookNumber, String siteCompany, String siteDetail,
			float siteEval, int siteState) {
		super();
		this.siteName = siteName;
		this.capacity = capacity;
		this.money = money;
		Address = address;
		this.siteAvailableNumber = siteAvailableNumber;
		this.siteType = siteType;
		this.sitePhoneNumber = sitePhoneNumber;
		this.bookTime = bookTime;
		this.endTime = endTime;
		this.siteBookNumber = siteBookNumber;
		this.siteCompany = siteCompany;
		this.siteDetail = siteDetail;
		this.siteEval = siteEval;
		this.siteState = siteState;
	}
	@Override
	public String toString() {
		return "SiteBean [siteName=" + siteName + ", capacity=" + capacity
				+ ", money=" + money + ", Address=" + Address
				+ ", siteAvailableNumber=" + siteAvailableNumber
				+ ", siteType=" + siteType + ", sitePhoneNumber="
				+ sitePhoneNumber + ", bookTime=" + bookTime + ", endTime="
				+ endTime + ", siteBookNumber=" + siteBookNumber
				+ ", siteCompany=" + siteCompany + ", siteDetail=" + siteDetail
				+ ", siteEval=" + siteEval + ", siteState=" + siteState + "]";
	}
	//initConstructer
	public SiteBean(String siteName, int capacity, String money,
			String address, int siteAvailableNumber, String siteType,
			String sitePhoneNumber, String siteCompany) {
		super();
		this.siteName = siteName;
		this.capacity = capacity;
		this.money = money;
		Address = address;
		this.siteAvailableNumber = siteAvailableNumber;
		this.siteType = siteType;
		this.sitePhoneNumber = sitePhoneNumber;
		this.siteCompany = siteCompany;
	}
	
	
	
	

}
