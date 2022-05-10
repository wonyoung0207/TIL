package day05;

// 두개의 주사위의 숫자 합이 8일때만 출력한다. 
public class Dice {
	
	public static void main(String[] args) {
		for(int i=1; i<= 6; i++) {
			for(int j=1; j <= 6; j++) {
				if((i + j) == 8) {
					System.out.printf("%d %d \t",i,j);
				}
			}
		}
		System.out.println("");

	}

}
