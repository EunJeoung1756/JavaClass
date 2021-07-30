package Project;

public class EMember {

	private String Id, Pw, Name;
	private int Age, Hight, Weight;
	

	
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPw() {
		return Pw;
	}
	public void setPw(String pw) {
		Pw = pw;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public int getHight() {
		return Hight;
	}
	public void setHight(int hight) {
		Hight = hight;
	}
	public int getWeight() {
		return Weight;
	}
	public void setWeight(int weight) {
		Weight = weight;
	}
	
	
	@Override
	public String toString() {
		return "EMember [Id=" + Id + ", Pw=" + Pw + ", Name=" + Name + ", Age=" + Age + ", Hight=" + Hight + ", Weight="
				+ Weight + "]";
	}
	
	
	public EMember() {
		super();
	}
	public EMember(String id, String pw, String name, int age, int hight, int weight) {
		super();
		Id = id;
		Pw = pw;
		Name = name;
		Age = age;
		Hight = hight;
		Weight = weight;
	}

	
	
	
	
	
}
