package day09;

public class CarApp {

	public static void main(String[] args) {

		
		
//		Hybrid h = new Hybrid("k2",100,100,10,null,"E",150,150,10);		
//		System.out.println(h);
//		h.go(1000);
//		System.out.println(h);
//		h.setMode("G");
//		h.go(1000);
//		System.out.println(h);
//		h.addE(10);
//		System.out.println(h);
//		h.addFuel(10);
		
//		System.out.println(h);
//		h.setMode("A");
//		
		Gasoline g = new Gasoline("k3",100,100,10,null,"G");
		System.out.println(g);
		g.go(10);
		System.out.println(g);
		g.stop();
		System.out.println(g);
		g.addFuel(10);
		System.out.println(g);		
	}
}
