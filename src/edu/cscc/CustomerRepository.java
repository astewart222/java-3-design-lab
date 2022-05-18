package edu.cscc;

import java.util.UUID;

/**
 * This is interface to be used when implementing the customer repository (following the Repository pattern).
 */
public interface CustomerRepository {

    /**
     * Create a new {@link Customer} object to be stored in the repository.
     *
     * This method should throw a {@link DuplicateCustomerException} if a customer with the same email address is attempted to be
     * created.
     * @param customer The customer to be stored.
     * @return A new Customer with the account number set on it.
     * @throws DuplicateCustomerException
     */
    Customer create(Customer customer) throws DuplicateCustomerException;

    /**
     * Find a {@link Customer} by account number.
     * @param accountNumber The account number of the Customer search for.
     * @return The Customer if one is found, null otherwise.
     */
    Customer read(UUID accountNumber);

    /**
     * Find a {@link Customer} by email address.
     * @param emailAddress The email address to search for a Customer by.
     * @return The found Customer, or null.
     */
    Customer findByEmailAddress(String emailAddress);

    /**
     * Update a given {@link Customer}.
     * This method throws a {@link CustomerNotFoundException} when a Customer could not by found by their account number.
     * This method throws a {@link DuplicateCustomerException} when another Customer exists with the provided email address.
     * @param customer The Customer to update with the fields to update set.
     * @throws CustomerNotFoundException
     * @throws DuplicateCustomerException
     */
    void update(Customer customer) throws CustomerNotFoundException, DuplicateCustomerException;

    /**
     * Delete a given {@link Customer} from the repository by account number.
     * This method throws a {@link CustomerNotFoundException} when the Customer to delete could not be found by account number.
     * @param accountNumber The account number of the Customer to search for and delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws CustomerNotFoundException
     */
    boolean delete(UUID accountNumber) throws CustomerNotFoundException;

    /**
     * Returns back the total count of Customers.
     * @return The current count of customers.
     */
    int count();
}
