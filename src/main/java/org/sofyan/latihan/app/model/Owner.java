package org.sofyan.latihan.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="owner")
public class Owner extends NamedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;
    
    @OneToMany(mappedBy="owner",cascade={CascadeType.ALL},fetch=FetchType.LAZY,orphanRemoval=true)
    private List<Pet> listPets;
    
    @OneToMany(mappedBy="owner",cascade={CascadeType.REMOVE},fetch=FetchType.LAZY,orphanRemoval=true)
    private List<Visit> listVisits;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Pet> getListPets() {
		return listPets;
	}

	public void setListPets(List<Pet> listPets) {
		this.listPets = listPets;
	}

	public List<Visit> getListVisits() {
		return listVisits;
	}

	public void setListVisits(List<Visit> listVisits) {
		this.listVisits = listVisits;
	}
	
}
