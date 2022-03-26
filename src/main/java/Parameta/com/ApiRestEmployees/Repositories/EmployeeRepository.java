package Parameta.com.ApiRestEmployees.Repositories;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Parameta.com.ApiRestEmployees.Models.EmployeeModel;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeModel, Integer>{
	
	public abstract EmployeeModel findByEmployeeId(Integer employeeId);
}
