package day08;

public class Admin extends Employee {
	private double bonus;
	
	public Admin() {

	}
	public Admin(String id, String name, double salary) {
		super(id,name,salary);
		this.bonus = salary * 0.1;

	}
	
	public double year_salary() {
		System.out.println("�������� ���ʽ���" + bonus + "�Դϴ�. ");
		return getSalary() * 12 + bonus;
	}
	
	public double incen(double incen) {//�μ�Ƽ�� 
		return incen * 1.0;
	}
	@Override
	public String toString() {
		return "Admin [bonus=" + bonus + ", toString()=" + super.toString() + "]";
	}
	
	
	

	
	
	

}
