package com.amaze_care.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amaze_care.exception.InvalidIdException;
import com.amaze_care.model.InPatientTest;
import com.amaze_care.model.InpatientTestResult;
import com.amaze_care.repo.InpatientTestRepository;
import com.amaze_care.repo.InpatientTestResultRepository;

@Service
public class InpatientTestResultService {
	
	@Autowired
	private InpatientTestResultRepository inpatienttestresultrepository;
	
	@Autowired
	private InpatientTestRepository inpatienttestrepo;
	
	
public InpatientTestResult addTestResult(MultipartFile file, int inpatienttestid) throws IOException, InvalidIdException {
		
		
		//fetching the inpatient-test by id 
		Optional<InPatientTest>  optionalinpatienttest= inpatienttestrepo.findById(inpatienttestid);
		if(optionalinpatienttest.isEmpty()) {
			throw new InvalidIdException("invalid inpatient-test id ");
		}
		InPatientTest inpatienttest = optionalinpatienttest.get();
		
		//saving this inpatienttest into inpatientresult
		InpatientTestResult inpatinettestresult = new InpatientTestResult();
		
		inpatinettestresult.setInPatientTest(inpatienttest);
		
		String filename = file.getOriginalFilename();
		//creating new file
		//File destinationfile = new File("C:/Users/Harika Adi/Desktop/A/hospital-amazecare-app/src/main/resources/static/" + filename);
		
		File destinationfile = new File("C:/Users/Aarthi/Documents/GitHub/Angular_Project/Hospital Management App/JA_Amazecare/src/main/resources/static/reports/inpatients/" + filename);
		//creating new filestream
		FileOutputStream fos = new FileOutputStream("C:/Users/Aarthi/Documents/GitHub/Angular_Project/Hospital Management App/JA_Amazecare/src/main/resources/static/reports/inpatients/" + filename);
	 
        //save this in the DB
		
		InputStream is= file.getInputStream();
           byte[] byt=new byte[is.available()];
           is.read(byt);
           fos.write(byt);
           fos.close();
           
           inpatinettestresult.setFileName(filename);
           inpatinettestresult.setFilePath(destinationfile.getAbsolutePath());
           inpatinettestresult.setUploadedAt(LocalDate.now());
           
           return inpatienttestresultrepository.save(inpatinettestresult);
           
           
		
	}


}
