package org.example;

import models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<BankAccount> bohdanAccounts = new ArrayList<>();
        Customer customer1 = new Customer("C001" , "Bohdan" , "Poruchnik" , "bogomdannyibogdan@gmail.com" );

        List<BankAccount> vladislavAccounts = new ArrayList<>();
        Customer customer2 = new Customer("C002" , "Vladislav" , "Solovyov" , "vladislav007@gmail.com" );

        System.out.println("Creating accounts for " + customer1.getFirstName() + " " + customer1.getLastName());
        System.out.println("Creating accounts for " + customer2.getFirstName() + " " + customer2.getLastName());

        BankAccount bohdanAccount1 = new CheckingAccount("A001", "Bohdan Poruchnik", 1000.0, 500);
        Card visaCard = new Card(
                "1111222233334444",
                "999",
                "0000",
                LocalDateTime.now().plusYears(4),
                true,
                bohdanAccount1
        );

        visaCard.pay(250.0);

        BankAccount vladislavAccount2 = new SavingAccount("A002", customer2.getFirstName() + " " + customer2.getLastName(), 5000.0, 0.02);

        bohdanAccounts.add(bohdanAccount1);
        vladislavAccounts.add(vladislavAccount2);

        customer1.setAccounts(bohdanAccounts);
        customer2.setAccounts(vladislavAccounts);

        printCustomerAccounts("\nAccount info before transfer for ", customer1);
        printCustomerAccounts("\nAccount info before transfer for ", customer2);

        System.out.println("\nTransferring 200 from " + customer1.getFirstName() + " to " + customer2.getFirstName());
        bohdanAccount1.transfer(vladislavAccount2, 200);
        printCustomerAccounts("\nAccount info after transfer for ", customer1);
        printCustomerAccounts("\nAccount info after transfer for ", customer2);

        System.out.println("\nTransferring 1000 from " + customer1.getFirstName() + " to " + customer2.getFirstName());
        bohdanAccount1.transfer(vladislavAccount2, 1000);

        printCustomerAccounts("\nAccount info after transfer for ", customer1);
        printCustomerAccounts("\nAccount info after transfer for ", customer2);

        System.out.println("\nTransferring 1000 from " + customer1.getFirstName() + " to " + customer2.getFirstName());
        bohdanAccount1.transfer(vladislavAccount2, 1000);

        printCustomerAccounts("\nAccount info after transfer for ", customer1);
        printCustomerAccounts("\nAccount info after transfer for ", customer2);

        System.out.println("\nTransferring 1000 from " + customer2.getFirstName() + " to " + customer1.getFirstName());
        vladislavAccount2.transfer(bohdanAccount1, 2200);

        printCustomerAccounts("\nAccount info after transfer for ", customer1);
        printCustomerAccounts("\nAccount info after transfer for ", customer2);

        System.out.println("\nHistory");
        bohdanAccount1.printStatement();
        vladislavAccount2.printStatement();
    }

    private static void printCustomerAccounts(String prefix, Customer customer) {
        System.out.println(prefix + customer.getFirstName() + ":");
        for (BankAccount account : customer.getAccounts()) {
            account.printAccountInfo();
            System.out.println("Account Type: " + account.getAccountType());
        }
    }
}