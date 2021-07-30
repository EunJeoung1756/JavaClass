package Project;

public class Sport {
	
	private String E_name, E_part;
	private int E_cal;
	
	public String getE_name() {
		return E_name;
	}
	public void setE_name(String e_name) {
		E_name = e_name;
	}
	public String getE_part() {
		return E_part;
	}
	public void setE_part(String e_part) {
		E_part = e_part;
	}
	public int getE_cal() {
		return E_cal;
	}
	public void setE_cal(int e_cal) {
		E_cal = e_cal;
	}
	
	
	@Override
	public String toString() {
		return "Sport [E_name=" + E_name + ", E_part=" + E_part + ", E_cal=" + E_cal + "]";
	}
	
	
	public Sport() {
		super();
	}
	public Sport(String e_name, String e_part, int e_cal) {
		super();
		E_name = e_name;
		E_part = e_part;
		E_cal = e_cal;
	}

}
