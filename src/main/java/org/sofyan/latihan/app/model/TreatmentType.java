package org.sofyan.latihan.app.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="treatmenttype")
public class TreatmentType extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="treatmentType",fetch=FetchType.LAZY)
	private List<VisitDetailTreatmentType> listVDTreatmentType;

	public List<VisitDetailTreatmentType> getListVDTreatmentType() {
		return listVDTreatmentType;
	}

	public void setListVDTreatmentType(
			List<VisitDetailTreatmentType> listVDTreatmentType) {
		this.listVDTreatmentType = listVDTreatmentType;
	}
	
}
