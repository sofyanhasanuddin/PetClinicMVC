package org.sofyan.latihan.app.service;

import java.util.List;

import org.sofyan.latihan.app.model.Pet;

public interface PetService extends BaseService<Pet, Long> {

	List<Pet> findAllActivePetByOwnerId(Long id);

}
