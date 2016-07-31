package org.sofyan.latihan.app.service;

import java.util.List;

import org.sofyan.latihan.app.model.PetType;

public interface PetTypeService extends BaseService<PetType, Long> {

	List<PetType> findAllCacheAble();

}
