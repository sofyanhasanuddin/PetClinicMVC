package org.sofyan.latihan.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="visitdetail")
public class VisitDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="visitId")
	private Visit visit;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="petId")
	private Pet pet;
	
	@OneToMany(mappedBy="visitDetail",cascade={CascadeType.ALL},fetch=FetchType.LAZY,orphanRemoval=true)
	private List<VisitDetailTreatmentType> listTreatmentType;
	
	@Column
	private String note;
	
	@Column
	private Boolean deleted;

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public List<VisitDetailTreatmentType> getListTreatmentType() {
		return listTreatmentType;
	}

	public void setListTreatmentType(
			List<VisitDetailTreatmentType> listTreatmentType) {
		this.listTreatmentType = listTreatmentType;
	}
	
}
