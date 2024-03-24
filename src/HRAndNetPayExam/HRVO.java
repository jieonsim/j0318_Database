package HRAndNetPayExam;

public class HRVO {
	private int idx; // 직원별 고유번호
	private String sabun; // 직급 코드(예시. 년(24), 월(03), 일(24), 숫자(00~99))
	private String buseo; // 부서명 (인사과, 총무과, 생산과, 영업과)
	private String name; // 이름
	private String jikkub; // 직급 (기본 : 부장, 과장, 대리, 사원)
	private int age; // 나이 (기본 : 25살)
	private String ipsail; // 입사일
	private String gender; // 성별
	private String address; // 주소

	private int bonbong; // 본봉

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getSabun() {
		return sabun;
	}

	public void setSabun(String sabun) {
		this.sabun = sabun;
	}

	public String getBuseo() {
		return buseo;
	}

	public void setBuseo(String buseo) {
		this.buseo = buseo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJikkub() {
		return jikkub;
	}

	public void setJikkub(String jikkub) {
		this.jikkub = jikkub;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIpsail() {
		return ipsail;
	}

	public void setIpsail(String ipsail) {
		this.ipsail = ipsail;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBonbong() {
		return bonbong;
	}

	public void setBonbong(int bonbong) {
		this.bonbong = bonbong;
	}

	@Override
	public String toString() {
		return "HRVO [idx=" + idx + ", sabun=" + sabun + ", buseo=" + buseo + ", name=" + name + ", jikkub=" + jikkub
				+ ", age=" + age + ", ipsail=" + ipsail + ", gender=" + gender + ", address=" + address + ", bonbong="
				+ bonbong + "]";
	}
}
