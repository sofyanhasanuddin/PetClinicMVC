package org.sofyan.latihan.app.service;

import java.io.Serializable;

import org.sofyan.latihan.app.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public interface BaseService <T extends BaseEntity, ID extends Serializable> {
	
	T save(T entity);

	Iterable<T> save(Iterable<T> entities);

	T findOne(ID id);

	boolean exists(ID id);

	Iterable<T> findAll();

	Iterable<T> findAll(Iterable<ID> ids);

	long count();

	void delete(ID id);
	
	void delete(T entity);
	
	void delete(Iterable<? extends T> entities);
	
	void deleteAll();
	
	Page<T> findAll(PageRequest pr);
	
	Page<T> findAll(Specification<T> specification,PageRequest pr);

}
