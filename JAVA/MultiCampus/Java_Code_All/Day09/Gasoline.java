package day09;

public class Gasoline extends Car {
	public Gasoline() {
	}
	public Gasoline(String name, double fsize, double cfuel, double eff, String status,String mode) {
		this.name=name;
		this.fsize=fsize;
		this.cfuel=cfuel;
		this.eff=eff;
		this.status=status;
		this.mode=mode;
	}
	
	public void addFuel(double fuel) {
		this.cfuel += fuel;
	}
	
	@Override
	public void stop() {
		System.out.println(this.name+": +STOP");
		this.status = "STOP";
	}
	@Override
	public void go(double km) {
		if(this.mode == "G") {
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
}

