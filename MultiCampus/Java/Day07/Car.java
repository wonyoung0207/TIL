package day07;

public class Car {
	//Attribute
	private String name;
	private String status;
	private double fsize;//기름 
	private double cfsize;
	private double fe;//연비 
	
	
	// Constructor 생성자
	public Car() {
		this.name = "Car_default";
		this.status = "default";
		this.fsize = 100;
		this.cfsize = 100;
	}
	
	public Car(String name,double fsize,double fe) {
		this.name = name;
		this.status = "default";
		this.fsize = fsize;
		this.cfsize = 100;
		this.fe = fe;
	}
	
	public Car(String name, String status, double fsize,double cfsize,double fe) {
		this.name = name;
		this.status = status;
		this.fsize = fsize;
		this.cfsize = cfsize;
		this.fe = fe;
		
	}
	

	//Operation
	public void go(double km) {
		if(this.cfsize == 0) {
			System.out.println("기름이 없습니다. ");
			return;
		}
		System.out.println(this.name + km + " GO!");
		this.status = "Go";
		this.cfsize -= (km/this.fe);// 연비만큼 기름을 뺀다. 
	}
	
	public void stop() {
		System.out.println("Stop!");
		this.status = "STOP";
	}
	
	public void addFuels(int f) {
		cfsize += f;
	}
	
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getFsize() {
		return fsize;
	}

	public void setFsize(double fsize) {
		this.fsize = fsize;
	}

	public double getCfsize() {
		return cfsize;
	}

	public void setCfsize(double cfsize) {
		this.cfsize = cfsize;
	}

	public double getFe() {
		return fe;
	}

	public void setFe(double fe) {
		this.fe = fe;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", status=" + status + ", fsize=" + fsize + ", cfsize=" + cfsize
				+ "]";
	}
	
}


