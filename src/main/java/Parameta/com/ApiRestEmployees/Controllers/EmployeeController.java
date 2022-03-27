package Parameta.com.ApiRestEmployees.Controllers;

import java.util.ArrayList;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public Object addEmployee(@RequestBody EmployeeModel employee) {
		if (calculatePassedYears(employee.getBirthDate()) < 18) {
			return "{'Error':'EMPLOYEE MUST BE 18 YEARS OLD AT LEAST'}";
		}
		return this.employeeService.addEmployee(employee);
	}

	@GetMapping("/getEmployee/{employeeId}")
	public EmployeeModel getEmployee(@PathVariable("employeeId") Integer employeeId) {
		return this.employeeService.getEmployee(employeeId);
	}

	@DeleteMapping("/deleteEmployee/{employeeId}")
	public String deleteEmployee(@PathVariable("employeeId") int employeeId) {
		boolean res = this.employeeService.deleteEmployee(employeeId);
		if (res) {
			return "Employee with id " + employeeId + " deleted";
		} else {
			return "Employee with id " + employeeId + " NOT deleted";
		}
	}

	@GetMapping("/getEmployeeInfo/{employeeId}")
	public String getEmployeeInfo(@PathVariable("employeeId") Integer employeeId) {
		EmployeeModel employee1 = this.employeeService.getEmployee(employeeId);
		if (employee1 == null) {
			return "There is not an employee with id " + employeeId;
		}
		return "Total time passed is " + calculateTimePassed(employee1.getVinculationDate()) + " And employees age is "
				+ calculateTimePassed(employee1.getBirthDate());
	}

	private int getDaysPassed(int thisMonth, int today, int vinculationDay) {
		int totalDaysPassed = getTotalMonthDays(thisMonth - 1) - vinculationDay + today;
		return totalDaysPassed;
	}

	private String calculateTimePassed(LocalDate dateToValidate) {
		// Separating data from years, months and days to use and compare it
		int yearToValidate = Integer.valueOf(dateToValidate.toString().substring(0, 4));
		int monthToValidate = Integer.valueOf(dateToValidate.toString().substring(5, 7));
		int dayToValidate = Integer.valueOf(dateToValidate.toString().substring(8, 10));
		// Getting today's date to get a reference about the time range to calculate
		LocalDate actualDate = LocalDate.now();

		// Separating data from years, months and days to use and compare it
		int thisYear = Integer.valueOf(actualDate.toString().substring(0, 4));
		int thisMonth = Integer.valueOf(actualDate.toString().substring(5, 7));
		int today = Integer.valueOf(actualDate.toString().substring(8, 10));
		int totalYearsPassed = 0;
		int totalMonthsPassed = 0;
		int totalDaysPassed = 0;

		// first, calculating years passed since year to validate
		if (yearToValidate > thisYear || (yearToValidate == thisYear && (monthToValidate > thisMonth)
				|| monthToValidate == thisMonth && dayToValidate > today)) {
			return "Given Date is after today's date";
		}
		totalYearsPassed = calculatePassedYears(dateToValidate);
		totalMonthsPassed = calculatePassedMonths(dateToValidate);
		totalDaysPassed = calculatePassedDays(dateToValidate);

		return totalYearsPassed + " years, " + totalMonthsPassed + " months, " + totalDaysPassed + " days.";
	}

	private int calculatePassedDays(LocalDate dateToValidate) {
		// Separating data from years, months and days to use and compare it
		int dayToValidate = Integer.valueOf(dateToValidate.toString().substring(8, 10));
		// Getting today's date to get a reference about the time range to calculate
		LocalDate actualDate = LocalDate.now();

		// Separating data from years, months and days to use and compare it
		int thisMonth = Integer.valueOf(actualDate.toString().substring(5, 7));
		int today = Integer.valueOf(actualDate.toString().substring(8, 10));
		int totalDaysPassed;
		if (today >= dayToValidate) {
			totalDaysPassed = today - dayToValidate;
		} else {
			totalDaysPassed = getDaysPassed(thisMonth, today, dayToValidate);
		}
		return totalDaysPassed;
	}

	private int calculatePassedMonths(LocalDate dateToValidate) {
		// Separating data from years, months and days to use and compare it
		int monthToValidate = Integer.valueOf(dateToValidate.toString().substring(5, 7));
		int dayToValidate = Integer.valueOf(dateToValidate.toString().substring(8, 10));
		// Getting today's date to get a reference about the time range to calculate
		LocalDate actualDate = LocalDate.now();

		// Separating data from years, months and days to use and compare it
		int thisMonth = Integer.valueOf(actualDate.toString().substring(5, 7));
		int today = Integer.valueOf(actualDate.toString().substring(8, 10));
		int totalMonthsPassed = 0;
		if (thisMonth >= monthToValidate && today >= dayToValidate) {
			totalMonthsPassed = thisMonth - monthToValidate;
		} else if (today >= dayToValidate) {
			totalMonthsPassed = 12 - Math.abs(thisMonth - monthToValidate);
		} else {
			totalMonthsPassed = 12 - Math.abs(thisMonth - monthToValidate) - 1;
		}
		return totalMonthsPassed;
	}

	private int calculatePassedYears(LocalDate dateToValidate) {
		// Separating data from years, months and days to use and compare it
		int yearToValidate = Integer.valueOf(dateToValidate.toString().substring(0, 4));
		int monthToValidate = Integer.valueOf(dateToValidate.toString().substring(5, 7));
		int dayToValidate = Integer.valueOf(dateToValidate.toString().substring(8, 10));
		// Getting today's date to get a reference about the time range to calculate
		LocalDate actualDate = LocalDate.now();

		// Separating data from years, months and days to use and compare it
		int thisYear = Integer.valueOf(actualDate.toString().substring(0, 4));
		int thisMonth = Integer.valueOf(actualDate.toString().substring(5, 7));
		int today = Integer.valueOf(actualDate.toString().substring(8, 10));

		int totalYearsPassed = 0;
		if (thisMonth >= monthToValidate && today >= dayToValidate && thisYear > yearToValidate) {
			totalYearsPassed = thisYear - yearToValidate;
		} else {
			totalYearsPassed = thisYear - yearToValidate - 1;
		}
		return totalYearsPassed;
	}

	private int getTotalMonthDays(int month) {
		int daysPerMonth = 0;
		switch (month) {
		case 1:
			daysPerMonth = 31;
			break;
		case 2:
			daysPerMonth = 28;
			break;
		case 3:
			daysPerMonth = 31;
			break;
		case 4:
			daysPerMonth = 30;
			break;
		case 5:
			daysPerMonth = 31;
			break;
		case 6:
			daysPerMonth = 30;
			break;
		case 7:
			daysPerMonth = 31;
			break;
		case 8:
			daysPerMonth = 31;
			break;
		case 9:
			daysPerMonth = 30;
			break;
		case 10:
			daysPerMonth = 31;
			break;
		case 11:
			daysPerMonth = 30;
			break;
		case 12:
			daysPerMonth = 31;
			break;
		default:
			return daysPerMonth;
		}
		return daysPerMonth;
	}

}
