package Parameta.com.ApiRestEmployees.Controllers;

import java.util.ArrayList;
import java.time.LocalDate;

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
		// Vinculation's date and info
		LocalDate vinculationDate = employee1.getVinculationDate();
		System.out.println(vinculationDate);
		int vinculationYear = Integer.valueOf(vinculationDate.toString().substring(0, 4));
		System.out.println("Vinculation Year :" + vinculationYear);
		int vinculationMonth = Integer.valueOf(vinculationDate.toString().substring(5, 7));
		System.out.println("Vinculation Month:" + vinculationMonth);
		int vinculationDay = Integer.valueOf(vinculationDate.toString().substring(8, 10));
		System.out.println("Vinculation Day:" + vinculationDay);
		LocalDate actualDate = LocalDate.now();
		// Today's date
		System.out.println(actualDate.toString());
		int thisYear = Integer.valueOf(actualDate.toString().substring(0, 4));
		System.out.println("This year :" + thisYear);
		int thisMonth = Integer.valueOf(actualDate.toString().substring(5, 7));
		System.out.println("This month:" + thisMonth);
		int today = Integer.valueOf(actualDate.toString().substring(8, 10));
		System.out.println("Today:" + today);
		int totalYearsVinculated = 0;
		int totalMonthsVinculated = 0;
		int totalDaysVinculated = 0;

		// first, calculating years
		if (vinculationYear > thisYear || (vinculationYear == thisYear && (vinculationMonth > thisMonth) || vinculationMonth==thisMonth && vinculationDay>today)) {
			return "Given Date is after today's date";
		}
		if (vinculationMonth >= thisMonth && vinculationDay >= today && thisYear > vinculationYear) {
			totalYearsVinculated = thisYear - vinculationYear;
		} /*
			 * else { totalYearsVinculated = thisYear - vinculationYear - 1; }
			 */

		if (thisMonth>=vinculationMonth && today>=vinculationDay) {
			totalMonthsVinculated=thisMonth-vinculationMonth;
		}else if(thisMonth>vinculationMonth) {
			totalMonthsVinculated=thisMonth-vinculationMonth;
		}else {
			totalMonthsVinculated=12-Math.abs(thisMonth-vinculationMonth);
		}
		
		// revisar esto jajaja
		if (today >= vinculationDay) {
			totalDaysVinculated = today - vinculationDay;
		} else {
			totalDaysVinculated = getDaysPassed(thisMonth, today, vinculationDay);
		}
		return "Total time passed: " + totalYearsVinculated + " years, " + totalMonthsVinculated + " months, "
				+ totalDaysVinculated + " days.";
	}

	private int getDaysPassed(int thisMonth, int today, int vinculationDay) {
		int totalDaysPassed = getTotalMonthDays(thisMonth - 1) - vinculationDay + today;
		return totalDaysPassed;
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
