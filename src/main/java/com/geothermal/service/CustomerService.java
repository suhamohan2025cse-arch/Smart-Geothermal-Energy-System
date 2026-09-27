package com.geothermal.service;

import com.geothermal.entity.Customer;
import com.geothermal.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Customer getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public Customer update(Long id, Customer input) {
        Customer customer = getById(id);
        customer.setName(input.getName());
        customer.setDistrict(input.getDistrict());
        customer.setContact(input.getContact());
        return repository.save(customer);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
