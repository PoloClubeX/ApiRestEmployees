package Parameta.com.ApiRestEmployees.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Parameta.com.ApiRestEmployees.Models.EmployeeModel;
import Parameta.com.ApiRestEmployees.Services.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@GetMapping()
	public ArrayList<EmployeeModel> getEmployees() {
		return employeeService.listEmployees();
	}

	@PostMapping()
	public EmployeeModel addEmployee(@RequestBody EmployeeModel employee) {
		return this.employeeService.addEmployee(employee);
	}

	@GetMapping("/getEmployee/{employeeId}")
	public EmployeeModel getEmployee(@PathVariable("employeeId") Integer employeeId) {
		return this.employeeService.getEmployee(employeeId);
	}

	@DeleteMapping("/deleteEmployee/{employeeId}")
	public String deleteEmployee(@PathVariable("employeeId") int employeeId) {
		boolean res=this.employeeService.deleteEmployee(employeeId);
		if (res) {
			return "Employee with id "+employeeId +" deleted";
		}else {
			return "Employee with id "+employeeId +" NOT deleted";
		}
	}
}
