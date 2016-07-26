package org.sofyan.latihan.app.repository;

import java.util.List;

import org.sofyan.latihan.app.model.VisitDetail;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VisitDetailRepository extends BaseRepository<VisitDetail, Long> {
	
	@EntityGraph(attributePaths={"listTreatmentType","listTreatmentType.treatmentType"},type=EntityGraphType.FETCH)
	List<VisitDetail> findAllByVisitIdAndDeletedIsFalseOrDeletedIsNull(@Param("visitId")Long visitId);
	
	@Query("select vd from VisitDetail vd left join fetch vd.visit left join fetch vd.pet p left join fetch p.type where vd.visit.id in :visitId and vd.deleted = 0 ")
	List<VisitDetail> findAllByVisitIdIn(@Param("visitId")List<Long> visitId);

}
