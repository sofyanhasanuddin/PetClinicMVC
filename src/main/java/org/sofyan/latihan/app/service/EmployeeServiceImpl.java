package org.sofyan.latihan.app.service;

import org.sofyan.latihan.app.model.Employee;
import org.sofyan.latihan.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long> implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super(employeeRepository);
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Page<Employee> findAll(PageRequest pr) {
		return this.employeeRepository.findAll( pr );
	}

}
