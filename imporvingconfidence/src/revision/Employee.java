package revision;

import java.math.BigDecimal;

public class Employee {

	private int id;
	
	private String empName;
	
	private BigDecimal salary;
	
	private String designation;
	
	private String department;
	
	private String mobileNumber;
	
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empName=" + empName + ", salary=" + salary + ", designation=" + designation
				+ ", department=" + department + ", mobileNumber=" + mobileNumber + ", address=" + address + "]";
	}

	public Employee(int id, String empName, BigDecimal salary, String designation, String department,
			String mobileNumber, String address) {
		super();
		this.id = id;
		this.empName = empName;
		this.salary = salary;
		this.designation = designation;
		this.department = department;
		this.mobileNumber = mobileNumber;
		this.address = address;
	}
	
	

}
