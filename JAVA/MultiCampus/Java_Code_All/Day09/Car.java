package day09;

public abstract class Car {
	protected String name;
	protected double fsize;//������ �뷮
	protected double cfuel;//���� ���ᷮ
	protected double eff;
	protected String status;//����
	protected String mode;//���� ��� 
	
	
	
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
