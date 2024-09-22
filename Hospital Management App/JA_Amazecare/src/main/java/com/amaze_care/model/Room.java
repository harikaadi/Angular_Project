package com.amaze_care.model;



import com.amaze_care.enums.RoomType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private RoomType roomtype;

    private Boolean isAvailable;
    
    private int bedsCount;

    private Double rate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoomType getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(RoomType roomtype) {
		this.roomtype = roomtype;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getBedsCount() {
		return bedsCount;
	}

	public void setBedsCount(int bedsCount) {
		this.bedsCount = bedsCount;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}
