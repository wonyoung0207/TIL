package day09;

public class Triangle extends Shape {

	private int width;
	private int height;
	
	public Triangle() {
	}

	public Triangle(int x, int y) {
		super(x, y);
	}
	
	public Triangle(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	
	@Override
	public String toString() {
		return "Triangle [width=" + width + ", height=" + height + ", x=" + x + ", y=" + y + "]";
	}

	@Override
	public double getArea() {
		return (width * height) / 2;
	}

	@Override
	public double getCircum() {
		return width + height + Math.hypot(width, height);
	}

}
