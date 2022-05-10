package day16;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public abstract class Lotte {
	protected int lotte_my[];//입력한 번호를 저장하는 배열 
	protected int count = 0;//맞춘 갯수 
	protected String rank = "0";//등수
	protected static int lotte_correct[] = new int[6];//로또 정답을 저장하는 배열 
	protected static Random r = new Random();
	protected static int correct_money = 0;//현재 상금을 저장 
	Scanner input = new Scanner(System.in);
	
	public abstract void insertArray() throws Exception;
	public abstract void compare();
	public abstract void rank();
	
	protected static void randArray() {//로또 정답 발표 -> 공유 가능 
		System.out.println("********** 로또의 정답을 발표합니다. ********** ");
		for(int i=0; i<lotte_correct.length; i++) {
			lotte_correct[i] = r.nextInt(45)+1;
			for(int j=0; j<i; j++) {
				if(lotte_correct[i] == lotte_correct[j]) {
					i--;
				}
			}

		}

		System.out.println(Arrays.toString(lotte_correct) + " 입니다! 당첨되신 모든 분들 축하드립니다!");

		
	}

}
