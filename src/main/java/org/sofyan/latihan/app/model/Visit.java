package org.sofyan.latihan.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="visit")
public class Visit extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date entryDate;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date leaveDate;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ownerId")
	private Owner owner;
	
	@Column
	private String note;
	
	@OneToMany(mappedBy="visit",cascade={CascadeType.ALL},fetch=FetchType.LAZY,orphanRemoval=true)
	private List<VisitDetail> listVisitDetail;

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<VisitDetail> getListVisitDetail() {
		return listVisitDetail;
	}

	public void setListVisitDetail(List<VisitDetail> listVisitDetail) {
		this.listVisitDetail = listVisitDetail;
	}
	
}
