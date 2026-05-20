package practice;

public class Employee {

	private Long id;
	private String empName;
	private String mobileNumber;
	private String email;
	private double salary;
	private String designation;
	private String department;
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", empName=" + empName + ", mobileNumber=" + mobileNumber + ", email=" + email
				+ ", salary=" + salary + ", designation=" + designation + ", department=" + department + "]";
	}
	public Employee(Long id, String empName, String mobileNumber, String email, double salary, String designation,
			String department) {
		super();
		this.id = id;
		this.empName = empName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.salary = salary;
		this.designation = designation;
		this.department = department;
	}
	
	
}
