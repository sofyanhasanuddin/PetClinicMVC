package org.sofyan.latihan.app.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee extends NamedEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private BigDecimal salary;
	
	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
