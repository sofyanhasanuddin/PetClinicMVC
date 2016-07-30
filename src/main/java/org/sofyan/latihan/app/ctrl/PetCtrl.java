package org.sofyan.latihan.app.ctrl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sofyan.latihan.app.model.Pet;
import org.sofyan.latihan.app.model.PetType;
import org.sofyan.latihan.app.model.TreatmentType;
import org.sofyan.latihan.app.service.PetService;
import org.sofyan.latihan.app.service.PetTypeService;
import org.sofyan.latihan.app.service.TreatmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PetCtrl {
	
	public static Logger log = LoggerFactory.getLogger( PetCtrl.class );
	
	@Autowired
	private PetService petServiceImpl;
	
	@Autowired
	private PetTypeService petTypeServiceImpl;
	
	@Autowired
	private TreatmentTypeService treatmentTypeServiceImpl;
	
	
	@RequestMapping( value 		= "/page/pet/listpetByOwner/{id}",
	 				headers 	=  "Content-type=application/json",
	 				method 		= RequestMethod.GET,
	 				produces 	= MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public List<Pet> getPetsByOwner(@PathVariable("id") Long ownerId) {
		
		if( ownerId == null )
			return null;
		
		return this.petServiceImpl.findAllActivePetByOwnerId( ownerId );
		
	}
	
	@RequestMapping( value 		= "/page/pet/listPetType",
					headers 	=  "Content-type=application/json",
					method 		= RequestMethod.GET,
					produces 	= MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public List<PetType> getPetType() {

		return (List<PetType>) this.petTypeServiceImpl.findAll();
	
	}
	
	@RequestMapping( value 		= "/page/pet/listTreatment",
					headers 	=  "Content-type=application/json",
					method 		= RequestMethod.GET,
					produces 	= MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public List<TreatmentType> getTreatment() {

		return (List<TreatmentType>) this.treatmentTypeServiceImpl.findAll();

	}

}
