package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Parking;

public interface ParkService {

	String getAvailableSlot();
	void updateAvailability(String slotno);
	List<Parking> getAllSlots();
	Parking findBySlotno(String str);
	void save(Parking curr_slot);
	
}
