package com.timsmeet.services;

import java.util.List;

import com.timsmeet.dto.Employee;

public interface EmployeeService {

	  List<Employee> readEmpoyees(Long companyId);
	  Employee save(Employee employee);
	  Employee readEmpoloyee(Long employeeId, String[] embeded);
	  void delete(Long employeeId);

}
