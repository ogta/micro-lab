package com.core.fintech.customer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.fintech.customer.entities.BaseResponse;
import com.core.fintech.customer.model.Customer;
import com.core.fintech.customer.service.ICustomerOperationService;

@RestController
@RequestMapping("/customer")
public class CustomerOperationController {

	@Autowired
	private ICustomerOperationService customerOperation;

	@GetMapping("/")
	public ResponseEntity<BaseResponse> getCustomers() {
		BaseResponse response = new BaseResponse();
		Iterable<Customer> customerList = this.customerOperation.getCustomers();
		if (customerList == null) {
			response.setErrorCode("01");
			response.setErrorDescription("Customer list is empty");
			return new ResponseEntity<BaseResponse>(response, HttpStatus.NO_CONTENT);
		}
		response.setResponseCode("00");
		response.setResponseDescription("Customer listed successfully");
		response.setObjectDetails(customerList);
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<BaseResponse> getCustomer(@PathVariable("uuid") final Long uuid) {
		BaseResponse response = new BaseResponse();
		Optional<Customer> customer = this.customerOperation.getCustomer(uuid);
		if (customer.isEmpty()) {
			response.setErrorCode("02");
			response.setErrorDescription("Customer is not found!");
			return new ResponseEntity<BaseResponse>(response, HttpStatus.NO_CONTENT);
		}
		response.setResponseCode("00");
		response.setResponseDescription("Customer get successfully!");
		response.setObjectDetails(customer);
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<BaseResponse> addCustomer(@Validated @RequestBody Customer customer) {
		BaseResponse response = new BaseResponse();
		response.setResponseCode("00");
		response.setResponseDescription("Customer added successfully!");
		Customer createdCustomer = this.customerOperation.save(customer);
		response.setObjectDetails(createdCustomer);
		return new ResponseEntity<BaseResponse>(response, HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<BaseResponse> update(@Validated @RequestBody Customer customer) {
		BaseResponse response = new BaseResponse();
		Optional<Customer> findedCustomer = this.customerOperation.getCustomer(customer.getUuid());
		if (findedCustomer.isEmpty()) {
			response.setErrorCode("02");
			response.setErrorDescription("Customer is not found!");
			return new ResponseEntity<BaseResponse>(response, HttpStatus.NO_CONTENT);
		}
		Customer customerForUpdate = findedCustomer.get();
		customerForUpdate.setIdentityNumber(customer.getIdentityNumber());
		customerForUpdate.setName(customer.getName());
		customerForUpdate.setSurname(customer.getSurname());
		response.setResponseCode("00");
		response.setResponseDescription("Customer updated successfully!");
		Customer updatedCustomer = this.customerOperation.save(customerForUpdate);
		response.setObjectDetails(updatedCustomer);
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);

	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<BaseResponse> deactiveCustomer(@PathVariable("uuid") final Long uuid) {
		BaseResponse response = new BaseResponse();
		Optional<Customer> customer = this.customerOperation.getCustomer(uuid);
		if (customer.isEmpty()) {
			response.setErrorCode("02");
			response.setErrorDescription("Customer is not found!");
			return new ResponseEntity<BaseResponse>(response, HttpStatus.NO_CONTENT);
		}

		this.customerOperation.deactiveCustomer(uuid, customer.get());
		response.setResponseCode("00");
		response.setResponseDescription("Customer deleted successfully!");
		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
	}

}
