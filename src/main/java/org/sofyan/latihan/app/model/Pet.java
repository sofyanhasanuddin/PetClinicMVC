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
@Table(name="pet")
public class Pet extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column()
	@Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "typeId")
    private PetType type;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ownerId")
    private Owner owner;
    
    @Column
    private boolean deleted;
    
    @OneToMany(mappedBy="pet",cascade={CascadeType.REMOVE},fetch=FetchType.LAZY,orphanRemoval=true)
    private List<VisitDetail> listVisitDetail;

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public PetType getType() {
		return type;
	}

	public void setType(PetType type) {
		this.type = type;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<VisitDetail> getListVisitDetail() {
		return listVisitDetail;
	}

	public void setListVisitDetail(List<VisitDetail> listVisitDetail) {
		this.listVisitDetail = listVisitDetail;
	}
	
}
