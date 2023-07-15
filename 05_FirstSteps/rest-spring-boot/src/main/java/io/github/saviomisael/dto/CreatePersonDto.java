package io.github.saviomisael.dto;

import jakarta.validation.constraints.*;

public class CreatePersonDto {
	@Size(min = 3, max = 255, message = "firstName must have at least 3 characters and no more than 255 characters.")
	private String firstName;

	@Size(min = 3, max = 255, message = "lastName must have at least 3 characters and no more than 255 characters.")
	private String lastName;

	@NotEmpty(message = "address is required.")
	private String address;

	@Pattern(regexp = "^(Male|Female)$", message = "gender must be Male or Female.")
	private String gender;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
