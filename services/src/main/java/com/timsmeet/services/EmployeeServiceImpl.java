package com.timsmeet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.timsmeet.dto.Employee;
import com.timsmeet.persistance.model.EmployeeEntity;
import com.timsmeet.persistance.repositories.EmployeeRepository;
import com.timsmeet.services.mapper.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public Employee save(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee readEmpoloyee(Long employeeId, String[] embeded) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long employeeId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Employee> readEmpoyees(Long companyId) {
		List<EmployeeEntity> dbEmployees = Lists.newArrayList(employeeRepository.findByCompanyId(companyId));
	    List<Employee> empoloyees = Lists.newArrayListWithCapacity(dbEmployees.size());
	    for(EmployeeEntity dbEmployee : dbEmployees) {
	    	Employee company = new Employee();
	    	employeeMapper.inverseMap(dbEmployee, company);
	    	empoloyees.add(company);
	    }
	    return empoloyees;
	}

}
