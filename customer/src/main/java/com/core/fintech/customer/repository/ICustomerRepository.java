package com.core.fintech.customer.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.core.fintech.customer.model.Customer;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {

}
