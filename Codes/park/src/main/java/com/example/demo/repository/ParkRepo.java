package com.example.demo.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Parking;

@Repository
public interface ParkRepo extends CrudRepository<Parking, Integer>{

	List<Parking> findByAvailableEquals(int i);

	Parking findBySlotno(String slotno);

	
}
