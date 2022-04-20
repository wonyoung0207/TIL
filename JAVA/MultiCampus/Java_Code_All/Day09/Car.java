package day09;

public abstract class Car {
	protected String name;
	protected double fsize;//연료통 용량
	protected double cfuel;//현재 연료량
	protected double eff;
	protected String status;//상태
	protected String mode;//엔진 모드 
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getFsize() {
		return fsize;
	}
	public void setFsize(double fsize) {
		this.fsize = fsize;
	}
	public double getCfuel() {
		return cfuel;
	}
	public void setCfuel(double cfuel) {
		this.cfuel = cfuel;
	}
	public double getEff() {
		return eff;
	}
	public void setEff(double eff) {
		this.eff = eff;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public abstract void go(double km); 
	public abstract void stop(); 
	public abstract void addFuel(double fuel);

	@Override
	public String toString() {
		return "Car [name=" + name + ", fsize=" + fsize + ", cfuel=" + cfuel + ", eff=" + eff + ", status=" + status
				+ "]";
	}
}
