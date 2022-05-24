package edu.cscc;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryImplTest {

    CustomerRepository customerRepository = new CustomerRepositoryImpl();

    Customer customer1 = new Customer("Mary", "Smith", "marySmith2022@gmail.com");
    Customer customer2 = new Customer("Joe", "Smith", "joeSmith2022@gmail.com");
    Customer customer3 = new Customer("Jane", "Smith", "joeSmith2022@gmail.com");

    @Test
    void itCanCreateCustomerEntry() throws DuplicateCustomerException {
        customer1 = customerRepository.create(customer1);
    }

    @Test
    void itCanThrowDuplicateCustomerFoundExceptionForCreate() throws DuplicateCustomerException {
        customer2 = customerRepository.create(customer2);
        assertThrows(DuplicateCustomerException.class, () -> customer3 = customerRepository.create(customer3));
    }

    @Test
    void itCanReadCustomerEntry() throws DuplicateCustomerException {
        customer1 = customerRepository.create(customer1);
        assertEquals(customer1.getFirstName(), customerRepository.read(customer1.getAccountNumber()).getFirstName());
    }

    @Test
    void itCanFindCustomerByEmailAddress() throws DuplicateCustomerException {
        customer1 = customerRepository.create(customer1);
        assertEquals(customer1.getEmailAddress(), customerRepository.read(customer1.getAccountNumber()).getEmailAddress());
    }

    @Test
    void itCanUpdateCustomer() throws DuplicateCustomerException, CustomerNotFoundException {
        customer1 = customerRepository.create(customer1);
        Customer updateCustomer1 = new Customer(customer1.getAccountNumber(), customer1.getFirstName(), "Johnson", customer1.getEmailAddress());
        customerRepository.update(updateCustomer1);
        assertEquals("Johnson", customer1.getLastName());
    }

    @Test
    void itCanThrowCustomerNotFoundExceptionForUpdate() throws DuplicateCustomerException, CustomerNotFoundException {
        customer1 = customerRepository.create(customer1);
        assertThrows(CustomerNotFoundException.class, () -> customerRepository.update(customer3));
    }

    @Test
    void itCanThrowDuplicateCustomerFoundExceptionForUpdate() throws DuplicateCustomerException {
        customer1 = customerRepository.create(customer1);
        customer2 = customerRepository.create(customer2);
        Customer updateCustomer1 = new Customer(customer1.getAccountNumber(), customer1.getFirstName(), "Johnson", "joeSmith2022@gmail.com");
        assertThrows(DuplicateCustomerException.class, () -> customerRepository.update(updateCustomer1));
    }

    @Test
    void itCanDeleteCustomer() throws DuplicateCustomerException, CustomerNotFoundException {
        customer1 = customerRepository.create(customer1);
        assertTrue(customerRepository.delete(customer1.getAccountNumber()));
    }

    @Test
    void itCanThrowCustomerNotFoundExceptionForDeletion() throws CustomerNotFoundException {
        assertThrows(CustomerNotFoundException.class, () -> customerRepository.delete(customer1.getAccountNumber()));
    }

    @Test
    void itCanCount() throws DuplicateCustomerException {
        customer1 = customerRepository.create(customer1);
        customer2 = customerRepository.create(customer2);
        assertEquals(2, customerRepository.count());
    }
}