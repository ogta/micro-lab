package com.core.fintech.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.fintech.customer.model.Customer;
import com.core.fintech.customer.repository.ICustomerRepository;

@Service
public class CustomerOperationService implements ICustomerOperationService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public Customer save(Customer customer) {
		Customer savedCustomer = this.customerRepository.save(customer);
		return savedCustomer;
	}

	@Override
	public Iterable<Customer> getCustomers() {
		return this.customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomer(Long uuid) {
		return this.customerRepository.findById(uuid);
	}

	@Override
	public void deactiveCustomer(Long uuid, Customer customer) {
		customer.setStatus(0);
		save(customer);
	}

}
