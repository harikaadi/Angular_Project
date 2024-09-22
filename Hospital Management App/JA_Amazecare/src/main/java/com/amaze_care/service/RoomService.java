package com.amaze_care.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amaze_care.exception.NoRoomsAvailableException;
import com.amaze_care.model.Room;
import com.amaze_care.repo.RoomRepository;

@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomrepository;
	
	public Room addRoom(Room room) {
        return roomrepository.save(room);
    }

    public List<Room> getAvailableRooms() throws NoRoomsAvailableException {
    	System.out.println("inside avaliable rooms");
        Optional<List<Room>> optionalRooms = roomrepository.findByisAvailable(true);
        
        if (optionalRooms.isEmpty()) {
            throw new NoRoomsAvailableException("No available rooms found.");
        }
        return optionalRooms.get();
    }

}
