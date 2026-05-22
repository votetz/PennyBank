package models;

import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount implements interfaces.Transferable {
    private String accountId;
    private String ownerName;
    private double balance;
    private Currency currency;
    private List<Transaction> transactionHistory = new ArrayList<>();

    public abstract String getAccountType();

    public void printAccountInfo() {
        System.out.println("Account ID: " + accountId);
        System.out.println("Owner Name: " + ownerName);
        System.out.println("Balance: " + balance + " " + currency);
    }

    public BankAccount(String accountId, String ownerName, double balance, Currency currency) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit in " + amount + " " + currency + ". New balance: " + balance + " " + currency);
            logTransaction("Deposit", amount, "Refill account" );
        }
    }

    public boolean withdraw(double amount) {
        return withdraw(amount, "Withdrawal", "Withdraw funds");
    }

    public boolean withdraw(double amount, String type, String description) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn " + amount + " " + currency + ". New balance: " + balance + " " + currency);
            logTransaction(type, amount, description);
            return true;
        } else {
            System.out.println("Withdrawal failed: insufficient funds or invalid amount");
            return false;
        }
    }

    @Override
    public void transfer(BankAccount target, double amount) {
        if (this.withdraw(amount)) {
            double convertedAmount = CurrencyConverter.convert(amount, this.currency, target.getCurrency());
            
            target.deposit(convertedAmount);
            System.out.println("Transfer successful!");
            this.logTransaction("Transfer out", amount, "Sent to " + target.getOwnerName() + " (ID: " + target.getAccountId() + ")");
            target.logTransaction("Transfer in", convertedAmount, "Received from " + this.getOwnerName() + " (ID: " + this.getAccountId() + ")");
        } else {
            System.out.println("Transfer aborted.");
        }
    }

    protected void logTransaction(String type, double amount, String description) {
        transactionHistory.add(new Transaction(type, amount, description));
    }

    public void printStatement() {
        System.out.println("\nTransaction history for account " + accountId + " = ");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : transactionHistory) {
                System.out.println(t);
            }
        }
    }
}