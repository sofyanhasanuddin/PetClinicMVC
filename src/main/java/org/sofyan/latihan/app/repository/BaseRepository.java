package org.sofyan.latihan.app.repository;

import java.io.Serializable;

import org.sofyan.latihan.app.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseRepository <T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, Serializable>, PagingAndSortingRepository<T, Serializable>, JpaSpecificationExecutor<T>{

}
