package org.sofyan.latihan.app.service;

import java.util.List;

import org.sofyan.latihan.app.model.TreatmentType;

public interface TreatmentTypeService extends BaseService<TreatmentType, Long> {

	List<TreatmentType> findAllCacheAble();

}
