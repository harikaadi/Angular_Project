package com.amaze_care.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amaze_care.enums.RoomType;
import com.amaze_care.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	// select * from room where roomtype="GENERAL_WARD_ROOM" AND beds_count>0 and is_available=1;AND r.bedsCount > 0 AND r.isAvailable = true
		 @Query("select r from Room r where r.roomtype = ?1 AND r.bedsCount > 0 AND r.isAvailable = true")
		 List<Room> findRoomAvailable(RoomType roomType);
		 
		Optional<List<Room>> findByisAvailable(Boolean isAvailable);
		 

}
