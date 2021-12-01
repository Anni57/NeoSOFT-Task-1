package com.nsft.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T,ID> {
	
	T findByIdAndIsActive(ID id,Boolean isActive);
	
	List<T> findAllByIsActiveOrderById(Boolean isActive);

}
