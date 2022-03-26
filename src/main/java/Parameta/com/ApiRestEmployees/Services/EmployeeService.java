package Parameta.com.ApiRestEmployees.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Parameta.com.ApiRestEmployees.Models.EmployeeModel;
import Parameta.com.ApiRestEmployees.Repositories.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	public ArrayList<EmployeeModel> listEmployees() {
		return (ArrayList<EmployeeModel>) employeeRepository.findAll();
	}

	public EmployeeModel addEmployee(EmployeeModel employee) {
		return employeeRepository.save(employee);
	}

	public EmployeeModel getEmployee(Integer employeeId) {
		return employeeRepository.findByEmployeeId(employeeId);
	}

	public boolean deleteEmployee(int employeeId) {
		try {
			employeeRepository.deleteById(employeeId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return false;
		}
		return true;
	}
}
