package com.amaze_care.model;



import com.amaze_care.enums.TestName;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Test {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@Enumerated(EnumType.STRING)
    private TestName testName;
    
    private String description;

    private int price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TestName getTestName() {
		return testName;
	}

	public void setTestName(TestName testName) {
		this.testName = testName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
