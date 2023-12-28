package com.seleniumexpress.employeeapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.seleniumexpress.employeeapp.entity.Employee;
import com.seleniumexpress.employeeapp.repo.EmployeeRepository;
import com.seleniumexpress.employeeapp.response.AddressResponse;
import com.seleniumexpress.employeeapp.response.EmployeeResponse;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//@Autowired
	private RestTemplate restTemplate;
	
	/*
	 * @Value("${adressservice.base.url}") 
	 * private String addressBaseUrl;
	 */
	
	public EmployeeService(@Value("${adressservice.base.url}") String addressBaseUrl, RestTemplateBuilder builder)
	{
		//System.out.println("uri"+addressBaseUrl);
		this.restTemplate = builder
				.rootUri(addressBaseUrl)
				.build();
	}

	public EmployeeResponse getEmployeeById(int id)
	{
		
		
		//EmployeeResponse employeeResponse = new EmployeeResponse();
		Employee employee = employeeRepository.findById(id).get();
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
//		employeeResponse.setId(employee.getId());
//		employeeResponse.setName(employee.getName());
//		employeeResponse.setEmail(employee.getEmail());
//		employeeResponse.setBloodGroup(employee.getBloodGroup());
		//Fetching data from hitting the url through the rest template
		AddressResponse addressResponse = restTemplate.getForObject("/address/{id}", AddressResponse.class,id);
		employeeResponse.setAddressResponse(addressResponse);
		return employeeResponse;
	}
}
