package edu.cscc;

import java.util.UUID;

/**
 * This class represents a video rental store in the Lackluster Video Rental business. Right now it contains the logic
 * for managing {@link Customer}s within the system. This class should be a singleton, and will utilize the {@link CustomerRepository}
 * class to managing the actual customer data.
 */
public class VideoStore {

    /**
     * Get the singleton instance of the VideoStore.
     * @return The singleton instance.
     */
    public static VideoStore getInstance() {
        return null;
    }

    /**
     * Create a customer using the given parameters. This method will throw a {@link DuplicateCustomerException} when
     * an attempt to create a {@link Customer} with the same email address occurs.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param emailAddress The email address of the customer.
     * @return The accountNumber of the customer as a {@link UUID}.
     * @throws DuplicateCustomerException
     */
    public UUID createCustomer(String firstName, String lastName, String emailAddress) throws DuplicateCustomerException {
        return null;
    }

    /**
     * Finds a customer by their email address.
     * @param emailAddress The email address of the customer to search for.
     * @return The {@link Customer} that is found, or null if one couldn't be found.
     */
    public Customer findCustomerByEmailAddress(String emailAddress) {
        return null;
    }

    /**
     * Get a customer by the accountNumber. This method throws a {@link CustomerNotFoundException} when the customer
     * cannot be found by account number.
     * @param input The account number of the customer.
     * @return {@link Customer} found.
     * @throws CustomerNotFoundException
     */
    public Customer getCustomer(String input) throws CustomerNotFoundException {
        return null;
    }

    /**
     * Updates a given {@link Customer}. Only the firstName, lastName, and emailAddress will be updated. The accountNumber
     * is used to look up the Customer.
     *
     * This method throws a CustomerNotFoundException if the customer could not be found by account number.
     * This method throws a DuplicateCustomerException if a customer already exists with the given email address (other than the customer being updated).
     * @param accountNumber The account number to look up the customer with.
     * @param firstName The first name to update the customer with.
     * @param lastName  The last name to update the customer with.
     * @param emailAddress The email address to update the customer with.
     * @throws CustomerNotFoundException
     * @throws DuplicateCustomerException
     */
    public void updateCustomer(String accountNumber, String firstName, String lastName, String emailAddress) throws CustomerNotFoundException, DuplicateCustomerException {

    }

    /**
     * Delete a {@link Customer} by their account number.
     * This method throws a {@link CustomerNotFoundException} when the customer could not be found by account number.
     * @param input The account number of the Customer.
     * @return true if the deletion was successful, false otherwise.
     * @throws CustomerNotFoundException
     */
    public boolean deleteCustomer(String input) throws CustomerNotFoundException {
        return false;
    }
}
