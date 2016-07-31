package org.sofyan.latihan.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sofyan.latihan.app.model.PetType;
import org.sofyan.latihan.app.repository.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PetTypeServiceImpl extends BaseServiceImpl<PetType, Long> implements PetTypeService {

	private PetTypeRepository petTypeRepository;
	
	private static Logger log = LoggerFactory.getLogger( PetTypeServiceImpl.class );
	
	@Autowired
	public PetTypeServiceImpl(PetTypeRepository petTypeRepository) {
		super(petTypeRepository);
		this.petTypeRepository = petTypeRepository;
	}
	
	@Override
	@Cacheable(value="petTypeAll")
	public List<PetType> findAllCacheAble() {
		
		log.debug("find all pet Type");
		
		return this.petTypeRepository.findAll();
	}

}
