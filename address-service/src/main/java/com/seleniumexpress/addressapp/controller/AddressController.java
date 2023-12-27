package com.seleniumexpress.addressapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.seleniumexpress.addressapp.response.AddressResponse;
import com.seleniumexpress.addressapp.service.AddressService;

@RestController
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	//Creating simple address controller
	@GetMapping("/address/{employeeId}")
	public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int id)
	{
		AddressResponse addressResponse = addressService.findAddressByEmployeeId(id) ;
		return new ResponseEntity<AddressResponse>(addressResponse, HttpStatus.OK);
	}

}
