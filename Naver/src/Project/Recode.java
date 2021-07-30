package Project;

public class Recode {

	private String R_id, Date, R_name, R_part;
	private int R_set, R_cal;
	
	public String getR_id() {
		return R_id;
	}
	public void setR_id(String r_id) {
		R_id = r_id;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getR_set() {
		return R_set;
	}
	public void setR_set(int r_set) {
		R_set = r_set;
	}
	public int getR_cal() {
		return R_cal;
	}
	public void setR_cal(int r_cal) {
		R_cal = r_cal;
	}
	public String getR_name() {
		return R_name;
	}
	public void setR_name(String r_name) {
		R_name = r_name;
	}
	public String getR_part() {
		return R_part;
	}
	public void setR_part(String r_part) {
		R_part = r_part;
	}
	
	
	@Override
	public String toString() {
		return "Recode [R_id=" + R_id + ", Date=" + Date + ", R_name=" + R_name + ", R_part=" + R_part + ", R_set="
				+ R_set + ", R_cal=" + R_cal + "]";
	}
	
	
	
	public Recode() {
		super();
	}
	
	public Recode(String r_id, String date, String r_name, String r_part, int r_set, int r_cal) {
		super();
		R_id = r_id;
		Date = date;
		R_name = r_name;
		R_part = r_part;
		R_set = r_set;
		R_cal = r_cal;
	}
}
	
	
	


