package org.sofyan.latihan.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.sofyan.latihan.app.model.Visit;
import org.sofyan.latihan.app.model.VisitDetail;
import org.sofyan.latihan.app.repository.VisitDetailTreatmentTypeRepository;
import org.sofyan.latihan.app.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class VisitServiceImpl extends BaseServiceImpl<Visit, Long> implements VisitService {

	private VisitRepository visitRepository;
	private VisitDetailTreatmentTypeRepository visitDetailTreatmentTypeRepository;
	
	@Autowired
	public VisitServiceImpl(VisitRepository visitRepository,
			VisitDetailTreatmentTypeRepository visitDetailTreatmentTypeRepository) {
		super(visitRepository);
		this.visitRepository = visitRepository;
		this.visitDetailTreatmentTypeRepository = visitDetailTreatmentTypeRepository;
	}

	@Override
	public Page<Visit> readAll(Specification<Visit> spec, Pageable pageable) {
		return this.visitRepository.findAll(spec, pageable);
	}
	
	@Override
	public Visit save(Visit entity) {

		List<Long> lVdIds = entity.getListVisitDetail()
							.stream()
							.filter( v -> v.getId() != null && !v.getId().equals( 0L ) )
							.map( VisitDetail::getId )
							.collect( Collectors.toList() );
		
		if( !CollectionUtils.isEmpty( lVdIds ) ) {
			this.visitDetailTreatmentTypeRepository.deleteByVisitDetailId( lVdIds );
		}
		
		entity.getListVisitDetail()
				.forEach( vd -> { 
					vd.setVisit(entity);
					vd.getListTreatmentType().forEach( tt -> tt.setVisitDetail(vd) );
				});
		
		return super.save(entity);
	}

}
