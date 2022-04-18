package day08;

public class Secretary extends Employee {
	private String boss;

	public Secretary() {

	}
	public Secretary(String id, String name, double salary, String boss) {
		super(id,name,salary);
		this.boss = boss;
	}
	
	public double year_salary() {
		System.out.println(boss + "������ ���縸ŭ ���� �޾ƶ�.");
		return getSalary() * 12;
	}
	
	public double incen(double incen) {//�μ�Ƽ�� 
		return incen * 0.6;
	}

	
	@Override
	public String toString() {
		return "Secretary [boss=" + boss + ", toString()=" + super.toString() + "]";
	}
	
	
	
	

}
