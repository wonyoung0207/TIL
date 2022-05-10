package day08;

public class Manager extends Employee{
	private double bonus;
	
	public Manager() {
		
		
	}
	
	public Manager(String id, String name, double salary,double bonus) {
		super(id, name, salary);
		this.bonus = bonus;
		
	}


	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	public double year_salary() {//재정의 (오버라이딩)
		System.out.println("manager은 연봉에 보너스가 추가됩니다. ");
//		return bonus + (super.getSalary()*12);
//		return bonus + (getSalary()*12);
		return bonus + super.year_salary();
	}
	
	public double getBonusTex() {//세금 
		double tex = 0.0;
		tex = this.bonus - (this.bonus / 1.1);
		return (double)Math.round(tex * 100 ) / 100;
		
	}

	@Override
	public String toString() {
		return "Manager [bonus=" + bonus + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
