package day09;

public class Hybrid extends Car {

	private double bettery;
	private double cbettery;
	private double beff;

	public Hybrid() {
	}
	public Hybrid(String name, double fsize, double cfuel, double eff, String status,String mode,double bettery, double cbettery, double beff) {
		this.name=name;
		this.fsize=fsize;
		this.cfuel=cfuel;
		this.eff=eff;
		this.status=status;
		this.mode=mode;
		this.bettery=bettery;
		this.cbettery=cbettery;
		this.beff=beff;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public double getBettery() {
		return bettery;
	}

	public void setBettery(double bettery) {
		this.bettery = bettery;
	}

	public double getcBettery() {
		return cbettery;
	}

	public void setcbettery(double cBettery) {
		this.cbettery = cBettery;
	}

	public double getBeff() {
		return beff;
	}

	public void setBeff(double beff) {
		this.beff = beff;
	}

	@Override
	public void go(double km) {
		if(this.mode == "E") {
			if(this.cbettery ==0) {
				System.out.println("배터리가 부족합니다.");
				return;
			}
			System.out.println(this.name+": +GO");
			this.status = "GO";
			this.cbettery -= (km/this.beff);
		}else if(this.mode == "G") {
			if(this.cfuel ==0) {
				System.out.println("기름이 부족합니다.");
				return;
			}
			System.out.println(this.name+": +GO");
			this.status = "GO";
			this.cfuel -= (km/this.eff);	
		}else {
			System.out.println("모드를 확인해 주세요");
			return;
		}
	}




	public void addFuel(double fuel) {
		this.cfuel += fuel;
	}
	public void addE(double elec) {
		this.cbettery += elec;
	}

	@Override
	public String toString() {
		return "Hybrid [mode=" + mode + ", bettery=" + bettery + ", cbettery=" + cbettery + ", beff=" + beff + ", name="
				+ name + ", fsize=" + fsize + ", cfuel=" + cfuel + ", eff=" + eff + ", status=" + status + "]";
	}

	@Override
	public void stop() {
		System.out.println(this.name+": +STOP");
		this.status = "STOP";
	}

}
