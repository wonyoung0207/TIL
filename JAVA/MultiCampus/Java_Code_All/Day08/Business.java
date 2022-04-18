package day08;

public class Business extends Employee {
	private String location;
	private double goal;
	private double success;
	

	public Business() {

	}
	public Business(String id, String name, double salary, String location, double goal) {
		super(id,name,salary);
		this.location = location;
		this.goal = goal;
		
	}
	
	public void setSuccess(double money) {
		this.success = incen(money);
		
		
	}
	
	public double year_salary() {
		return getSalary() * 12;
	}
	
	public double incen(double incen) {//인센티브 목표 200만
		if(incen > 0 && incen < this.goal) {
			//인센티브 없음 
			System.out.println("목표금 달성에 실패하였습니다. ");
			return 0;
		}else if(incen >= this.goal){
			System.out.println("목표금에 달성했습니다. 인센티브에 80%를 추가 지급 합니다. ");
			return incen * 0.8;//인센티브 80% 더줌 	
		}else {//입력 오류
			return -1;
		}
	}


	@Override
	public String toString() {
		return "Business [location=" + location + ", goal=" + goal + ", success=" + success + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	

}
