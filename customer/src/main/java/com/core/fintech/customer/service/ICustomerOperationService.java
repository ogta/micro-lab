package com.core.fintech.customer.service;

import java.util.Optional;

import com.core.fintech.customer.model.Customer;

public interface ICustomerOperationService {

	public Customer save(Customer customer);

	public Iterable<Customer> getCustomers();

	public Optional<Customer> getCustomer(Long uuid);

	public void deactiveCustomer(Long uuid, Customer customer);

}
