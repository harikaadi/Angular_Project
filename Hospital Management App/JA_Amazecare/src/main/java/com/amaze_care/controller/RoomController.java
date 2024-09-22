package com.amaze_care.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.amaze_care.dto.MessageDto;
import com.amaze_care.exception.NoRoomsAvailableException;
import com.amaze_care.model.Room;
import com.amaze_care.service.RoomService;

@RestController
public class RoomController {
	
	
	@Autowired
	private RoomService roomservice;
	
	 @PostMapping("/room/add")
	    public ResponseEntity<?> addRoom(@RequestBody Room room, MessageDto messageDto) {
	        try {
	            Room savedRoom = roomservice.addRoom(room);
	            return ResponseEntity.ok(savedRoom);
	        } catch (Exception e) {
	            messageDto.setMsg(e.getMessage());
	            return ResponseEntity.badRequest().body(messageDto);
	        }
	    }

	    @GetMapping("/room/avail")
	    public ResponseEntity<?> getAvailableRooms(MessageDto messageDto) {
	    	System.out.println("room available");
	        try {
	            List<Room> rooms = roomservice.getAvailableRooms();
	            return ResponseEntity.ok(rooms);
	        } catch (NoRoomsAvailableException e) {
	            messageDto.setMsg(e.getMessage());
	            return ResponseEntity.badRequest().body(messageDto);
	        }
	    }

}
