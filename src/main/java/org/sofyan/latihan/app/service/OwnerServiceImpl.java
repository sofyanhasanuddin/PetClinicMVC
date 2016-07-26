package org.sofyan.latihan.app.service;

import org.apache.commons.collections.CollectionUtils;
import org.sofyan.latihan.app.model.Owner;
import org.sofyan.latihan.app.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Service
@Transactional
public class OwnerServiceImpl extends BaseServiceImpl<Owner, Long> implements OwnerService {

	private OwnerRepository ownerRepository;
	
	@Autowired
	public OwnerServiceImpl(OwnerRepository ownerRepository) {
		super(ownerRepository);
		this.ownerRepository = ownerRepository;
	}
	
	@Override
	public Owner save(Owner entity) {
		
		entity.getListPets().stream().forEach( p -> {
			p.setOwner(entity);
			
			//Workaround In case exception orphan removal
			if( CollectionUtils.isEmpty( p.getListVisitDetail() ) )
				p.setListVisitDetail( Lists.newArrayList() );
		} );
		
		//Workaround In case exception orphan removal
		entity.setListVisits( Lists.newArrayList() );
		
		return super.save(entity);
	}

}
