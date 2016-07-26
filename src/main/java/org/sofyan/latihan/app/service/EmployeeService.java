package org.sofyan.latihan.app.service;

import org.sofyan.latihan.app.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface EmployeeService extends BaseService<Employee, Long> {
	
	Page<Employee> findAll(PageRequest pr);
	
}
