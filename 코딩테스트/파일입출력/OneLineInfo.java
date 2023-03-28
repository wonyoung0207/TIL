package 메타빌드코딩테스트;

public class OneLineInfo {
	private String ip; // ip
	private String fileName; // 파일 명
	private int allSum; // 전체 요청 건수 ( 합 )
	private int method_get; // 메소드 get
	private int method_post; // 메소드 post
	private int method_put; // 메소드 put
	private int method_delete; // 메소드 delete
	private double err_rate; // 오류율
	private double c_rate; // 캐시히트율
	
	public OneLineInfo(String fileName) { // 초기화
		this.ip = "";
		this.fileName = fileName;
		this.allSum = 0;
		this.method_get = 0;
		this.method_post = 0;
		this.method_put = 0;
		this.method_delete = 0;
		this.err_rate = 0;
		this.c_rate = 0;
	}
	
	public OneLineInfo(String ip, String fileName, int allSum, int method_get, int method_post, int method_put,
			int method_delete, double err_rate, double c_rate) {
		super();
		this.ip = ip;
		this.fileName = fileName;
		this.allSum = allSum;
		this.method_get = method_get;
		this.method_post = method_post;
		this.method_put = method_put;
		this.method_delete = method_delete;
		this.err_rate = err_rate;
		this.c_rate = c_rate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getAllSum() {
		return allSum;
	}

	public void setAllSum(int allSum) {
		this.allSum = allSum;
	}

	public int getMethod_get() {
		return method_get;
	}

	public void setMethod_get(int method_get) {
		this.method_get = method_get;
	}

	public int getMethod_post() {
		return method_post;
	}

	public void setMethod_post(int method_post) {
		this.method_post = method_post;
	}

	public int getMethod_put() {
		return method_put;
	}

	public void setMethod_put(int method_put) {
		this.method_put = method_put;
	}

	public int getMethod_delete() {
		return method_delete;
	}

	public void setMethod_delete(int method_delete) {
		this.method_delete = method_delete;
	}

	public double getErr_rate() {
		return err_rate;
	}

	public void setErr_rate(double err_rate) {
		this.err_rate = err_rate;
	}

	public double getC_rate() {
		return c_rate;
	}

	public void setC_rate(double c_rate) {
		this.c_rate = c_rate;
	}

	@Override
	public String toString() {
		return ip + ", " + fileName + ", " + allSum  + ", " + method_get  + ", " + method_post  + ", " + method_put  + ", " + method_delete  + ", " + err_rate  + ", " + c_rate;
	}

	

}
