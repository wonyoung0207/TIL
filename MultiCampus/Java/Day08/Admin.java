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
		System.out.println("관리자의 보너스는" + bonus + "입니다. ");
		return getSalary() * 12 + bonus;
	}
	
	public double incen(double incen) {//인센티브 
		return incen * 1.0;
	}
	@Override
	public String toString() {
		return "Admin [bonus=" + bonus + ", toString()=" + super.toString() + "]";
	}
	
	
	

	
	
	

}
