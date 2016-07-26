package org.sofyan.latihan.app.repository;

import org.sofyan.latihan.app.model.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

public interface VisitRepository extends BaseRepository<Visit, Long> {
	
	@EntityGraph(type=EntityGraphType.LOAD,attributePaths={"owner"})
	Page<Visit> findAll(Specification<Visit> spec, Pageable pageable);
	
}
