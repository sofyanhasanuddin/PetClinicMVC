package org.sofyan.latihan.app.service;

import org.sofyan.latihan.app.model.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface VisitService extends BaseService<Visit, Long> {
	
	Page<Visit> readAll(Specification<Visit> spec, Pageable pageable);

}
