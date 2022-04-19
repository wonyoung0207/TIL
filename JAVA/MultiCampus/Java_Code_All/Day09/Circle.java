package day09; 
public class Circle extends Shape {

	private int radius;
	
	public Circle() {
	}

	public Circle(int x, int y) {
		super(x, y);
	}
	
	public Circle(int x, int y, int radius) {
		super(x, y);
		this.radius = radius;
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setColor(String color) {
		System.out.println(color);
	}
	
	@Override
	public String toString() {
		return "Circle [radius=" + radius + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public double getArea() {
		return radius * radius * Math.PI;
	}

	@Override
	public double getCircum() {
		return 2 * radius * Math.PI;
	}

}
