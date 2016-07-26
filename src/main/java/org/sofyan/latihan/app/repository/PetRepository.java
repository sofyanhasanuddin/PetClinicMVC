package org.sofyan.latihan.app.repository;

import java.util.List;

import org.sofyan.latihan.app.model.Pet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;

public interface PetRepository extends BaseRepository<Pet, Long> {
	
	@EntityGraph(attributePaths="type",type=EntityGraphType.FETCH)
	List<Pet> findAllByOwnerIdAndDeletedIsFalse(@Param("ownerId")Long ownerId);

}
