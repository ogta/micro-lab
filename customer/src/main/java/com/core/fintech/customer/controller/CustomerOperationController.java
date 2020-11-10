package com.core.fintech.customer.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.fintech.customer.model.Customer;
import com.core.fintech.customer.repository.ICustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerOperationController {

	@Autowired
	private ICustomerRepository customerRepository;

	@GetMapping("/")
	public ResponseEntity<Iterable<Customer>> getCustomers() {
		return new ResponseEntity<Iterable<Customer>>(this.customerRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable("uuid") final Long uuid) {
		return new ResponseEntity<Optional<Customer>>(this.customerRepository.findById(uuid), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer createdCustomer = this.customerRepository.save(customer);
		return new ResponseEntity<Customer>(createdCustomer, HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity update(@RequestBody Customer customer) {
		Optional<Customer> findedCustomer = this.customerRepository.findById(customer.getUuid());
		if (findedCustomer == null || findedCustomer.get() == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		this.customerRepository.save(customer);
		return new ResponseEntity(HttpStatus.OK);
	
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity deactiveCustomer(@PathVariable("uuid") final Long uuid) {
		Optional<Customer> customer = this.customerRepository.findById(uuid);
		if (customer == null || customer.get() == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		Customer deactivetedCustomer = customer.get();
		deactivetedCustomer.setStatus(0);
		this.customerRepository.save(customer.get());
		return new ResponseEntity(HttpStatus.OK);
	}

}
