package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="parking_slots")
public class Parking {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;
	
	@Column(name= "slot_no")
	private String slotno;

	@Column(name= "available")
	private int available;

	public String getSlotno() {
		return slotno;
	}

	public void setSlotno(String slotno) {
		this.slotno = slotno;
	}

	@Override
	public String toString() {
		return "Parking [id=" + id + ", slot_no=" + slotno + ", available=" + available + "]";
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}