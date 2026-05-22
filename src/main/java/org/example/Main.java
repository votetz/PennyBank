package org.example;

import models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<BankAccount> ivanovAccounts = new ArrayList<>();
        Customer customer1 = new Customer("C001", "Ivan", "Ivanov", "ivan.ivanov@gmail.com");

        List<BankAccount> petrovAccounts = new ArrayList<>();
        Customer customer2 = new Customer("C002", "Petr", "Petrov", "petr.petrov@gmail.com");

        System.out.println("Creating accounts for " + customer1.getFirstName() + " " + customer1.getLastName());
        System.out.println("Creating accounts for " + customer2.getFirstName() + " " + customer2.getLastName());

        CheckingAccount ivanovAccount1 = new CheckingAccount("A001", "Ivan Ivanov", 1000.0, Currency.USD, 500);
        Card visaCard = new Card(
                "1111222233334444",
                "999",
                "0000",
                LocalDateTime.now().plusYears(4),
                true,
                ivanovAccount1
        );

        visaCard.pay(250.0);

        SavingAccount petrovAccount2 = new SavingAccount("A002", customer2.getFirstName() + " " + customer2.getLastName(), 5000.0, Currency.UAH, 0.02);

        ivanovAccounts.add(ivanovAccount1);
        petrovAccounts.add(petrovAccount2);

        customer1.setAccounts(ivanovAccounts);
        customer2.setAccounts(petrovAccounts);

        printCustomerAccounts("\nAccount info before transfer for ", customer1);
        printCustomerAccounts("\nAccount info before transfer for ", customer2);

        System.out.println("\nTransferring 200 from " + customer1.getFirstName() + " to " + customer2.getFirstName());
        ivanovAccount1.transfer(petrovAccount2, 200);
        printCustomerAccounts("\nAccount info after transfer for ", customer1);
        printCustomerAccounts("\nAccount info after transfer for ", customer2);

        System.out.println("\nTransferring 1000 from " + customer1.getFirstName() + " to " + customer2.getFirstName());
        ivanovAccount1.transfer(petrovAccount2, 1000);

        printCustomerAccounts("\nAccount info after transfer for ", customer1);
        printCustomerAccounts("\nAccount info after transfer for ", customer2);

        System.out.println("\nTransferring 1000 from " + customer1.getFirstName() + " to " + customer2.getFirstName());
        ivanovAccount1.transfer(petrovAccount2, 1000);

        printCustomerAccounts("\nAccount info after transfer for ", customer1);
        printCustomerAccounts("\nAccount info after transfer for ", customer2);

        System.out.println("\nTransferring 2200 from " + customer2.getFirstName() + " to " + customer1.getFirstName());
        petrovAccount2.transfer(ivanovAccount1, 2200);

        printCustomerAccounts("\nAccount info after transfer for ", customer1);
        printCustomerAccounts("\nAccount info after transfer for ", customer2);

        System.out.println("\nHistory");
        ivanovAccount1.printStatement();
        petrovAccount2.printStatement();

        System.out.println("\n========== MULTI-CURRENCY TRANSFER TEST ==========");
        
        CheckingAccount usdAccount = new CheckingAccount("A003", "Bohdan", 1000.0, Currency.USD, 200);
        SavingAccount uahAccount = new SavingAccount("A004", "Vladyslav", 40000.0, Currency.UAH, 0.03);
        SavingAccount eurAccount = new SavingAccount("A005", "Maria", 920.0, Currency.EUR, 0.025);
        
        System.out.println("\n--- Initial balances ---");
        usdAccount.printAccountInfo();
        uahAccount.printAccountInfo();
        eurAccount.printAccountInfo();
        
        System.out.println("\n--- Transfer 100 USD -> UAH ---");
        usdAccount.transfer(uahAccount, 100);
        
        System.out.println("\n--- Transfer 1000 UAH -> EUR ---");
        uahAccount.transfer(eurAccount, 1000);
        
        System.out.println("\n--- Transfer 50 EUR -> USD ---");
        eurAccount.transfer(usdAccount, 50);
        
        System.out.println("\n--- Final balances ---");
        usdAccount.printAccountInfo();
        uahAccount.printAccountInfo();
        eurAccount.printAccountInfo();
    }

    private static void printCustomerAccounts(String prefix, Customer customer) {
        System.out.println(prefix + customer.getFirstName() + ":");
        for (BankAccount account : customer.getAccounts()) {
            account.printAccountInfo();
            System.out.println("Account Type: " + account.getAccountType());
        }
    }
}