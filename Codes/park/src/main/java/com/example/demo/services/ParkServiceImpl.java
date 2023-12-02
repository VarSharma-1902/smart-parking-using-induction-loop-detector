package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Parking;
import com.example.demo.repository.ParkRepo;

@Service
@Transactional
public class ParkServiceImpl implements ParkService{
	@Autowired
	ParkRepo parkrep;

	@Override
	public List<Parking> getAllSlots() {
		return (List<Parking>)parkrep.findAll();
	}

	@Override
	public String getAvailableSlot() {
		List<Parking> park = parkrep.findByAvailableEquals(1);
		if(park.size()==0)
		{
			return "-1";
		}
		Parking value = park.get(0);
		String val = value.getSlotno();
		System.out.println(val);
		return val;
	}

	@Override
	public void updateAvailability(String slotno) {
		// TODO Auto-generated method stub
		Parking obj = parkrep.findBySlotno(slotno);
		obj.setAvailable(0);
		parkrep.save(obj);
	}


	@Override
	public void save(Parking curr_slot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Parking findBySlotno(String str) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
