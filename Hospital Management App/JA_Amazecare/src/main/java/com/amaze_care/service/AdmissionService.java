package com.amaze_care.service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.amaze_care.enums.CommonHealthIssues;
import com.amaze_care.enums.DoctorType;
import com.amaze_care.enums.RoomType;
import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.exception.NoRoomsAvailableException;

import com.amaze_care.model.Admission;
import com.amaze_care.model.Doctor;
import com.amaze_care.model.HealthIssue;
import com.amaze_care.model.InPatient;
import com.amaze_care.model.Room;

import com.amaze_care.repo.AdmissionRepository;
import com.amaze_care.repo.DoctorRepository;
import com.amaze_care.repo.HealthIssueRepository;
import com.amaze_care.repo.InPatientRepository;
import com.amaze_care.repo.RoomRepository;

@Service
public class AdmissionService {
	
	@Autowired
	private InPatientRepository patientrepo;
	
	@Autowired
	private HealthIssueRepository healthissuerepo;
	
	@Autowired
	private DoctorRepository doctorepo;
	
	@Autowired
	private RoomRepository roomrepo;
	
	@Autowired
	private AdmissionRepository admissionrepository;
	
	
	public Admission addAdmission(int patientid, String issueName, String roomtype, Admission admission) throws InvalidIdException, NoRoomsAvailableException {
		System.out.println("inside add_admission service");
     //fetch patient by patientID
		 Optional <InPatient> optionpatient = patientrepo.findById(patientid);
		//Optional <InPatient> optionpatient = patientrepo.getByUsername(principal.getName());
		 if(optionpatient.isEmpty()) {
			 throw new InvalidIdException("patientid is invalid");
		     }
		 InPatient patient = optionpatient.get();
		 System.out.println("after patient ");
		 System.out.println(" patient "+patient);
		 
	 //check whether healthissue exsits
		/* Optional <HealthIssue> optionhealthissue= healthissuerepo.findByIssueName(issueName);
		 if(optionhealthissue.isEmpty()) {
			 throw new InvalidIdException("healthissue is invalid");
		 }
		 HealthIssue healthisuue = optionhealthissue.get();
		 System.out.println("after healthissue ");
		 System.out.println("after healthissue "+healthisuue);
		 
	 //fetch doctor by healthissue
		Optional<Doctor> optiondoctor = doctorepo.findById(healthisuue.getDoctor().getId());
		Doctor doctor =optiondoctor.get();
		System.out.println("after doctor ");
		 System.out.println("after doctor "+doctor);*/
		 
		 
		 DoctorType doctorType = DoctorType.INPATIENT_DOCTOR;
	     System.out.println(issueName);
	     System.out.println(CommonHealthIssues.valueOf(issueName));
		 Optional<List<Doctor>> optiondoctor= doctorepo.findByHissueAndDoctortypeAndAvailable(CommonHealthIssues.valueOf(issueName), doctorType, true);
	        
		 if (optiondoctor.isEmpty()) {
	            throw new InvalidIdException("No available doctors found for the specified health issue.");
	        }
	        //Doctor doctor = optiondoctor.get().get(1);
	        List<Doctor> listdoctor = optiondoctor.get();

	     // Initialize variables to track the doctor with the minimum patients
	     Doctor doctorWithMinPatients = listdoctor.get(0); // Default to the first doctor
	     int minPatientCount = Integer.MAX_VALUE; // Set an initial high value for comparison

	     // Loop through each doctor in the list and find the one with the least patients
	     for (Doctor doctor : listdoctor) {
	         // Assuming you have a method in your service/repository to count patients by doctorId
	         int patientCount = admissionrepository.countPatientsByDoctorId(doctor.getId()); // Replace with actual method

	         // Compare patient count and keep track of the doctor with the minimum patients
	         if (patientCount < minPatientCount) {
	             minPatientCount = patientCount;
	             doctorWithMinPatients = doctor; // Assign the doctor with fewer patients
	         }
	        
	     }
		
	// Convert roomTypeStr to RoomType enum
        /*RoomType roomType;
        try {
            roomType = RoomType.valueOf(roomtype);
            System.out.println("inside room conversion ");
   		 System.out.println("room coversion "+roomType);
        } catch (IllegalArgumentException e) {
            throw new InvalidIdException("Invalid room type specified.");
        }*/
        
		
	//finding room
		 System.out.println("romm"+roomtype);
		List<Room> roomsavalible = roomrepo.findRoomAvailable(RoomType.valueOf(roomtype));
		if(roomsavalible.isEmpty()) {
			throw new NoRoomsAvailableException("No available rooms found for the specified room type.");
		}
		
		// Select the first available room (or implement your logic for room selection)
        Room selectedRoom = roomsavalible.get(0);
        
        //modify booked room details or Mark the room as occupied if necessary
        selectedRoom.setBedsCount(selectedRoom.getBedsCount()-1);  
        
        if(selectedRoom.getBedsCount() == 0) {
        	selectedRoom.setIsAvailable(false);
        }
        
        roomrepo.save(selectedRoom);
        
     // Set details on admission
        admission.setInpatient(patient);
        admission.setDoctor(doctorWithMinPatients);
        admission.setRoom(selectedRoom);
        admission.setAdmissionDate(LocalDate.now());
        admission.setAdmissionStatus("admitted");
        
        

    // Save the admission
        return admissionrepository.save(admission);

		 }
	
	
	

}
