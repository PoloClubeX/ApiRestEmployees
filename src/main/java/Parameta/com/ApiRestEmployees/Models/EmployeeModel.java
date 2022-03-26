package Parameta.com.ApiRestEmployees.Models;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.RowId;

@Entity
@Table(name="Employee")
public class EmployeeModel {
	@Column(nullable=false)
	private String firstName;
	@Column(nullable=false)
	private String lastName;
	@Column(nullable=false)
	private String documentType;
	
	@Id
	@Column(unique = true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	
	@Column(unique = true, nullable=false)
	private String documentNumber;
	@Column(nullable=false)
	private Date birthDate;
	@Column(nullable=false)
	private Date vinculationDate;
	@Column(nullable=false)
	private String position;
	private double salary;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getVinculationDate() {
		return vinculationDate;
	}
	public void setVinculationDate(Date vinculationDate) {
		this.vinculationDate = vinculationDate;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
		
}
