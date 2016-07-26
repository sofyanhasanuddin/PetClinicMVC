package org.sofyan.latihan.app.service;

import java.util.List;

import org.sofyan.latihan.app.model.VisitDetail;
import org.sofyan.latihan.app.model.VisitDetailTreatmentType;

public interface VisitDetailService extends BaseService<VisitDetail, Long> {

	List<VisitDetail> findAllByVisitIdAndDeletedIsFalse(Long visitId);

	List<VisitDetail> findAllByVisitIdIn(List<Long> visitId);

	List<VisitDetailTreatmentType> findAllVisitDetailTreatmentTypeBasedOnVisitDetailIds(
			List<Long> ids);

}
