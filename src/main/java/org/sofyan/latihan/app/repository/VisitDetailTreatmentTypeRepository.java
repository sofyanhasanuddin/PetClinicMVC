package org.sofyan.latihan.app.repository;

import java.util.List;

import org.sofyan.latihan.app.model.VisitDetailTreatmentType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VisitDetailTreatmentTypeRepository extends BaseRepository<VisitDetailTreatmentType, Long> {
	
	@Query("select a from VisitDetailTreatmentType a left join fetch a.visitDetail b left join fetch a.treatmentType c where b.id in :visitDetailIds")
	List<VisitDetailTreatmentType> findAllByVisitDetailIdsIn(@Param("visitDetailIds")List<Long> visitId);
	
	@Modifying
	@Query("delete from VisitDetailTreatmentType a where a.visitDetail.id in :visitDetailId")
	void deleteByVisitDetailId(@Param("visitDetailId") List<Long> visitDetailId);

}
