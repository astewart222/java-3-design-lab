package edu.cscc;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class CustomerRepositoryImpl implements CustomerRepository {

    private Set<Customer> customers;

    public CustomerRepositoryImpl(){
        customers = new HashSet<>();
    }

    public Customer create(Customer customer) throws DuplicateCustomerException {
        boolean foundMatch = customers.stream()
                .anyMatch(customer1 -> customer1.getEmailAddress().equals(customer.getEmailAddress()));

        if (foundMatch) {
            throw new DuplicateCustomerException(customer.getEmailAddress());
        } else {
            customer.setAccountNumber(UUID.randomUUID());
            customers.add(customer);
            return customer;
        }
    }

    public Customer read(UUID accountNumber){
        Optional<Customer> accountFound = customers.stream()
                .filter(customer -> customer.getAccountNumber() == accountNumber)
                .findFirst();

        if (accountFound.isPresent()) {
            return accountFound.get();
        } else {
            return null;
        }
    }

    @Override
    public Customer findByEmailAddress(String emailAddress) {
        Optional<Customer> emailFound = customers.stream()
                .filter(customer -> customer.getEmailAddress().toLowerCase().equals(emailAddress.toLowerCase()))
                .findFirst();

        if (emailFound.isPresent()) {
            return emailFound.get();
        } else {
            return null;
        }
    }

    public void update(Customer customer) throws DuplicateCustomerException, CustomerNotFoundException {
        Optional<Customer> outdatedCustomer = customers.stream()
                .filter(customer1 -> customer1.getAccountNumber() == customer.getAccountNumber())
                .findFirst();

        if (outdatedCustomer.isPresent()){
            //Found the customer. If the email has changed, make sure it doesn't match any other customer
            if (outdatedCustomer.get().getEmailAddress().equals(customer.getEmailAddress())){
                outdatedCustomer.get().setLastName(customer.getLastName());
                outdatedCustomer.get().setFirstName(customer.getLastName());
            } else {
                boolean foundMatch = customers.stream()
                        .anyMatch(customer1 -> customer1.getEmailAddress().equals(customer.getEmailAddress()));

                if (foundMatch) {
                    throw new DuplicateCustomerException(customer.getEmailAddress());
                } else {
                    outdatedCustomer.get().setLastName(customer.getLastName());
                    outdatedCustomer.get().setFirstName(customer.getLastName());
                    outdatedCustomer.get().setEmailAddress(customer.getEmailAddress());
                }
            }

        } else {
            throw new CustomerNotFoundException(null);
        }
    }

    public boolean delete(UUID accountNumber) throws CustomerNotFoundException {
        Optional<Customer> customer = customers.stream()
                .filter(customer1 -> customer1.getAccountNumber() == accountNumber)
                .findFirst();

        if (customer.isPresent()){
            customers.remove(customer);
            return  true;
        } else {
            throw new CustomerNotFoundException(null);
        }
    }

    public int count(){return customers.size();}
}
