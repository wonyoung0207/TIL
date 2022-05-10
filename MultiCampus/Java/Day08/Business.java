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
	
	public double incen(double incen) {//�μ�Ƽ�� ��ǥ 200��
		if(incen > 0 && incen < this.goal) {
			//�μ�Ƽ�� ���� 
			System.out.println("��ǥ�� �޼��� �����Ͽ����ϴ�. ");
			return 0;
		}else if(incen >= this.goal){
			System.out.println("��ǥ�ݿ� �޼��߽��ϴ�. �μ�Ƽ�꿡 80%�� �߰� ���� �մϴ�. ");
			return incen * 0.8;//�μ�Ƽ�� 80% ���� 	
		}else {//�Է� ����
			return -1;
		}
	}


	@Override
	public String toString() {
		return "Business [location=" + location + ", goal=" + goal + ", success=" + success + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	

}
