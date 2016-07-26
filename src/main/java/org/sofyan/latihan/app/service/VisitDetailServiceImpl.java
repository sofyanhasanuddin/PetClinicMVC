package org.sofyan.latihan.app.service;

import java.util.List;

import org.sofyan.latihan.app.model.VisitDetail;
import org.sofyan.latihan.app.model.VisitDetailTreatmentType;
import org.sofyan.latihan.app.repository.VisitDetailRepository;
import org.sofyan.latihan.app.repository.VisitDetailTreatmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VisitDetailServiceImpl extends BaseServiceImpl<VisitDetail, Long> implements VisitDetailService {

	private VisitDetailRepository visitDetailRepository;
	private VisitDetailTreatmentTypeRepository visitDetailTreatmentTypeRepository;
	
	@Autowired
	public VisitDetailServiceImpl(VisitDetailRepository visitDetailRepository, 
			VisitDetailTreatmentTypeRepository visitDetailTreatmentTypeRepository) {
		super(visitDetailRepository);
		this.visitDetailRepository = visitDetailRepository;
		this.visitDetailTreatmentTypeRepository = visitDetailTreatmentTypeRepository;
	}
	
	@Override
	public List<VisitDetail> findAllByVisitIdAndDeletedIsFalse(Long visitId) {
		return this.visitDetailRepository.findAllByVisitIdAndDeletedIsFalseOrDeletedIsNull(visitId);
	}
	
	@Override
	public List<VisitDetail> findAllByVisitIdIn(List<Long> visitId) {
		return this.visitDetailRepository.findAllByVisitIdIn(visitId);
	}
	
	@Override
	public List<VisitDetailTreatmentType> findAllVisitDetailTreatmentTypeBasedOnVisitDetailIds(List<Long> ids) {
		return this.visitDetailTreatmentTypeRepository.findAllByVisitDetailIdsIn(ids);
	}

}
