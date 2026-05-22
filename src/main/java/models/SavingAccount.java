package models;

public class SavingAccount extends BankAccount {
    private double interestRate;

    public SavingAccount(String accountId, String ownerName, double balance, Currency currency, double interestRate) {
        super(accountId, ownerName, balance, currency);
        this.interestRate = interestRate;
    }

    @Override
    public String getAccountType() {
        return "Saving";
    }
}