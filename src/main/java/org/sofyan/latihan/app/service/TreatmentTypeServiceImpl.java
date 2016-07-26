package org.sofyan.latihan.app.service;

import org.sofyan.latihan.app.model.TreatmentType;
import org.sofyan.latihan.app.repository.TreatmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TreatmentTypeServiceImpl extends BaseServiceImpl<TreatmentType, Long> implements TreatmentTypeService {

	private TreatmentTypeRepository treatmentTypeRepository;
	
	@Autowired
	public TreatmentTypeServiceImpl(TreatmentTypeRepository treatmentTypeRepository) {
		super(treatmentTypeRepository);
		this.treatmentTypeRepository = treatmentTypeRepository;
	}

}
