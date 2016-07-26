package org.sofyan.latihan.app.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="visitdetailtreatmenttype")
public class VisitDetailTreatmentType extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="visitDetailId")
	private VisitDetail visitDetail;
	
	@ManyToOne
	@JoinColumn(name="treatmentTypeId")
	private TreatmentType treatmentType;
	
	@Transient
	private Boolean deleted;

	public VisitDetailTreatmentType() {
	}
	
	public VisitDetailTreatmentType(TreatmentType tt) {
		this.treatmentType = tt;
	}
	
	public VisitDetail getVisitDetail() {
		return visitDetail;
	}

	public void setVisitDetail(VisitDetail visitDetail) {
		this.visitDetail = visitDetail;
	}

	public TreatmentType getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(TreatmentType treatmentType) {
		this.treatmentType = treatmentType;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}
