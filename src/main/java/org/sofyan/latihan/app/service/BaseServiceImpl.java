package org.sofyan.latihan.app.service;

import java.io.Serializable;

import org.sofyan.latihan.app.model.BaseEntity;
import org.sofyan.latihan.app.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public class BaseServiceImpl<T extends BaseEntity, ID extends Serializable> implements BaseService<T, ID> {
	
	private BaseRepository<T, ID> baseRepository;
	
	public BaseServiceImpl(BaseRepository<T, ID> baseRepository) {
		this.baseRepository = baseRepository;
	}

	@Override
	public T save(T entity) {
		return this.baseRepository.save(entity);
	}

	@Override
	public Iterable<T> save(Iterable<T> entities) {
		return this.baseRepository.save(entities);
	}

	@Override
	public T findOne(ID id) {
		return this.baseRepository.findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		return this.baseRepository.exists(id);
	}

	@Override
	public Iterable<T> findAll() {
		return this.baseRepository.findAll();
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		//return this.baseRepository.findAll( ids );
		return null;
	}

	@Override
	public long count() {
		return this.baseRepository.count();
	}

	@Override
	public void delete(ID id) {
		this.baseRepository.delete(id);
	}

	@Override
	public void delete(T entity) {
		this.baseRepository.delete(entity);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		this.baseRepository.delete(entities);
	}

	@Override
	public void deleteAll() {
		this.baseRepository.deleteAll();
	}

	@Override
	public Page<T> findAll(PageRequest pr) {
		return this.baseRepository.findAll( pr );
	}

	@Override
	public Page<T> findAll(Specification<T> specification, PageRequest pr) {
		return this.baseRepository.findAll(specification, pr);
	}


}
