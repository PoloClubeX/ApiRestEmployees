package Parameta.com.ApiRestEmployees.Models;

import javax.persistence.*;
import java.time.LocalDate;

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
	private LocalDate birthDate;
	@Column(nullable=false)
	private LocalDate vinculationDate;
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
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public LocalDate getVinculationDate() {
		return vinculationDate;
	}
	public void setVinculationDate(LocalDate vinculationDate) {
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
