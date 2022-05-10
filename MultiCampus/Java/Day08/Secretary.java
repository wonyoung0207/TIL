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
		System.out.println(boss + "때문에 힘든만큼 많이 받아라.");
		return getSalary() * 12;
	}
	
	public double incen(double incen) {//인센티브 
		return incen * 0.6;
	}

	
	@Override
	public String toString() {
		return "Secretary [boss=" + boss + ", toString()=" + super.toString() + "]";
	}
	
	
	
	

}
