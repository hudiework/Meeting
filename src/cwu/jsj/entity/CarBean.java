package cwu.jsj.entity;

public class CarBean {
	public int carBookNumber;//车的预定数量
	public int carPeopleNumber;//可乘坐人数
	public String destination;//目的地
	public int carAvailableNumber;//车辆剩余数量
	public String sourceCity;//起始地
	public String money;//预定价钱
	public String Address;
	public String carPhoneNumber;
	public String vehicleType;//车辆类型 分为中大型big，豪华型luxury，suv，跑车Roadster
	public String pickCarTime;//取车时间
	public String returnCarTime;//换车时间
	public String carDetail;//详细的信息，包括车辆介绍，租借条件，何时年检，公里数，所有人
	public String carCompany;//隶属公司
	public float carEval;//车辆评价
	public int carState;
	
	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getCarState() {
		return carState;
	}
	public void setCarState(int carState) {
		this.carState = carState;
	}
	public int getCarBookNumber() {
		return carBookNumber;
	}
	public void setCarBookNumber(int carBookNumber) {
		this.carBookNumber = carBookNumber;
	}
	public int getCarPeopleNumber() {
		return carPeopleNumber;
	}
	public void setCarPeopleNumber(int carPeopleNumber) {
		this.carPeopleNumber = carPeopleNumber;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getCarAvailableNumber() {
		return carAvailableNumber;
	}
	public void setCarAvailableNumber(int carAvailableNumber) {
		this.carAvailableNumber = carAvailableNumber;
	}
	public String getSourceCity() {
		return sourceCity;
	}
	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getPickCarTime() {
		return pickCarTime;
	}
	public void setPickCarTime(String pickCarTime) {
		this.pickCarTime = pickCarTime;
	}
	public String getReturnCarTime() {
		return returnCarTime;
	}
	public void setReturnCarTime(String returnCarTime) {
		this.returnCarTime = returnCarTime;
	}
	public String getCarDetail() {
		return carDetail;
	}
	public void setCarDetail(String carDetail) {
		this.carDetail = carDetail;
	}
	public String getCarCompany() {
		return carCompany;
	}
	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}
	public float getCarEval() {
		return carEval;
	}
	public void setCarEval(float carEval) {
		this.carEval = carEval;
	}
	public String getCarPhoneNumber() {
		return carPhoneNumber;
	}
	public void setCarPhoneNumber(String carPhoneNumber) {
		this.carPhoneNumber = carPhoneNumber;
	}
	public CarBean(int carBookNumber, int carPeopleNumber, String destination,
			int carAvailableNumber, String sourceCity, String money,
			String address, String carPhoneNumber, String vehicleType,
			String pickCarTime, String returnCarTime, String carDetail,
			String carCompany, float carEval, int carState) {
		super();
		this.carBookNumber = carBookNumber;
		this.carPeopleNumber = carPeopleNumber;
		this.destination = destination;
		this.carAvailableNumber = carAvailableNumber;
		this.sourceCity = sourceCity;
		this.money = money;
		Address = address;
		this.carPhoneNumber = carPhoneNumber;
		this.vehicleType = vehicleType;
		this.pickCarTime = pickCarTime;
		this.returnCarTime = returnCarTime;
		this.carDetail = carDetail;
		this.carCompany = carCompany;
		this.carEval = carEval;
		this.carState = carState;
	}
	@Override
	public String toString() {
		return "CarBean [carBookNumber=" + carBookNumber + ", carPeopleNumber="
				+ carPeopleNumber + ", destination=" + destination
				+ ", carAvailableNumber=" + carAvailableNumber
				+ ", sourceCity=" + sourceCity + ", money=" + money
				+ ", Address=" + Address + ", carPhoneNumber=" + carPhoneNumber
				+ ", vehicleType=" + vehicleType + ", pickCarTime="
				+ pickCarTime + ", returnCarTime=" + returnCarTime
				+ ", carDetail=" + carDetail + ", carCompany=" + carCompany
				+ ", carEval=" + carEval + ", carState=" + carState + "]";
	}
	
	
	


	
	
	
	
	
}