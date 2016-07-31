package org.sofyan.latihan.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sofyan.latihan.app.model.TreatmentType;
import org.sofyan.latihan.app.repository.TreatmentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TreatmentTypeServiceImpl extends BaseServiceImpl<TreatmentType, Long> implements TreatmentTypeService {

	private TreatmentTypeRepository treatmentTypeRepository;
	
	private static Logger log = LoggerFactory.getLogger( TreatmentTypeServiceImpl.class );
	
	@Autowired
	public TreatmentTypeServiceImpl(TreatmentTypeRepository treatmentTypeRepository) {
		super(treatmentTypeRepository);
		this.treatmentTypeRepository = treatmentTypeRepository;
	}
	
	@Override
	@Cacheable(value="treatmentTypeAll")
	public List<TreatmentType> findAllCacheAble() {
		
		log.debug("find all treatment type");
		
		return this.treatmentTypeRepository.findAll();
		
	}

}
