package edu.cscc;

import java.util.*;

public class Main {

    private static final String CREATE_CUSTOMER = "1";
    private static final String FIND_BY_EMAIL = "2";
    private static final String FIND_BY_ACCOUNT_NUMBER = "3";
    private static final String UPDATE_CUSTOMER = "4";
    private static final String DELETE_CUSTOMER = "5";
    private static final String EXIT = "6";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String option = displayMainMenu(scanner);

        while (true) {
            switch (option) {
                case CREATE_CUSTOMER:
                    createCustomer(scanner);
                    break;
                case FIND_BY_EMAIL:
                    findByEmail(scanner);
                    break;
                case FIND_BY_ACCOUNT_NUMBER:
                    findByAccountNumber(scanner);
                    break;
                case UPDATE_CUSTOMER:
                    updateCustomer(scanner);
                    break;
                case DELETE_CUSTOMER:
                    deleteCustomer(scanner);
                    break;
                case EXIT:
                    System.out.println("Goodbye!");
                    System.exit(0);
            }

            option = displayMainMenu(scanner);
        }
    }

    private static void updateCustomer(Scanner scanner) {
        System.out.print("Account number: ");
        String input = scanner.nextLine();
        try {
            Customer customer = VideoStore.getInstance().getCustomer(input);
            System.out.println("Found customer to update: ");
            displayCustomer(customer);
            System.out.print("New First name: ");
            String firstName = scanner.nextLine();
            System.out.print("New Last name: ");
            String lastName = scanner.nextLine();
            System.out.print("New Email address name: ");
            String emailAddress = scanner.nextLine();
            VideoStore.getInstance().updateCustomer(input, firstName, lastName, emailAddress);
            System.out.println("Customer succesfully updated!");
            System.out.println();
        } catch (CustomerNotFoundException e) {
            System.out.println("Could not find customer to update for account number: " + input);
        } catch (DuplicateCustomerException e) {
            System.out.println("Customer with that email address already exists!");
        }
    }

    private static void deleteCustomer(Scanner scanner) {
        System.out.print("Account number: ");
        String input = scanner.nextLine();
        try {
            if (VideoStore.getInstance().deleteCustomer(input)) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Could not delete customer for account number: " + input);
            }
        } catch (CustomerNotFoundException e) {
            System.out.println("Could not delete customer for account number: " + input);
        }
    }

    private static void findByEmail(Scanner scanner) {
        System.out.print("Email address: ");
        String input = scanner.nextLine();
        Customer customer;
        customer = VideoStore.getInstance().findCustomerByEmailAddress(input);
        if (customer == null) {
            System.out.println("No customer found for that email address.");
            return;
        }
        displayCustomer(customer);
    }

    private static void findByAccountNumber(Scanner scanner) {
        System.out.print("Account number: ");
        String input = scanner.nextLine();
        Customer customer;
        try {
            customer = VideoStore.getInstance().getCustomer(input);
            if (customer == null) {
                System.out.println("No customer found for that account number.");
                return;
            }
        } catch(CustomerNotFoundException ex) {
            System.out.println("No customer found for that account number.");
            return;
        }

        displayCustomer(customer);
    }

    private static void displayCustomer(Customer customer) {
        System.out.println("Customer " + customer.getAccountNumber().toString());
        System.out.println("First name: " + customer.getFirstName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("Email address: " + customer.getEmailAddress());
        System.out.println();
    }

    private static void createCustomer(Scanner scanner) {
        System.out.print("Customer first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Customer last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Email address: ");
        String emailAddress = scanner.nextLine();

        try {
            UUID accountNumber = VideoStore.getInstance().createCustomer(firstName, lastName, emailAddress);
            System.out.println("Customer created with account number: " + accountNumber);
            System.out.println();
        } catch (DuplicateCustomerException e) {
            System.out.println("Customer with email address " + emailAddress + " already exists.");
        }
    }

    private static String displayMainMenu(Scanner scanner) {
        String option = "";
        Set<String> validOptions = getValidMenuOptions();
        while (!validOptions.contains(option)) {
            outputMenu();
            option = parseUserInput(scanner);
        }
        return option;
    }

    private static String parseUserInput(Scanner scanner) {
        String option = scanner.nextLine();
        return option;
    }

    private static Set<String> getValidMenuOptions() {
        Set<String> validOptions = new HashSet<>(Arrays.asList(CREATE_CUSTOMER, FIND_BY_EMAIL, FIND_BY_ACCOUNT_NUMBER, UPDATE_CUSTOMER, DELETE_CUSTOMER, EXIT));
        return validOptions;
    }

    private static void outputMenu() {
        System.out.println("Welcome to Lackluster Video!");
        System.out.println("****************************");
        System.out.println("Options are:");
        System.out.println("1. Create a Customer");
        System.out.println("2. Find a Customer by email address");
        System.out.println("3. Find a Customer by account number");
        System.out.println("4. Update a Customer");
        System.out.println("5. Delete a Customer");
        System.out.println("6. Exit");
        System.out.print("Please enter an option (1,2,3,4,5,6): ");
    }
}
